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
public class CrudProduto extends AbstractCrud{
        
    private EntityManager em;
    
     public CrudProduto() {
        super(Model.Produto.class);
    }
     
     public List<Model.Produto> SelectByDescricao(String descricao) {
        List<Model.Produto> lista;
        try {
            lista= getEntityManager().createNamedQuery("grupo.findByDescricao").setParameter("descricao", "%" + descricao.toUpperCase() + "%").getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public CrudProduto(Class entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;      
    }
    
}
