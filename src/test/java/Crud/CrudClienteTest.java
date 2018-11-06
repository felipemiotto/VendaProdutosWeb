/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Cep;
import Model.Cliente;
import Utilitarios.Util;
import java.util.List;
import javax.swing.JOptionPane;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luizf
 */
public class CrudClienteTest {

    public CrudClienteTest() {
    }

      /**
     * Test of getEntityManager method, of class CrudCliente.
     */
    @Test
    public void testAdicoonaCliente() {
        Cliente cliente = new Cliente();
        CrudCep crudCep = new CrudCep();
        boolean resposta = true;
        List<Cep> lst;
        lst = crudCep.getAll();
        cliente.setId(Util.geraId());
        cliente.setNome("Teste Nome");
        cliente.setBairro("Teste Bairro");
        cliente.setComplemento("Teste Complemento");
        cliente.setCpf("000000000-0");
        cliente.setEmail("email@teste.com.br");
        cliente.setEndereco("Rua teste");
        cliente.setFone("0000000000");
        cliente.setNumero("00");
        cliente.setCepId(lst.get(0).getId().toString());
        CrudCliente crud = new CrudCliente();
        Exception insert;
        insert = crud.persist(cliente);
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
