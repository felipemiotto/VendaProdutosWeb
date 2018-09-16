/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author alexj
 */
public class CrudCep extends AbstractCrud<Model.Cep> {

    private EntityManager em;

    //private static final String PU = EMNames.EMN1;
    //private static final 

    public CrudCep() {
        super(Model.Cep.class);
    }

    public List<Model.Cep> SelectByNome(String nome) {
        List<Model.Cep> lista;
        try {
            lista= getEntityManager().createNamedQuery("Cep.findByNome").setParameter("cep", "%" + nome.toUpperCase() + "%").getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

}
