/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Pais;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author felip
 */
public class CrudPais extends AbstractCrud<Pais>{

    private EntityManager em;

    public CrudPais() {
        super(Pais.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }
    
}
