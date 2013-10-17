/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ziez.bdl.entity.Bahan;

/**
 *
 * @author ziez
 */
@Stateless
public class BahanFacade extends AbstractFacade<Bahan> {
    @PersistenceContext(unitName = "SICETAK-3.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BahanFacade() {
        super(Bahan.class);
    }
    
}
