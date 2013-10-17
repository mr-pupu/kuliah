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
@Table(name = "BAHAN")
@NamedQueries({
    @NamedQuery(name = "Bahan.findAll", query = "SELECT b FROM Bahan b"),
    @NamedQuery(name = "Bahan.findByIdBahan", query = "SELECT b FROM Bahan b WHERE b.idBahan = :idBahan"),
    @NamedQuery(name = "Bahan.findByNamaBahan", query = "SELECT b FROM Bahan b WHERE b.namaBahan = :namaBahan"),
    @NamedQuery(name = "Bahan.findByJumlahBahan", query = "SELECT b FROM Bahan b WHERE b.jumlahBahan = :jumlahBahan"),
    @NamedQuery(name = "Bahan.findBySatuanBahan", query = "SELECT b FROM Bahan b WHERE b.satuanBahan = :satuanBahan")})
public class Bahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BAHAN")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idBahan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAMA_BAHAN")
    private String namaBahan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JUMLAH_BAHAN")
    private double jumlahBahan;
    @Size(max = 20)
    @Column(name = "SATUAN_BAHAN")
    private String satuanBahan;
    @JoinColumn(name = "ID_KATEGORI_BAHAN", referencedColumnName = "ID_KATEGORI_BAHAN")
    @ManyToOne(optional = false)
    private KategoriBahan idKategoriBahan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bahan")
    private Collection<BahanProduk> bahanProdukCollection;

    public Bahan() {
    }

    public Bahan(Long idBahan) {
        this.idBahan = idBahan;
    }

    public Bahan(Long idBahan, String namaBahan, double jumlahBahan) {
        this.idBahan = idBahan;
        this.namaBahan = namaBahan;
        this.jumlahBahan = jumlahBahan;
    }

    public Long getIdBahan() {
        return idBahan;
    }

    public void setIdBahan(Long idBahan) {
        this.idBahan = idBahan;
    }

    public String getNamaBahan() {
        return namaBahan;
    }

    public void setNamaBahan(String namaBahan) {
        this.namaBahan = namaBahan;
    }

    public double getJumlahBahan() {
        return jumlahBahan;
    }

    public void setJumlahBahan(double jumlahBahan) {
        this.jumlahBahan = jumlahBahan;
    }

    public String getSatuanBahan() {
        return satuanBahan;
    }

    public void setSatuanBahan(String satuanBahan) {
        this.satuanBahan = satuanBahan;
    }

    public KategoriBahan getIdKategoriBahan() {
        return idKategoriBahan;
    }

    public void setIdKategoriBahan(KategoriBahan idKategoriBahan) {
        this.idKategoriBahan = idKategoriBahan;
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
        hash += (idBahan != null ? idBahan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bahan)) {
            return false;
        }
        Bahan other = (Bahan) object;
        if ((this.idBahan == null && other.idBahan != null) || (this.idBahan != null && !this.idBahan.equals(other.idBahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idBahan=" + idBahan + " ]"+"[namaBahan=" +namaBahan+"]";
    }
    
}
