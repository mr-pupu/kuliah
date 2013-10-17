/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ziez
 */
@Entity
@Table(name = "PEMBAYARAN")
@NamedQueries({
    @NamedQuery(name = "Pembayaran.findAll", query = "SELECT p FROM Pembayaran p"),
    @NamedQuery(name = "Pembayaran.findByIdPembayaran", query = "SELECT p FROM Pembayaran p WHERE p.idPembayaran = :idPembayaran"),
    @NamedQuery(name = "Pembayaran.findByTglPembayaran", query = "SELECT p FROM Pembayaran p WHERE p.tglPembayaran = :tglPembayaran"),
    @NamedQuery(name = "Pembayaran.findByTotalPembayaran", query = "SELECT p FROM Pembayaran p WHERE p.totalPembayaran = :totalPembayaran")})


public class Pembayaran implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PEMBAYARAN")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idPembayaran;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TGL_PEMBAYARAN")
    @Temporal(TemporalType.DATE)
    private Date tglPembayaran;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PEMBAYARAN")
    private long totalPembayaran;
    @JoinColumn(name = "ID_PESANAN", referencedColumnName = "ID_PESANAN")
    @OneToOne(optional = false)
    private Pesanan idPesanan;
    @JoinColumn(name = "ID_PEGAWAI", referencedColumnName = "ID_PEGAWAI")
    @ManyToOne(optional = false)
    private Pegawai idPegawai;

    public Pembayaran() {
    }

    public Pembayaran(Long idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public Pembayaran(Long idPembayaran, Date tglPembayaran, long totalPembayaran) {
        this.idPembayaran = idPembayaran;
        this.tglPembayaran = tglPembayaran;
        this.totalPembayaran = totalPembayaran;
    }

    public Long getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Long idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public Date getTglPembayaran() {
        return tglPembayaran;
    }

    public void setTglPembayaran(Date tglPembayaran) {
        this.tglPembayaran = tglPembayaran;
    }

    public long getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(long totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public Pesanan getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(Pesanan idPesanan) {
        this.idPesanan = idPesanan;
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
        hash += (idPembayaran != null ? idPembayaran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pembayaran)) {
            return false;
        }
        Pembayaran other = (Pembayaran) object;
        if ((this.idPembayaran == null && other.idPembayaran != null) || (this.idPembayaran != null && !this.idPembayaran.equals(other.idPembayaran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idPembayaran=" + idPembayaran + " ]"+"[ tglPembayaran=" + tglPembayaran + " ]";
    }
    
}
