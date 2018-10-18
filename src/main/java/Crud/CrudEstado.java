/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Estado;
import Model.Pais;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author felip
 */
public class CrudEstado extends AbstractCrud<Estado>{

    private EntityManager em;

    public CrudEstado() {
        super(Estado.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {       
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;    
    }
    
}
