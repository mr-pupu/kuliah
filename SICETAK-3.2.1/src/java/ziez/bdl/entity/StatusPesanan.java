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
@Table(name = "STATUS_PESANAN")
@NamedQueries({
    @NamedQuery(name = "StatusPesanan.findAll", query = "SELECT s FROM StatusPesanan s"),
    @NamedQuery(name = "StatusPesanan.findByIdStatusPesanan", query = "SELECT s FROM StatusPesanan s WHERE s.idStatusPesanan = :idStatusPesanan"),
    @NamedQuery(name = "StatusPesanan.findByNamaStatusPesanan", query = "SELECT s FROM StatusPesanan s WHERE s.namaStatusPesanan = :namaStatusPesanan")})
public class StatusPesanan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_STATUS_PESANAN")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idStatusPesanan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAMA_STATUS_PESANAN")
    private String namaStatusPesanan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatusPesanan")
    private Collection<Pesanan> pesananCollection;

    public StatusPesanan() {
    }

    public StatusPesanan(Long idStatusPesanan) {
        this.idStatusPesanan = idStatusPesanan;
    }

    public StatusPesanan(Long idStatusPesanan, String namaStatusPesanan) {
        this.idStatusPesanan = idStatusPesanan;
        this.namaStatusPesanan = namaStatusPesanan;
    }

    public Long getIdStatusPesanan() {
        return idStatusPesanan;
    }

    public void setIdStatusPesanan(Long idStatusPesanan) {
        this.idStatusPesanan = idStatusPesanan;
    }

    public String getNamaStatusPesanan() {
        return namaStatusPesanan;
    }

    public void setNamaStatusPesanan(String namaStatusPesanan) {
        this.namaStatusPesanan = namaStatusPesanan;
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
        hash += (idStatusPesanan != null ? idStatusPesanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusPesanan)) {
            return false;
        }
        StatusPesanan other = (StatusPesanan) object;
        if ((this.idStatusPesanan == null && other.idStatusPesanan != null) || (this.idStatusPesanan != null && !this.idStatusPesanan.equals(other.idStatusPesanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idStatusPesanan=" + idStatusPesanan + " ]"+"[ namaStatusPesanan=" + namaStatusPesanan + " ]";
    }
    
}
