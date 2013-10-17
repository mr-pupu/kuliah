/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author p14
 */
@Entity
@Table(name = "PRODUK")
@NamedQueries({
    @NamedQuery(name = "Produk.findAll", query = "SELECT p FROM Produk p"),
    @NamedQuery(name = "Produk.findById", query = "SELECT p FROM Produk p WHERE p.id = :id"),
    @NamedQuery(name = "Produk.findByNama", query = "SELECT p FROM Produk p WHERE p.nama = :nama"),
    @NamedQuery(name = "Produk.findByHarga", query = "SELECT p FROM Produk p WHERE p.harga = :harga"),
    @NamedQuery(name = "Produk.findByStok", query = "SELECT p FROM Produk p WHERE p.stok = :stok"),
    @NamedQuery(name = "Produk.findByKeterangan", query = "SELECT p FROM Produk p WHERE p.keterangan = :keterangan"),
    @NamedQuery(name = "Produk.findByTglUpdate", query = "SELECT p FROM Produk p WHERE p.tglUpdate = :tglUpdate")})
public class Produk implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SequenceProduk")
    @SequenceGenerator(name="SequenceProduk", sequenceName="SEQ_PRODUK",allocationSize=1)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)

    @Column(name = "NAMA")
    private String nama;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "HARGA")
    private BigDecimal harga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOK")
    private long stok;
    @Size(max = 50)
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "TGL_UPDATE")
    @Temporal(TemporalType.DATE)
    private Date tglUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produk")
    private Collection<OrderProduk> orderProdukCollection;
    @JoinColumn(name = "ID_KATEGORI", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Kategori idKategori;

    public Produk() {
    }

    public Produk(Long id) {
        this.id = id;
    }

    public Produk(Long id, String nama, BigDecimal harga, long stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public long getStok() {
        return stok;
    }

    public void setStok(long stok) {
        this.stok = stok;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTglUpdate() {
        return tglUpdate;
    }

    public void setTglUpdate(Date tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    public Collection<OrderProduk> getOrderProdukCollection() {
        return orderProdukCollection;
    }

    public void setOrderProdukCollection(Collection<OrderProduk> orderProdukCollection) {
        this.orderProdukCollection = orderProdukCollection;
    }

    public Kategori getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Kategori idKategori) {
        this.idKategori = idKategori;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produk)) {
            return false;
        }
        Produk other = (Produk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Produk[ id=" + id + " ]";
    }

}
