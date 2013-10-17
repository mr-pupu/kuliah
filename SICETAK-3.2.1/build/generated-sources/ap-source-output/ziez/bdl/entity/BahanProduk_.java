package ziez.bdl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Bahan;
import ziez.bdl.entity.BahanProdukPK;
import ziez.bdl.entity.Produk;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(BahanProduk.class)
public class BahanProduk_ { 

    public static volatile SingularAttribute<BahanProduk, Produk> produk;
    public static volatile SingularAttribute<BahanProduk, BahanProdukPK> bahanProdukPK;
    public static volatile SingularAttribute<BahanProduk, Double> jumlahBahan;
    public static volatile SingularAttribute<BahanProduk, Bahan> bahan;

}