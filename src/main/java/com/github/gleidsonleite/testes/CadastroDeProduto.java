package com.github.gleidsonleite.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.github.gleidsonleite.dao.CategoriaDao;
import com.github.gleidsonleite.dao.ProdutoDao;
import com.github.gleidsonleite.modelo.Categoria;
import com.github.gleidsonleite.modelo.Produto;
import com.github.gleidsonleite.utils.JPAUtil;

public class CadastroDeProduto {
  public static void main(String[] args) {
    cadastrarProduto();

    Long id = 1L;
    EntityManager entityManager = JPAUtil.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    Produto produto = produtoDao.buscarPorId(id);
    System.out.println(produto.getNome());
    List<Produto> produtos = produtoDao.buscarTodos();
    produtos.forEach(p -> System.out.println(p.getNome()));
  }

  private static void cadastrarProduto() {
    Categoria celulares = new Categoria();
    Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

    EntityManager entityManager = JPAUtil.getEntityManager();
    ProdutoDao produtoDao = new ProdutoDao(entityManager);
    CategoriaDao categoriaDao = new CategoriaDao(entityManager);

    entityManager.getTransaction().begin();

    categoriaDao.salvar(celulares);
    produtoDao.salvar(celular);

    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
