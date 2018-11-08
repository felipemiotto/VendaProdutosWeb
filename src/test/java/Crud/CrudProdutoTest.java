/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Grupo;
import Model.Produto;
import Utilitarios.Util;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luizf
 */
public class CrudProdutoTest {

    public CrudProdutoTest() {
        CrudGrupoProdutoTest p = new CrudGrupoProdutoTest();
        p.testGravaGrupoProduto();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of SelectByDescricao method, of class CrudProduto.
     */
    @Test
    public void testGravaProduto() {
        Produto produto = new Produto();
        CrudProduto crudProduto = new CrudProduto();
        CrudGrupoProduto crudGrupoProduto = new CrudGrupoProduto();
        List<Grupo> lst;
        lst = crudGrupoProduto.getAll();
        boolean resposta;
        produto.setId(Util.geraId());
        produto.setCusto(new BigDecimal(1));
        produto.setDescricao("Teste Produto");
        produto.setGrupoId(lst.get(0).getId().toString());
        produto.setPeso(new BigDecimal(1));
        produto.setPreco(new BigDecimal(1));
        produto.setUnidade("TT");
        Exception insert;
        insert = crudProduto.persist(produto);
        if (insert == null) {
            System.out.println("Dados Gravados Com Sucesso!!");
            resposta = true;
        } else {
            System.out.println("Erroo!!");
            resposta = false;
        }
        assertEquals(true, resposta);
    }

}
