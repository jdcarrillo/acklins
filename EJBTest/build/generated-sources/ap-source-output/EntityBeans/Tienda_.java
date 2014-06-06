package EntityBeans;

import EntityBeans.Estadoconexion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-03T18:32:08")
@StaticMetamodel(Tienda.class)
public class Tienda_ { 

    public static volatile SingularAttribute<Tienda, Integer> idtienda;
    public static volatile SingularAttribute<Tienda, Boolean> status;
    public static volatile SingularAttribute<Tienda, String> tienda;
    public static volatile CollectionAttribute<Tienda, Estadoconexion> estadoconexionCollection;

}