package ziez.bdl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Pesanan;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(Pelanggan.class)
public class Pelanggan_ { 

    public static volatile SingularAttribute<Pelanggan, String> tlpPelanggan;
    public static volatile CollectionAttribute<Pelanggan, Pesanan> pesananCollection;
    public static volatile SingularAttribute<Pelanggan, String> namaPelanggan;
    public static volatile SingularAttribute<Pelanggan, String> alamatPelanggan;
    public static volatile SingularAttribute<Pelanggan, String> kotaPelanggan;
    public static volatile SingularAttribute<Pelanggan, Long> idPelanggan;

}