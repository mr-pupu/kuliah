/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author p14
 */
@Entity
@Table(name = "KATEGORI")
@NamedQueries({
    @NamedQuery(name = "Kategori.findAll", query = "SELECT k FROM Kategori k"),
    @NamedQuery(name = "Kategori.findById", query = "SELECT k FROM Kategori k WHERE k.id = :id"),
    @NamedQuery(name = "Kategori.findByNama", query = "SELECT k FROM Kategori k WHERE k.nama = :nama")})
public class Kategori implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SequenceKategori")
    @SequenceGenerator(name="SequenceKategori", sequenceName="SEQ_KATEGORI", allocationSize=1)
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAMA")
    private String nama;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKategori")
    private Collection<Produk> produkCollection;

    public Kategori() {
    }

    public Kategori(Short id) {
        this.id = id;
    }

    public Kategori(Short id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategori)) {
            return false;
        }
        Kategori other = (Kategori) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Kategori[ id=" + id + " ]";
    }

}
