package ziez.bdl.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Pegawai;
import ziez.bdl.entity.Pelanggan;
import ziez.bdl.entity.PesananProduk;
import ziez.bdl.entity.StatusPesanan;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(Pesanan.class)
public class Pesanan_ { 

    public static volatile SingularAttribute<Pesanan, Date> tglJadi;
    public static volatile SingularAttribute<Pesanan, Pegawai> idPegawai;
    public static volatile CollectionAttribute<Pesanan, PesananProduk> pesananProdukCollection;
    public static volatile SingularAttribute<Pesanan, Date> tglPesan;
    public static volatile SingularAttribute<Pesanan, Long> idPesanan;
    public static volatile SingularAttribute<Pesanan, StatusPesanan> idStatusPesanan;
    public static volatile SingularAttribute<Pesanan, Pelanggan> idPelanggan;

}