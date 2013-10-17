/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ziez
 */
@Entity
@Table(name = "KATEGORI_PRODUK")
@NamedQueries({
    @NamedQuery(name = "KategoriProduk.findAll", query = "SELECT k FROM KategoriProduk k"),
    @NamedQuery(name = "KategoriProduk.findByIdKategoriProduk", query = "SELECT k FROM KategoriProduk k WHERE k.idKategoriProduk = :idKategoriProduk"),
    @NamedQuery(name = "KategoriProduk.findByNamaKategoriProduk", query = "SELECT k FROM KategoriProduk k WHERE k.namaKategoriProduk = :namaKategoriProduk")})
public class KategoriProduk implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KATEGORI_PRODUK")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idKategoriProduk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAMA_KATEGORI_PRODUK")
    private String namaKategoriProduk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKategoriProduk")
    private Collection<Produk> produkCollection;

    public KategoriProduk() {
    }

    public KategoriProduk(Long idKategoriProduk) {
        this.idKategoriProduk = idKategoriProduk;
    }

    public KategoriProduk(Long idKategoriProduk, String namaKategoriProduk) {
        this.idKategoriProduk = idKategoriProduk;
        this.namaKategoriProduk = namaKategoriProduk;
    }

    public Long getIdKategoriProduk() {
        return idKategoriProduk;
    }

    public void setIdKategoriProduk(Long idKategoriProduk) {
        this.idKategoriProduk = idKategoriProduk;
    }

    public String getNamaKategoriProduk() {
        return namaKategoriProduk;
    }

    public void setNamaKategoriProduk(String namaKategoriProduk) {
        this.namaKategoriProduk = namaKategoriProduk;
    }

    public Collection<Produk> getProdukCollection() {
        return produkCollection;
    }

    public void setProdukCollection(Collection<Produk> produkCollection) {
        this.produkCollection = produkCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKategoriProduk != null ? idKategoriProduk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KategoriProduk)) {
            return false;
        }
        KategoriProduk other = (KategoriProduk) object;
        if ((this.idKategoriProduk == null && other.idKategoriProduk != null) || (this.idKategoriProduk != null && !this.idKategoriProduk.equals(other.idKategoriProduk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "["+ idKategoriProduk + "] "+namaKategoriProduk;
    }
    
}
