package ziez.bdl.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Pegawai;
import ziez.bdl.entity.Pesanan;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(Pembayaran.class)
public class Pembayaran_ { 

    public static volatile SingularAttribute<Pembayaran, Pegawai> idPegawai;
    public static volatile SingularAttribute<Pembayaran, Long> totalPembayaran;
    public static volatile SingularAttribute<Pembayaran, Date> tglPembayaran;
    public static volatile SingularAttribute<Pembayaran, Long> idPembayaran;
    public static volatile SingularAttribute<Pembayaran, Pesanan> idPesanan;

}