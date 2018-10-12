/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Model.Documento;
import Utilitarios.Util;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author luizf
 */
public class CrudDocumento extends AbstractCrud<Documento> {

    private EntityManager em;

    public CrudDocumento() {
        super(Documento.class);
    }

    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;
    }

    public List<Documento> busca(String nome) {
        List<Documento> lista;
        try {
            lista = getEntityManager()
                    .createNamedQuery("Documento.findByDescricao")
                    .setParameter("descricao", "%" + nome.toUpperCase() + "%").getResultList();
            return lista;
        } catch (Exception e) {
            Util.log(e.getMessage());
        }
        return null;
    }
}
