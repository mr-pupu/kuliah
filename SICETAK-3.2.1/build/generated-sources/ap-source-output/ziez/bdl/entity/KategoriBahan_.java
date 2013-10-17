package ziez.bdl.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ziez.bdl.entity.Bahan;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-17T19:09:51")
@StaticMetamodel(KategoriBahan.class)
public class KategoriBahan_ { 

    public static volatile SingularAttribute<KategoriBahan, Long> idKategoriBahan;
    public static volatile SingularAttribute<KategoriBahan, String> namaKategoriBahan;
    public static volatile CollectionAttribute<KategoriBahan, Bahan> bahanCollection;

}