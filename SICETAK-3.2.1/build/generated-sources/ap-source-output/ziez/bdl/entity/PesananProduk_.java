package ziez.bdl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Pesanan;
import ziez.bdl.entity.PesananProdukPK;
import ziez.bdl.entity.Produk;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(PesananProduk.class)
public class PesananProduk_ { 

    public static volatile SingularAttribute<PesananProduk, Produk> produk;
    public static volatile SingularAttribute<PesananProduk, PesananProdukPK> pesananProdukPK;
    public static volatile SingularAttribute<PesananProduk, Pesanan> pesanan;
    public static volatile SingularAttribute<PesananProduk, Long> jumlahProduk;

}