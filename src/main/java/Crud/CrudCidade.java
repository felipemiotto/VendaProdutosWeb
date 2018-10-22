/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Cidade;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author felip
 */
public class CrudCidade extends AbstractCrud<Cidade>{

    private EntityManager em;

    public CrudCidade() {
        super(Cidade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;      
    }
    
}
