/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ziez.bdl.entity.Pembayaran;

/**
 *
 * @author ziez
 */
@Stateless
public class PembayaranFacade extends AbstractFacade<Pembayaran> {
    @PersistenceContext(unitName = "SICETAK-3.0PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public PembayaranFacade() {
        super(Pembayaran.class);
    }
    
    
    
}
