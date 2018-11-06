/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Pais;
import Utilitarios.Util;
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
public class CrudPaisTest {

    public CrudPaisTest() {
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
     * Test of getEntityManager method, of class CrudPais.
     */
    @Test
    public void testGravaPais() {
        Pais pais = new Pais();
        boolean resposta;
        pais.setId(Util.geraId());
        pais.setNome("Teste Nome Pais");
        pais.setCodigo("0000");
        CrudPais crud = new CrudPais();
        Exception insert;
        insert = crud.persist(pais);
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
