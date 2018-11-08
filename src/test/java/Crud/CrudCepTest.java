/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Cep;
import Model.Cidade;
import Utilitarios.Util;
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
public class CrudCepTest {

    public CrudCepTest() {
        CrudCidadeTest c  = new CrudCidadeTest();
        c.testGravaCidade();
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
     * Test of SelectByNome method, of class CrudCep.
     */
    @Test
    public void testGravaCep() {
        Cep cep = new Cep();
        CrudCep crudCep = new CrudCep();
        CrudCidade crudCidade = new CrudCidade();
        List<Cidade> lst;
        boolean resposta;
        cep.setId(Util.geraId());
        cep.setCep("00000000");
        lst = crudCidade.getAll();
        cep.setCidadeId(lst.get(0).getId().toString());
        Exception insert;
        insert = crudCep.persist(cep);
        if (insert == null) {
            JOptionPane.showMessageDialog(null, "Dados Gravados Com Sucesso!!");
            resposta = true;
        } else {
            JOptionPane.showMessageDialog(null, "Erroo!!");
            resposta = false;
        }
        assertEquals(true, resposta);
    }
}
