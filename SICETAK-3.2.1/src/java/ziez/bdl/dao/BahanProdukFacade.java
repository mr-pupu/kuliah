/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ziez.bdl.entity.BahanProduk;

/**
 *
 * @author ziez
 */
@Stateless
public class BahanProdukFacade extends AbstractFacade<BahanProduk> {
    @PersistenceContext(unitName = "SICETAK-3.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BahanProdukFacade() {
        super(BahanProduk.class);
    }
    
}
