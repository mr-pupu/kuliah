/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PelangganOrder;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author p14
 */
@Stateless
public class PelangganOrderFacade extends AbstractFacade<PelangganOrder> {
    @PersistenceContext(unitName = "TokotoPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PelangganOrderFacade() {
        super(PelangganOrder.class);
    }

    // overridden - refresh method called to retrieve order id from database
    @Override
    public PelangganOrder find(Object id) {
        PelangganOrder order = em.find(PelangganOrder.class, id);
        em.refresh(order);
        return order;
    }

    @RolesAllowed("tokotoAdmin")
    public PelangganOrder findByPelanggan(Object pelanggan) {
        return (PelangganOrder) em.createNamedQuery("PelangganOrder.findByPelanggan").setParameter("pelanggan", pelanggan).getSingleResult();
    }

}
