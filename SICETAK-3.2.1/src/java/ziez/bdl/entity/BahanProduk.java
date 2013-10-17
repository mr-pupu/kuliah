/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ziez
 */
@Entity
@Table(name = "BAHAN_PRODUK")
@NamedQueries({
    @NamedQuery(name = "BahanProduk.findAll", query = "SELECT b FROM BahanProduk b"),
    @NamedQuery(name = "BahanProduk.findByIdProduk", query = "SELECT b FROM BahanProduk b WHERE b.bahanProdukPK.idProduk = :idProduk"),
    @NamedQuery(name = "BahanProduk.findByIdBahan", query = "SELECT b FROM BahanProduk b WHERE b.bahanProdukPK.idBahan = :idBahan"),
    @NamedQuery(name = "BahanProduk.findByJumlahBahan", query = "SELECT b FROM BahanProduk b WHERE b.jumlahBahan = :jumlahBahan")})
public class BahanProduk implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BahanProdukPK bahanProdukPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "JUMLAH_BAHAN")
    private Double jumlahBahan;
    @JoinColumn(name = "ID_PRODUK", referencedColumnName = "ID_PRODUK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produk produk;
    @JoinColumn(name = "ID_BAHAN", referencedColumnName = "ID_BAHAN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bahan bahan;

    public BahanProduk() {
    }

    public BahanProduk(BahanProdukPK bahanProdukPK) {
        this.bahanProdukPK = bahanProdukPK;
    }

    public BahanProduk(long idProduk, long idBahan) {
        this.bahanProdukPK = new BahanProdukPK(idProduk, idBahan);
    }

    public BahanProdukPK getBahanProdukPK() {
        return bahanProdukPK;
    }

    public void setBahanProdukPK(BahanProdukPK bahanProdukPK) {
        this.bahanProdukPK = bahanProdukPK;
    }

    public Double getJumlahBahan() {
        return jumlahBahan;
    }

    public void setJumlahBahan(Double jumlahBahan) {
        this.jumlahBahan = jumlahBahan;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Bahan getBahan() {
        return bahan;
    }

    public void setBahan(Bahan bahan) {
        this.bahan = bahan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bahanProdukPK != null ? bahanProdukPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BahanProduk)) {
            return false;
        }
        BahanProduk other = (BahanProduk) object;
        if ((this.bahanProdukPK == null && other.bahanProdukPK != null) || (this.bahanProdukPK != null && !this.bahanProdukPK.equals(other.bahanProdukPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ bahanProdukPK=" + bahanProdukPK + " ]";
    }
    
}
