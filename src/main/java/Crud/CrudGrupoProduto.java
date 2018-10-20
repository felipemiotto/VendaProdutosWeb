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
public class CrudGrupoProduto extends AbstractCrud<Model.Grupo> {

    private EntityManager em;

    //private static final String PU = EMNames.EMN1;
    //private static final 

    public CrudGrupoProduto() {
        super(Model.Grupo.class);
    }

    public List<Model.Grupo> SelectByNome(String nome) {
        List<Model.Grupo> lista;
        try {
            lista= getEntityManager().createNamedQuery("grupo.findByNome").setParameter("nome", "%" + nome.toUpperCase() + "%").getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Model.Grupo SelectByNome_codigo(String nome, String codigo) {
        List<Model.Grupo> lista;
        try {
            lista= getEntityManager().createNamedQuery("grupo.findByCodigo_nome").setParameter("nome", "%" + nome.toUpperCase() + "%" + "%"+ codigo.toUpperCase() + "%").getResultList();
            return lista.get(0);
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
