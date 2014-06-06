package EntityBeans;

import EntityBeans.Tienda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-03T18:32:08")
@StaticMetamodel(Estadoconexion.class)
public class Estadoconexion_ { 

    public static volatile SingularAttribute<Estadoconexion, Date> fecha;
    public static volatile SingularAttribute<Estadoconexion, Boolean> estadoConex;
    public static volatile SingularAttribute<Estadoconexion, Tienda> idTienda;
    public static volatile SingularAttribute<Estadoconexion, Integer> idEstadoConex;

}