/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author p14
 */
@Embeddable
public class OrderProdukPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PELANGGAN_ORDER")
    private long idPelangganOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRODUK")
    private long idProduk;

    public OrderProdukPK() {
    }

    public OrderProdukPK(long idPelangganOrder, long idProduk) {
        this.idPelangganOrder = idPelangganOrder;
        this.idProduk = idProduk;
    }

    public long getIdPelangganOrder() {
        return idPelangganOrder;
    }

    public void setIdPelangganOrder(long idPelangganOrder) {
        this.idPelangganOrder = idPelangganOrder;
    }

    public long getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(long idProduk) {
        this.idProduk = idProduk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPelangganOrder;
        hash += (int) idProduk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderProdukPK)) {
            return false;
        }
        OrderProdukPK other = (OrderProdukPK) object;
        if (this.idPelangganOrder != other.idPelangganOrder) {
            return false;
        }
        if (this.idProduk != other.idProduk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderProdukPK[ idPelangganOrder=" + idPelangganOrder + ", idProduk=" + idProduk + " ]";
    }

}
