package ziez.bdl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.BahanProduk;
import ziez.bdl.entity.KategoriProduk;
import ziez.bdl.entity.PesananProduk;
import ziez.bdl.entity.StatusProduk;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(Produk.class)
public class Produk_ { 

    public static volatile SingularAttribute<Produk, String> imageProduk;
    public static volatile SingularAttribute<Produk, KategoriProduk> idKategoriProduk;
    public static volatile SingularAttribute<Produk, String> satuanProduk;
    public static volatile CollectionAttribute<Produk, BahanProduk> bahanProdukCollection;
    public static volatile SingularAttribute<Produk, Long> idProduk;
    public static volatile SingularAttribute<Produk, String> namaProduk;
    public static volatile CollectionAttribute<Produk, PesananProduk> pesananProdukCollection;
    public static volatile SingularAttribute<Produk, Long> hargaProduk;
    public static volatile SingularAttribute<Produk, StatusProduk> idStatusProduk;

}