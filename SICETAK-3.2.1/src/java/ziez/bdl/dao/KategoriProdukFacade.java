/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ziez.bdl.entity.KategoriProduk;

/**
 *
 * @author ziez
 */
@Stateless
public class KategoriProdukFacade extends AbstractFacade<KategoriProduk> {
    @PersistenceContext(unitName = "SICETAK-3.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KategoriProdukFacade() {
        super(KategoriProduk.class);
    }
    
}
