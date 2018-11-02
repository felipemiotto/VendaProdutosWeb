/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Cidade;
import Model.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author luizf
 */
public class CrudCliente extends AbstractCrud<Cliente> {
     private EntityManager em;

    public CrudCliente() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;      
    }
}
