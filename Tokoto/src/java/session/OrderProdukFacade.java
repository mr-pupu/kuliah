/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderProduk;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author p14
 */
@Stateless
public class OrderProdukFacade extends AbstractFacade<OrderProduk> {
    @PersistenceContext(unitName = "TokotoPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderProdukFacade() {
        super(OrderProduk.class);
    }

    // manually created
    public List<OrderProduk> findByIdOrder(Object id) {
        return em.createNamedQuery("OrderProduk.findByIdPelangganOrder").setParameter("idPelangganOrder", id).getResultList();
    }
}
