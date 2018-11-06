/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Grupo;
import Utilitarios.Util;
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
 * @author luizf
 */
public class CrudGrupoProdutoTest {
    
    public CrudGrupoProdutoTest() {
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
     * Test of SelectByNome_codigo method, of class CrudGrupoProduto.
     */
    @Test
    public void testGravaGrupoProduto() {
        CrudGrupoProduto crudGrupoProduto = new CrudGrupoProduto();
        Grupo grupo = new Grupo();
        grupo.setId(Util.geraId());
        grupo.setCodigo("000");
        grupo.setNome("Teste Grupo");
        Exception insert;
        insert = crudGrupoProduto.persist(grupo);
        boolean resposta;
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
