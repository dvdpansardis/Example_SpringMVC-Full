package org.Example_SpringMVC.Full.repository;

import org.Example_SpringMVC.Full.builder.ProdutoBuilder;
import org.Example_SpringMVC.Full.conf.DataSourceConfigurationTest;
import org.Example_SpringMVC.Full.conf.JPAConfiguration;
import org.Example_SpringMVC.Full.model.Produto;
import org.Example_SpringMVC.Full.model.TipoPreco;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDAOTest {

    @Autowired
    private ProdutoDAO produtoDAO;

    @Test
    @Transactional //Para dar rollback no final do test!!! malandragem!!!
    public void deveSomarTodosOsPrecosPorTipoLivro(){
        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(2).buildAll();
        List<Produto> livrosEBooks = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

        livrosImpressos.stream().forEach(produtoDAO::gravar);
        livrosEBooks.stream().forEach(produtoDAO::gravar);

        BigDecimal bigDecimal = produtoDAO.somaPrecosPorTipoPreco(TipoPreco.IMPRESSO);
        Assert.assertEquals(new BigDecimal(30).setScale(2), bigDecimal);
    }
}
