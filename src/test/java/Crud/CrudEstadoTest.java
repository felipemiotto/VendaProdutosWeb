/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

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
public class CrudEstadoTest {
    
    public CrudEstadoTest() {
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
     * Test of getEntityManager method, of class CrudEstado.
     */
    @Test
    public void testGravaCidade() {
        boolean resposta;
        CrudPais crud = new CrudPais();
        CrudEstado crudEstado = new CrudEstado();
        List<Pais> lst;
        lst = crud.getAll();
        Estado estado = new Estado();
        estado.setId(Util.geraId());
        estado.setNome("Teste Nome Estado");
        estado.setSigla("TE");
        estado.setPaisId(lst.get(0).getId().toString());
        Exception insert;
        insert = crudEstado.persist(estado);
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
