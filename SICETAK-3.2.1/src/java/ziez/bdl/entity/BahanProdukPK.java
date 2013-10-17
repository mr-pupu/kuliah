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
public class BahanProdukPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRODUK")
    private long idProduk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BAHAN")
    private long idBahan;

    public BahanProdukPK() {
    }

    public BahanProdukPK(long idProduk, long idBahan) {
        this.idProduk = idProduk;
        this.idBahan = idBahan;
    }

    public long getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(long idProduk) {
        this.idProduk = idProduk;
    }

    public long getIdBahan() {
        return idBahan;
    }

    public void setIdBahan(long idBahan) {
        this.idBahan = idBahan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduk;
        hash += (int) idBahan;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BahanProdukPK)) {
            return false;
        }
        BahanProdukPK other = (BahanProdukPK) object;
        if (this.idProduk != other.idProduk) {
            return false;
        }
        if (this.idBahan != other.idBahan) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ziez.bdl.entity.BahanProdukPK[ idProduk=" + idProduk + ", idBahan=" + idBahan + " ]";
    }
    
}
