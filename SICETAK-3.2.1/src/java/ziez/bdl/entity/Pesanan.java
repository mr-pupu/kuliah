/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ziez
 */
@Entity
@Table(name = "PESANAN")
@NamedQueries({
    @NamedQuery(name = "Pesanan.findAll", query = "SELECT p FROM Pesanan p"),
    @NamedQuery(name = "Pesanan.findByIdPesanan", query = "SELECT p FROM Pesanan p WHERE p.idPesanan = :idPesanan"),
    @NamedQuery(name = "Pesanan.findByTglPesan", query = "SELECT p FROM Pesanan p WHERE p.tglPesan = :tglPesan"),
    @NamedQuery(name = "Pesanan.findByTglJadi", query = "SELECT p FROM Pesanan p WHERE p.tglJadi = :tglJadi")})
public class Pesanan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PESANAN")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idPesanan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TGL_PESAN")
    @Temporal(TemporalType.DATE)
    private Date tglPesan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TGL_JADI")
    @Temporal(TemporalType.DATE)
    private Date tglJadi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesanan")
    private Collection<PesananProduk> pesananProdukCollection;
    
    @JoinColumn(name = "ID_STATUS_PESANAN", referencedColumnName = "ID_STATUS_PESANAN")
    @ManyToOne(optional = false)
    private StatusPesanan idStatusPesanan;
    @JoinColumn(name = "ID_PELANGGAN", referencedColumnName = "ID_PELANGGAN")
    @ManyToOne(optional = false)
    private Pelanggan idPelanggan;
    @JoinColumn(name = "ID_PEGAWAI", referencedColumnName = "ID_PEGAWAI")
    @ManyToOne(optional = false)
    private Pegawai idPegawai;

    public Pesanan() {
    }

    public Pesanan(Long idPesanan) {
        this.idPesanan = idPesanan;
    }

    public Pesanan(Long idPesanan, Date tglPesan, Date tglJadi) {
        this.idPesanan = idPesanan;
        this.tglPesan = tglPesan;
        this.tglJadi = tglJadi;
    }

    public Long getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(Long idPesanan) {
        this.idPesanan = idPesanan;
    }

    public Date getTglPesan() {
        return tglPesan;
    }

    public void setTglPesan(Date tglPesan) {
        this.tglPesan = tglPesan;
    }

    public Date getTglJadi() {
        return tglJadi;
    }

    public void setTglJadi(Date tglJadi) {
        this.tglJadi = tglJadi;
    }

    public Collection<PesananProduk> getPesananProdukCollection() {
        return pesananProdukCollection;
    }

    public void setPesananProdukCollection(Collection<PesananProduk> pesananProdukCollection) {
        this.pesananProdukCollection = pesananProdukCollection;
    }

    public StatusPesanan getIdStatusPesanan() {
        return idStatusPesanan;
    }

    public void setIdStatusPesanan(StatusPesanan idStatusPesanan) {
        this.idStatusPesanan = idStatusPesanan;
    }

    public Pelanggan getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(Pelanggan idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public Pegawai getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Pegawai idPegawai) {
        this.idPegawai = idPegawai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPesanan != null ? idPesanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pesanan)) {
            return false;
        }
        Pesanan other = (Pesanan) object;
        if ((this.idPesanan == null && other.idPesanan != null) || (this.idPesanan != null && !this.idPesanan.equals(other.idPesanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idPesanan=" + idPesanan + " ]"+"[ tglPesanan=" + tglPesan + " ]";
    }
    
}
