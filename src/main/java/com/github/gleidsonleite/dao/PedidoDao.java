package com.github.gleidsonleite.dao;

import javax.persistence.EntityManager;

import com.github.gleidsonleite.modelo.Pedido;
import com.github.gleidsonleite.vo.RelatorioDeVendasVo;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
  private final EntityManager entityManager;

  public PedidoDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public void salvar(Pedido pedido) {
    this.entityManager.persist(pedido);
  }

  public void atualizar(Pedido pedido) {
    this.entityManager.merge(pedido);
  }

  public BigDecimal valorTotalVendido() {
    String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
    return this.entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
  }

  public List<RelatorioDeVendasVo> relatorioDeVendas() {
    String jpql = "select new com.github.gleidsonleite.vo.RelatorioDeVendasVo(produto.nome, sum(item.quantidade) as quantidadeTotal, max(pedido.data)) from Pedido pedido join pedido.itens item join item.produto produto group by produto.nome order by quantidadeTotal desc";
    return this.entityManager.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
  }
}
