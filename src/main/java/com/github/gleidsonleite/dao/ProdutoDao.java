package com.github.gleidsonleite.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.github.gleidsonleite.modelo.Produto;

public class ProdutoDao {
  private EntityManager entityManager;

  public ProdutoDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void salvar(Produto produto) {
    this.entityManager.persist(produto);
  }

  public void atualizar(Produto produto) {
    this.entityManager.merge(produto);
  }

  public void remover(Produto produto) {
    this.entityManager.remove(produto);
  }

  public Produto buscarPorId(Long id) {
    return this.entityManager.find(Produto.class, id);
  }

  public List<Produto> buscarTodos() {
    return this.entityManager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
  }

  public List<Produto> buscarPorNome(String nome) {
    return this.entityManager.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class)
        .setParameter("nome", nome).getResultList();
  }

  public List<Produto> buscarPorNomeDaCategoria(String nome) {
    return this.entityManager.createQuery("SELECT p FROM Produto p WHERE p.categoria.nome = :nome", Produto.class)
        .setParameter("nome", nome).getResultList();
  }

  public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
    return this.entityManager.createQuery("SELECT p.preco FROM Produto p WHERE p.nome = :nome", BigDecimal.class)
        .setParameter("nome", nome).getSingleResult();
  }
}
