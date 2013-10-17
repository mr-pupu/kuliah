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
@Table(name = "PEGAWAI")
@NamedQueries({
    @NamedQuery(name = "Pegawai.findAll", query = "SELECT p FROM Pegawai p"),
    @NamedQuery(name = "Pegawai.findByIdPegawai", query = "SELECT p FROM Pegawai p WHERE p.idPegawai = :idPegawai"),
    @NamedQuery(name = "Pegawai.findByNamaPegawai", query = "SELECT p FROM Pegawai p WHERE p.namaPegawai = :namaPegawai"),
    @NamedQuery(name = "Pegawai.findByAlamatPegawai", query = "SELECT p FROM Pegawai p WHERE p.alamatPegawai = :alamatPegawai"),
    @NamedQuery(name = "Pegawai.findByTlpPegawai", query = "SELECT p FROM Pegawai p WHERE p.tlpPegawai = :tlpPegawai")})
public class Pegawai implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PEGAWAI")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idPegawai;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAMA_PEGAWAI")
    private String namaPegawai;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ALAMAT_PEGAWAI")
    private String alamatPegawai;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TLP_PEGAWAI")
    private String tlpPegawai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPegawai")
    private Collection<Pembayaran> pembayaranCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPegawai")
    private Collection<Pesanan> pesananCollection;

    public Pegawai() {
    }

    public Pegawai(Long idPegawai) {
        this.idPegawai = idPegawai;
    }

    public Pegawai(Long idPegawai, String namaPegawai, String alamatPegawai, String tlpPegawai) {
        this.idPegawai = idPegawai;
        this.namaPegawai = namaPegawai;
        this.alamatPegawai = alamatPegawai;
        this.tlpPegawai = tlpPegawai;
    }

    public Long getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Long idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getAlamatPegawai() {
        return alamatPegawai;
    }

    public void setAlamatPegawai(String alamatPegawai) {
        this.alamatPegawai = alamatPegawai;
    }

    public String getTlpPegawai() {
        return tlpPegawai;
    }

    public void setTlpPegawai(String tlpPegawai) {
        this.tlpPegawai = tlpPegawai;
    }

    public Collection<Pembayaran> getPembayaranCollection() {
        return pembayaranCollection;
    }

    public void setPembayaranCollection(Collection<Pembayaran> pembayaranCollection) {
        this.pembayaranCollection = pembayaranCollection;
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
        hash += (idPegawai != null ? idPegawai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pegawai)) {
            return false;
        }
        Pegawai other = (Pegawai) object;
        if ((this.idPegawai == null && other.idPegawai != null) || (this.idPegawai != null && !this.idPegawai.equals(other.idPegawai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + idPegawai + "] "+namaPegawai;
    }
    
}
