/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderProduk;
import entity.OrderProdukPK;
import entity.Pelanggan;
import entity.PelangganOrder;
import entity.Produk;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tas.IsiTasBelanja;
import tas.TasBelanja;

/**
 *
 * @author p14
 */
@Stateless
@LocalBean
public class OrderManager {

    @PersistenceContext(unitName = "TokotoPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private ProdukFacade produkFacade;
    @EJB
    private PelangganOrderFacade pelangganOrderFacade;
    @EJB
    private OrderProdukFacade orderProdukFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long placeOrder(String nama, String email, String phone, String alamat, TasBelanja tas) {

        try {
            Pelanggan pelanggan = addPelanggan(nama, email, phone, alamat);
            PelangganOrder order = addOrder(pelanggan, tas);
            addOrderedItems(order, tas);


            return order.getId();
        } catch (Exception e) {
            context.setRollbackOnly();
            return 0;
        }

    }

    private Pelanggan addPelanggan(String nama, String email, String phone, String alamat) {

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setNama(nama);
        pelanggan.setEmail(email);
        pelanggan.setPhone(phone);
        pelanggan.setAlamat(alamat);

        em.persist(pelanggan);
        return pelanggan;
    }

    private PelangganOrder addOrder(Pelanggan pelanggan, TasBelanja tas) {
        // set up customer order
        PelangganOrder order = new PelangganOrder();
        order.setPelanggan(pelanggan);
        order.setJumlah(BigDecimal.valueOf(tas.getTotal()));

        // create confirmation number
        Random random = new Random();
        long i = random.nextInt(999999999);
        order.setNoKonfirmasi(i);
        em.persist(order);

        return order;
    }

    private void addOrderedItems(PelangganOrder order, TasBelanja tas) {
        em.flush();

        List<IsiTasBelanja> items = tas.getItems();

        // iterate through shopping cart and create OrderedProducts
        for (IsiTasBelanja scItems : items) {
            long idProduk = scItems.getProduct().getId();

            OrderProdukPK orderProdukPK = new OrderProdukPK();
            orderProdukPK.setIdPelangganOrder(order.getId());
            orderProdukPK.setIdProduk(idProduk);

            OrderProduk orderedItem = new OrderProduk(orderProdukPK);

            orderedItem.setQuantity(scItems.getQuantity());
            em.persist(orderedItem);


        }

    }



    public Map getOrderDetails(long idOrder) {

        Map orderMap = new HashMap();

        // get order
        PelangganOrder order = pelangganOrderFacade.find(idOrder);

        // get customer
        Pelanggan pelanggan = order.getPelanggan();

        // get all ordered products
        List<OrderProduk> orderProduk = orderProdukFacade.findByIdOrder(idOrder);

        // get product details for ordered items
        List<Produk> produk = new ArrayList<Produk>();

        for (OrderProduk op : orderProduk) {

            Produk p = (Produk) produkFacade.find(op.getOrderProdukPK().getIdProduk());
            produk.add(p);
        }

        // add each item to orderMap
        orderMap.put("recordOrder", order);
        orderMap.put("pelanggan", pelanggan);
        orderMap.put("orderProduk", orderProduk);
        orderMap.put("produk", produk);

        return orderMap;
    }
}
