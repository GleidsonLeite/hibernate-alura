package com.github.gleidsonleite.testes;

import java.math.BigDecimal;

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

public class CadastroDePedido {

  public static void main(String[] args) {
    popularBancoDeDados();

    EntityManager entityManager = JPAUtil.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    Produto produto = produtoDao.buscarPorId(1L);

    entityManager.getTransaction().begin();

    ClienteDao clienteDao = new ClienteDao(entityManager);

    Cliente cliente = clienteDao.buscarPorId(1L);
    Pedido pedido = new Pedido(cliente);

    pedido.adicionarItem(new ItemPedido(10, pedido, produto));

    PedidoDao pedidoDao = new PedidoDao(entityManager);
    pedidoDao.salvar(pedido);

    entityManager.getTransaction().commit();
  }

  private static void popularBancoDeDados() {
    Categoria celulares = new Categoria();
    Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

    Cliente cliente = new Cliente("Gleidson", "123456");

    EntityManager entityManager = JPAUtil.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);
    ClienteDao clienteDao = new ClienteDao(entityManager);

    entityManager.getTransaction().begin();

    categoriaDao.salvar(celulares);
    produtoDao.salvar(celular);
    clienteDao.salvar(cliente);
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
