/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ziez.bdl.entity.StatusProduk;

/**
 *
 * @author ziez
 */
@Stateless
public class StatusProdukFacade extends AbstractFacade<StatusProduk> {
    @PersistenceContext(unitName = "SICETAK-3.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusProdukFacade() {
        super(StatusProduk.class);
    }
    
}
