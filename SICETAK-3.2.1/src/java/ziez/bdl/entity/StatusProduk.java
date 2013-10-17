/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ziez
 */
@Entity
@Table(name = "STATUS_PRODUK")
@NamedQueries({
    @NamedQuery(name = "StatusProduk.findAll", query = "SELECT s FROM StatusProduk s"),
    @NamedQuery(name = "StatusProduk.findByIdStatusProduk", query = "SELECT s FROM StatusProduk s WHERE s.idStatusProduk = :idStatusProduk"),
    @NamedQuery(name = "StatusProduk.findByNamaStatusProduk", query = "SELECT s FROM StatusProduk s WHERE s.namaStatusProduk = :namaStatusProduk")})
public class StatusProduk implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_STATUS_PRODUK")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigDecimal idStatusProduk;
    @Size(max = 20)
    @Column(name = "NAMA_STATUS_PRODUK")
    private String namaStatusProduk;
    @OneToMany(mappedBy = "idStatusProduk")
    private Collection<Produk> produkCollection;

    public StatusProduk() {
    }

    public StatusProduk(BigDecimal idStatusProduk) {
        this.idStatusProduk = idStatusProduk;
    }

    public BigDecimal getIdStatusProduk() {
        return idStatusProduk;
    }

    public void setIdStatusProduk(BigDecimal idStatusProduk) {
        this.idStatusProduk = idStatusProduk;
    }

    public String getNamaStatusProduk() {
        return namaStatusProduk;
    }

    public void setNamaStatusProduk(String namaStatusProduk) {
        this.namaStatusProduk = namaStatusProduk;
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
        hash += (idStatusProduk != null ? idStatusProduk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusProduk)) {
            return false;
        }
        StatusProduk other = (StatusProduk) object;
        if ((this.idStatusProduk == null && other.idStatusProduk != null) || (this.idStatusProduk != null && !this.idStatusProduk.equals(other.idStatusProduk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idStatusProduk=" + idStatusProduk + " ]"+"[ namaStatusProduk=" + namaStatusProduk + " ]";
    }
    
}
