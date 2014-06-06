/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Promocion;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author MAFER
 */
@Remote
public interface PromocionFacadeRemote {

    void create(Promocion promocion);

    void edit(Promocion promocion);

    void remove(Promocion promocion);

    Promocion find(Object id);

    List<Promocion> findAll();

    void calculoDescuento(Object id);

}
