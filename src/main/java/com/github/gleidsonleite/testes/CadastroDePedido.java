package com.github.gleidsonleite.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.github.gleidsonleite.dao.CategoriaDao;
import com.github.gleidsonleite.dao.ClienteDao;
import com.github.gleidsonleite.dao.PedidoDao;
import com.github.gleidsonleite.dao.ProdutoDao;
import com.github.gleidsonleite.modelo.Categoria;
import com.github.gleidsonleite.modelo.Cliente;
import com.github.gleidsonleite.modelo.ItemPedido;
import com.github.gleidsonleite.modelo.Pedido;
import com.github.gleidsonleite.modelo.Produto;
import com.github.gleidsonleite.utils.JPAUtil;
import com.github.gleidsonleite.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

  public static void main(String[] args) {
    popularBancoDeDados();

    EntityManager entityManager = JPAUtil.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    Produto produto = produtoDao.buscarPorId(1L);
    Produto produto2 = produtoDao.buscarPorId(2L);
    Produto produto3 = produtoDao.buscarPorId(3L);

    entityManager.getTransaction().begin();

    ClienteDao clienteDao = new ClienteDao(entityManager);

    Cliente cliente = clienteDao.buscarPorId(1L);
    Pedido pedido = new Pedido(cliente);

    pedido.adicionarItem(new ItemPedido(10, pedido, produto));
    pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

    Pedido pedido2 = new Pedido(cliente);
    pedido2.adicionarItem(new ItemPedido(2, pedido2, produto3));

    PedidoDao pedidoDao = new PedidoDao(entityManager);
    pedidoDao.salvar(pedido);
    pedidoDao.salvar(pedido2);

    entityManager.getTransaction().commit();

    BigDecimal totalVendido = pedidoDao.valorTotalVendido();
    System.out.println("Valor total: " + totalVendido);

    List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
    relatorio.forEach(System.out::println);
  }

  private static void popularBancoDeDados() {
    Categoria celulares = new Categoria("CELULARES");
    Categoria videogames = new Categoria("VIDEOGAMES");
    Categoria informatica = new Categoria("INFORMATICA");

    Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
    Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogames);
    Produto macbook = new Produto("MacBook", "Macbook Pro Retina", new BigDecimal("10000"), informatica);

    Cliente cliente = new Cliente("Gleidson", "123456");

    EntityManager entityManager = JPAUtil.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);
    ClienteDao clienteDao = new ClienteDao(entityManager);

    entityManager.getTransaction().begin();

    categoriaDao.salvar(celulares);
    categoriaDao.salvar(videogames);
    categoriaDao.salvar(informatica);

    produtoDao.salvar(celular);
    produtoDao.salvar(videogame);
    produtoDao.salvar(macbook);

    clienteDao.salvar(cliente);
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
