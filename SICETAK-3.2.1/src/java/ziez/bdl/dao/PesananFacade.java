/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ziez.bdl.entity.Pesanan;

/**
 *
 * @author ziez
 */
@Stateless
public class PesananFacade extends AbstractFacade<Pesanan> {
    @PersistenceContext(unitName = "SICETAK-3.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PesananFacade() {
        super(Pesanan.class);
    }
    
}
