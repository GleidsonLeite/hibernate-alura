package com.github.gleidsonleite.dao;

import javax.persistence.EntityManager;

import com.github.gleidsonleite.modelo.Pedido;

public class PedidoDao {
  private EntityManager entityManager;

  public PedidoDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void salvar(Pedido pedido) {
    this.entityManager.persist(pedido);
  }

  public void atualizar(Pedido pedido) {
    this.entityManager.merge(pedido);
  }
}
