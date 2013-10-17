package ziez.bdl.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Produk;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(StatusProduk.class)
public class StatusProduk_ { 

    public static volatile CollectionAttribute<StatusProduk, Produk> produkCollection;
    public static volatile SingularAttribute<StatusProduk, String> namaStatusProduk;
    public static volatile SingularAttribute<StatusProduk, BigDecimal> idStatusProduk;

}