package com.github.gleidsonleite.dao;

import javax.persistence.EntityManager;

import com.github.gleidsonleite.modelo.Cliente;

public class ClienteDao {
  private EntityManager entityManager;

  public ClienteDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void salvar(Cliente cliente) {
    this.entityManager.persist(cliente);
  }

  public Cliente buscarPorId(Long id) {
    return this.entityManager.find(Cliente.class, id);
  }
}
