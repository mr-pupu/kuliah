/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Produk;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author p14
 */
@Stateless
public class ProdukFacade extends AbstractFacade<Produk> {
    @PersistenceContext(unitName = "TokotoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdukFacade() {
        super(Produk.class);
    }


}
