package org.Example_SpringMVC.Full.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.Example_SpringMVC.Full.model.Produto;
import org.Example_SpringMVC.Full.model.TipoPreco;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProdutoDAO {

    @PersistenceContext
    private EntityManager manager;

    public void gravar(Produto produto) {
        manager.persist(produto);
    }

    public List<Produto> listar() {
        return manager.createQuery("select distinct(p) from Produto p", Produto.class).getResultList();
    }

    public Produto find(Long id) {
        return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public BigDecimal somaPrecosPorTipoPreco(TipoPreco tipoPreco) {
        return manager.createQuery("select sum(preco.valor) from Produto p " +
                " join p.precos preco where preco.tipo = :tipo", BigDecimal.class).setParameter("tipo", tipoPreco).getSingleResult();
    }
}
