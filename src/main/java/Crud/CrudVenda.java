package Crud;

import Model.Venda;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author luizf
 */
public class CrudVenda extends AbstractCrud<Venda> {
 
    private EntityManager em;

    public CrudVenda() {
        super(Venda.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;    
    }
}