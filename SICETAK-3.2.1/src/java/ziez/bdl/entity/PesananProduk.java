/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ziez
 */
@Entity
@Table(name = "PESANAN_PRODUK")
@NamedQueries({
    @NamedQuery(name = "PesananProduk.findAll", query = "SELECT p FROM PesananProduk p"),
    @NamedQuery(name = "PesananProduk.findByIdPesanan", query = "SELECT p FROM PesananProduk p WHERE p.pesananProdukPK.idPesanan = :idPesanan"),
    @NamedQuery(name = "PesananProduk.findByIdProduk", query = "SELECT p FROM PesananProduk p WHERE p.pesananProdukPK.idProduk = :idProduk"),
    @NamedQuery(name = "PesananProduk.findByJumlahProduk", query = "SELECT p FROM PesananProduk p WHERE p.jumlahProduk = :jumlahProduk")})
public class PesananProduk implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PesananProdukPK pesananProdukPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JUMLAH_PRODUK")
    private long jumlahProduk;
    @JoinColumn(name = "ID_PRODUK", referencedColumnName = "ID_PRODUK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produk produk;
    @JoinColumn(name = "ID_PESANAN", referencedColumnName = "ID_PESANAN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pesanan pesanan;

    public PesananProduk() {
    }

    public PesananProduk(PesananProdukPK pesananProdukPK) {
        this.pesananProdukPK = pesananProdukPK;
    }

    public PesananProduk(PesananProdukPK pesananProdukPK, long jumlahProduk) {
        this.pesananProdukPK = pesananProdukPK;
        this.jumlahProduk = jumlahProduk;
    }

    public PesananProduk(long idPesanan, long idProduk) {
        this.pesananProdukPK = new PesananProdukPK(idPesanan, idProduk);
    }

    public PesananProdukPK getPesananProdukPK() {
        return pesananProdukPK;
    }

    public void setPesananProdukPK(PesananProdukPK pesananProdukPK) {
        this.pesananProdukPK = pesananProdukPK;
    }

    public long getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(long jumlahProduk) {
        this.jumlahProduk = jumlahProduk;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Pesanan getPesanan() {
        return pesanan;
    }

    public void setPesanan(Pesanan pesanan) {
        this.pesanan = pesanan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pesananProdukPK != null ? pesananProdukPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PesananProduk)) {
            return false;
        }
        PesananProduk other = (PesananProduk) object;
        if ((this.pesananProdukPK == null && other.pesananProdukPK != null) || (this.pesananProdukPK != null && !this.pesananProdukPK.equals(other.pesananProdukPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ziez.bdl.entity.PesananProduk[ pesananProdukPK=" + pesananProdukPK + " ]";
    }
    
}
