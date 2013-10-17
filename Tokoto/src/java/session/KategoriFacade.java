/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Kategori;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author p14
 */
@Stateless
public class KategoriFacade extends AbstractFacade<Kategori> {
    @PersistenceContext(unitName = "TokotoPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public KategoriFacade() {
        super(Kategori.class);
    }

}
