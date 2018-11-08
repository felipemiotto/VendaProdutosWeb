/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Cidade;
import Model.Estado;
import Model.Pais;
import Utilitarios.Util;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felip
 */
public class CrudCidadeTest {

    public CrudCidadeTest() {
        CrudEstadoTest e = new CrudEstadoTest();
        e.testGravaEstado();
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
     * Test of getEntityManager method, of class CrudCidade.
     */
    @Test
    public void testGravaCidade() {
        Cidade cidade = new Cidade();
        CrudCidade crudCidade = new CrudCidade();
        CrudEstado crud = new CrudEstado();
        List<Estado> lst;
        lst = crud.getAll();
        boolean resposta;
        cidade.setId(Util.geraId());
        cidade.setNome("Teste Nome Cidade");
        cidade.setEstadoId(lst.get(0).getId().toString());
        Exception insert;
        insert = crudCidade.persist(cidade);
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
