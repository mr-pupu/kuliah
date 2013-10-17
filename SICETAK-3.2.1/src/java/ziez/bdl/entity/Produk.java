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
@Table(name = "PRODUK")
@NamedQueries({
    @NamedQuery(name = "Produk.findAll", query = "SELECT p FROM Produk p"),
    @NamedQuery(name = "Produk.findByIdProduk", query = "SELECT p FROM Produk p WHERE p.idProduk = :idProduk"),
    @NamedQuery(name = "Produk.findByNamaProduk", query = "SELECT p FROM Produk p WHERE p.namaProduk = :namaProduk"),
    @NamedQuery(name = "Produk.findByHargaProduk", query = "SELECT p FROM Produk p WHERE p.hargaProduk = :hargaProduk"),
    @NamedQuery(name = "Produk.findBySatuanProduk", query = "SELECT p FROM Produk p WHERE p.satuanProduk = :satuanProduk"),
    @NamedQuery(name = "Produk.findByImageProduk", query = "SELECT p FROM Produk p WHERE p.imageProduk = :imageProduk")})
public class Produk implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRODUK")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idProduk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAMA_PRODUK")
    private String namaProduk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HARGA_PRODUK")
    private long hargaProduk;
    @Size(max = 20)
    @Column(name = "SATUAN_PRODUK")
    private String satuanProduk;
    @Size(max = 25)
    @Column(name = "IMAGE_PRODUK")
    private String imageProduk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produk")
    private Collection<PesananProduk> pesananProdukCollection;
    @JoinColumn(name = "ID_STATUS_PRODUK", referencedColumnName = "ID_STATUS_PRODUK")
    @ManyToOne
    private StatusProduk idStatusProduk;
    @JoinColumn(name = "ID_KATEGORI_PRODUK", referencedColumnName = "ID_KATEGORI_PRODUK")
    @ManyToOne(optional = false)
    private KategoriProduk idKategoriProduk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produk")
    private Collection<BahanProduk> bahanProdukCollection;

    public Produk() {
    }

    public Produk(Long idProduk) {
        this.idProduk = idProduk;
    }

    public Produk(Long idProduk, String namaProduk, long hargaProduk) {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.hargaProduk = hargaProduk;
    }

    public Long getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Long idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public long getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(long hargaProduk) {
        this.hargaProduk = hargaProduk;
    }

    public String getSatuanProduk() {
        return satuanProduk;
    }

    public void setSatuanProduk(String satuanProduk) {
        this.satuanProduk = satuanProduk;
    }

    public String getImageProduk() {
        return imageProduk;
    }

    public void setImageProduk(String imageProduk) {
        this.imageProduk = imageProduk;
    }

    public Collection<PesananProduk> getPesananProdukCollection() {
        return pesananProdukCollection;
    }

    public void setPesananProdukCollection(Collection<PesananProduk> pesananProdukCollection) {
        this.pesananProdukCollection = pesananProdukCollection;
    }

    public StatusProduk getIdStatusProduk() {
        return idStatusProduk;
    }

    public void setIdStatusProduk(StatusProduk idStatusProduk) {
        this.idStatusProduk = idStatusProduk;
    }

    public KategoriProduk getIdKategoriProduk() {
        return idKategoriProduk;
    }

    public void setIdKategoriProduk(KategoriProduk idKategoriProduk) {
        this.idKategoriProduk = idKategoriProduk;
    }

    public Collection<BahanProduk> getBahanProdukCollection() {
        return bahanProdukCollection;
    }

    public void setBahanProdukCollection(Collection<BahanProduk> bahanProdukCollection) {
        this.bahanProdukCollection = bahanProdukCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduk != null ? idProduk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produk)) {
            return false;
        }
        Produk other = (Produk) object;
        if ((this.idProduk == null && other.idProduk != null) || (this.idProduk != null && !this.idProduk.equals(other.idProduk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + idProduk + "] "+namaProduk;
    }
    
}
