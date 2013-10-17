/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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

/**
 *
 * @author p14
 */
@Entity
@Table(name = "PELANGGAN_ORDER")
@NamedQueries({
    @NamedQuery(name = "PelangganOrder.findAll", query = "SELECT p FROM PelangganOrder p"),
    @NamedQuery(name = "PelangganOrder.findById", query = "SELECT p FROM PelangganOrder p WHERE p.id = :id"),
    @NamedQuery(name = "PelangganOrder.findByPelanggan", query = "SELECT p FROM PelangganOrder p WHERE p.pelanggan = :pelanggan"), // manually created
    @NamedQuery(name = "PelangganOrder.findByJumlah", query = "SELECT p FROM PelangganOrder p WHERE p.jumlah = :jumlah"),
    @NamedQuery(name = "PelangganOrder.findByTglOrder", query = "SELECT p FROM PelangganOrder p WHERE p.tglOrder = :tglOrder"),
    @NamedQuery(name = "PelangganOrder.findByNoKonfirmasi", query = "SELECT p FROM PelangganOrder p WHERE p.noKonfirmasi = :noKonfirmasi")})
public class PelangganOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SequencePelangganOrder")
    @SequenceGenerator(name="SequencePelangganOrder", sequenceName="SEQ_PELANGGAN_ORDER", allocationSize=1)
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "JUMLAH")
    private BigDecimal jumlah;
    @Column(name = "TGL_ORDER")
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglOrder = Calendar.getInstance().getTime();
    @Basic(optional = false)
    @NotNull
    @Column(name = "NO_KONFIRMASI")
    private long noKonfirmasi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pelangganOrder")
    private Collection<OrderProduk> orderProdukCollection;
    @JoinColumn(name = "ID_PELANGGAN", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pelanggan pelanggan;


    public PelangganOrder() {
    }

    public PelangganOrder(Long id) {
        this.id = id;
    }

    public PelangganOrder(Long id, BigDecimal jumlah, Date tglOrder,long noKonfirmasi) {
        this.id = id;
        this.jumlah = jumlah;
        this.tglOrder = tglOrder;
        this.noKonfirmasi = noKonfirmasi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTglOrder() {
        return tglOrder;
    }

    public void setTglOrder(Date tglOrder) {
        this.tglOrder = tglOrder;
    }

    public long getNoKonfirmasi() {
        return noKonfirmasi;
    }

    public void setNoKonfirmasi(long noKonfirmasi) {
        this.noKonfirmasi = noKonfirmasi;
    }

    public Collection<OrderProduk> getOrderProdukCollection() {
        return orderProdukCollection;
    }

    public void setOrderProdukCollection(Collection<OrderProduk> orderProdukCollection) {
        this.orderProdukCollection = orderProdukCollection;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
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
        if (!(object instanceof PelangganOrder)) {
            return false;
        }
        PelangganOrder other = (PelangganOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PelangganOrder[ id=" + id + " ]";
    }

}
