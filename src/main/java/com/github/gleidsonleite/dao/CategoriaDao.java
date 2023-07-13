package com.github.gleidsonleite.dao;

import javax.persistence.EntityManager;

import com.github.gleidsonleite.modelo.Categoria;

public class CategoriaDao {

  private EntityManager entityManager;

  public CategoriaDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void salvar(Categoria categoria) {
    this.entityManager.persist(categoria);
  }

  public void atualizar(Categoria categoria) {
    this.entityManager.merge(categoria);
  }

  public void remover(Categoria categoria) {
    this.entityManager.remove(categoria);
  }
}
