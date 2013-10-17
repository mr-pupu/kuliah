package ziez.bdl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Pembayaran;
import ziez.bdl.entity.Pesanan;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(Pegawai.class)
public class Pegawai_ { 

    public static volatile SingularAttribute<Pegawai, Long> idPegawai;
    public static volatile CollectionAttribute<Pegawai, Pembayaran> pembayaranCollection;
    public static volatile SingularAttribute<Pegawai, String> namaPegawai;
    public static volatile CollectionAttribute<Pegawai, Pesanan> pesananCollection;
    public static volatile SingularAttribute<Pegawai, String> tlpPegawai;
    public static volatile SingularAttribute<Pegawai, String> alamatPegawai;

}