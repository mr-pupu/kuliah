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
@Table(name = "PELANGGAN")
@NamedQueries({
    @NamedQuery(name = "Pelanggan.findAll", query = "SELECT p FROM Pelanggan p"),
    @NamedQuery(name = "Pelanggan.findByIdPelanggan", query = "SELECT p FROM Pelanggan p WHERE p.idPelanggan = :idPelanggan"),
    @NamedQuery(name = "Pelanggan.findByNamaPelanggan", query = "SELECT p FROM Pelanggan p WHERE p.namaPelanggan = :namaPelanggan"),
    @NamedQuery(name = "Pelanggan.findByAlamatPelanggan", query = "SELECT p FROM Pelanggan p WHERE p.alamatPelanggan = :alamatPelanggan"),
    @NamedQuery(name = "Pelanggan.findByTlpPelanggan", query = "SELECT p FROM Pelanggan p WHERE p.tlpPelanggan = :tlpPelanggan"),
    @NamedQuery(name = "Pelanggan.findByKotaPelanggan", query = "SELECT p FROM Pelanggan p WHERE p.kotaPelanggan = :kotaPelanggan")})
public class Pelanggan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PELANGGAN")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idPelanggan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAMA_PELANGGAN")
    private String namaPelanggan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ALAMAT_PELANGGAN")
    private String alamatPelanggan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TLP_PELANGGAN")
    private String tlpPelanggan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "KOTA_PELANGGAN")
    private String kotaPelanggan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPelanggan")
    private Collection<Pesanan> pesananCollection;

    public Pelanggan() {
    }

    public Pelanggan(Long idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public Pelanggan(Long idPelanggan, String namaPelanggan, String alamatPelanggan, String tlpPelanggan, String kotaPelanggan) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamatPelanggan = alamatPelanggan;
        this.tlpPelanggan = tlpPelanggan;
        this.kotaPelanggan = kotaPelanggan;
    }

    public Long getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(Long idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getAlamatPelanggan() {
        return alamatPelanggan;
    }

    public void setAlamatPelanggan(String alamatPelanggan) {
        this.alamatPelanggan = alamatPelanggan;
    }

    public String getTlpPelanggan() {
        return tlpPelanggan;
    }

    public void setTlpPelanggan(String tlpPelanggan) {
        this.tlpPelanggan = tlpPelanggan;
    }

    public String getKotaPelanggan() {
        return kotaPelanggan;
    }

    public void setKotaPelanggan(String kotaPelanggan) {
        this.kotaPelanggan = kotaPelanggan;
    }

    public Collection<Pesanan> getPesananCollection() {
        return pesananCollection;
    }

    public void setPesananCollection(Collection<Pesanan> pesananCollection) {
        this.pesananCollection = pesananCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPelanggan != null ? idPelanggan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelanggan)) {
            return false;
        }
        Pelanggan other = (Pelanggan) object;
        if ((this.idPelanggan == null && other.idPelanggan != null) || (this.idPelanggan != null && !this.idPelanggan.equals(other.idPelanggan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idPelanggan=" + idPelanggan + " ]"+"[ namaPelanggan=" + namaPelanggan + " ]";
    }
    
}
