package ziez.bdl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.BahanProduk;
import ziez.bdl.entity.KategoriBahan;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(Bahan.class)
public class Bahan_ { 

    public static volatile SingularAttribute<Bahan, String> satuanBahan;
    public static volatile SingularAttribute<Bahan, KategoriBahan> idKategoriBahan;
    public static volatile CollectionAttribute<Bahan, BahanProduk> bahanProdukCollection;
    public static volatile SingularAttribute<Bahan, String> namaBahan;
    public static volatile SingularAttribute<Bahan, Double> jumlahBahan;
    public static volatile SingularAttribute<Bahan, Long> idBahan;

}