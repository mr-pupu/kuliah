/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author p14
 */
@Entity
@Table(name = "ORDER_PRODUK")
@NamedQueries({
    @NamedQuery(name = "OrderProduk.findAll", query = "SELECT o FROM OrderProduk o"),
    @NamedQuery(name = "OrderProduk.findByIdPelangganOrder", query = "SELECT o FROM OrderProduk o WHERE o.orderProdukPK.idPelangganOrder = :idPelangganOrder"),
    @NamedQuery(name = "OrderProduk.findByIdProduk", query = "SELECT o FROM OrderProduk o WHERE o.orderProdukPK.idProduk = :idProduk"),
    @NamedQuery(name = "OrderProduk.findByQuantity", query = "SELECT o FROM OrderProduk o WHERE o.quantity = :quantity")})
public class OrderProduk implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderProdukPK orderProdukPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @JoinColumn(name = "ID_PRODUK", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produk produk;
    @JoinColumn(name = "ID_PELANGGAN_ORDER", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PelangganOrder pelangganOrder;

    public OrderProduk() {
    }

    public OrderProduk(OrderProdukPK orderProdukPK) {
        this.orderProdukPK = orderProdukPK;
    }

    public OrderProduk(OrderProdukPK orderProdukPK, int quantity) {
        this.orderProdukPK = orderProdukPK;
        this.quantity = quantity;
    }

    public OrderProduk(long idPelangganOrder, long idProduk) {
        this.orderProdukPK = new OrderProdukPK(idPelangganOrder, idProduk);
    }

    public OrderProdukPK getOrderProdukPK() {
        return orderProdukPK;
    }

    public void setOrderProdukPK(OrderProdukPK orderProdukPK) {
        this.orderProdukPK = orderProdukPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public PelangganOrder getPelangganOrder() {
        return pelangganOrder;
    }

    public void setPelangganOrder(PelangganOrder pelangganOrder) {
        this.pelangganOrder = pelangganOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderProdukPK != null ? orderProdukPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderProduk)) {
            return false;
        }
        OrderProduk other = (OrderProduk) object;
        if ((this.orderProdukPK == null && other.orderProdukPK != null) || (this.orderProdukPK != null && !this.orderProdukPK.equals(other.orderProdukPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderProduk[ orderProdukPK=" + orderProdukPK + " ]";
    }

}
