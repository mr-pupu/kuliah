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
@Table(name = "KATEGORI_BAHAN")
@NamedQueries({
    @NamedQuery(name = "KategoriBahan.findAll", query = "SELECT k FROM KategoriBahan k"),
    @NamedQuery(name = "KategoriBahan.findByIdKategoriBahan", query = "SELECT k FROM KategoriBahan k WHERE k.idKategoriBahan = :idKategoriBahan"),
    @NamedQuery(name = "KategoriBahan.findByNamaKategoriBahan", query = "SELECT k FROM KategoriBahan k WHERE k.namaKategoriBahan = :namaKategoriBahan")})
public class KategoriBahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KATEGORI_BAHAN")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idKategoriBahan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAMA_KATEGORI_BAHAN")
    private String namaKategoriBahan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKategoriBahan")
    private Collection<Bahan> bahanCollection;

    public KategoriBahan() {
    }

    public KategoriBahan(Long idKategoriBahan) {
        this.idKategoriBahan = idKategoriBahan;
    }

    public KategoriBahan(Long idKategoriBahan, String namaKategoriBahan) {
        this.idKategoriBahan = idKategoriBahan;
        this.namaKategoriBahan = namaKategoriBahan;
    }

    public Long getIdKategoriBahan() {
        return idKategoriBahan;
    }

    public void setIdKategoriBahan(Long idKategoriBahan) {
        this.idKategoriBahan = idKategoriBahan;
    }

    public String getNamaKategoriBahan() {
        return namaKategoriBahan;
    }

    public void setNamaKategoriBahan(String namaKategoriBahan) {
        this.namaKategoriBahan = namaKategoriBahan;
    }

    public Collection<Bahan> getBahanCollection() {
        return bahanCollection;
    }

    public void setBahanCollection(Collection<Bahan> bahanCollection) {
        this.bahanCollection = bahanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKategoriBahan != null ? idKategoriBahan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KategoriBahan)) {
            return false;
        }
        KategoriBahan other = (KategoriBahan) object;
        if ((this.idKategoriBahan == null && other.idKategoriBahan != null) || (this.idKategoriBahan != null && !this.idKategoriBahan.equals(other.idKategoriBahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idKategoriBahan=" + idKategoriBahan + " ]"+"[ namaKategoriBahan=" + namaKategoriBahan + " ]";
    }
    
}
