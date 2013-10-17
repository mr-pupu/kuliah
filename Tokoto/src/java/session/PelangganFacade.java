/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Pelanggan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author p14
 */
@Stateless
public class PelangganFacade extends AbstractFacade<Pelanggan> {
    @PersistenceContext(unitName = "TokotoPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PelangganFacade() {
        super(Pelanggan.class);
    }

}
