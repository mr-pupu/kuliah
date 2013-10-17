/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ziez
 */
@Embeddable
public class PesananProdukPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PESANAN")
    private long idPesanan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRODUK")
    private long idProduk;

    public PesananProdukPK() {
    }

    public PesananProdukPK(long idPesanan, long idProduk) {
        this.idPesanan = idPesanan;
        this.idProduk = idProduk;
    }

    public long getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(long idPesanan) {
        this.idPesanan = idPesanan;
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
        hash += (int) idPesanan;
        hash += (int) idProduk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PesananProdukPK)) {
            return false;
        }
        PesananProdukPK other = (PesananProdukPK) object;
        if (this.idPesanan != other.idPesanan) {
            return false;
        }
        if (this.idProduk != other.idProduk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ziez.bdl.entity.PesananProdukPK[ idPesanan=" + idPesanan + ", idProduk=" + idProduk + " ]";
    }
    
}
