package Crud;

import Model.ItensVenda;
import Model.Venda;
import java.util.ArrayList;
import java.util.Collection;
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
    public Exception persist(Venda venda) { //precisamos de um persist especifico neste caso
        try {
            getEntityManager().getTransaction().begin();
            Collection<ItensVenda> litem = venda.getItensVendaCollection();
            venda.setItensVendaCollection(new ArrayList<ItensVenda>());
            getEntityManager().persist(venda);
            getEntityManager().getTransaction().commit();
            
            if (litem.size() > 0) {
                getEntityManager().getTransaction().begin();
                venda.setItensVendaCollection(litem);
                for (ItensVenda itemvenda : litem) {
                    itemvenda.setVendaId(venda);
                    getEntityManager().persist(itemvenda);
                }
                 getEntityManager().getTransaction().commit();
            }
           
            return null;
        } catch (Exception e) {
            return e;
        }
    }
    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(EMNames.EMN1, EMNames.getEMN1Props()).createEntityManager();
        }
        return em;    
    }
}