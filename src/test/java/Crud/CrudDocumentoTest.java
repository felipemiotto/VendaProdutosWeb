/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Documento;
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
 * @author luizf
 */
public class CrudDocumentoTest {

    public CrudDocumentoTest() {
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
     * Test of getEntityManager method, of class CrudDocumento.
     */
    @Test
    public void testGravaDocumento() {
        CrudDocumento crudDocumento = new CrudDocumento();
        Documento documento = new Documento();
        documento.setId(Util.geraId());
        documento.setCodigo("000");
        documento.setDescricao("Teste Documento");
        Exception insert;
        insert = crudDocumento.persist(documento);
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
