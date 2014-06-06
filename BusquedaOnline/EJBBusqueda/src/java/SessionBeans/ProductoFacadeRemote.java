/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Producto;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author MAFER
 */
@Remote
public interface ProductoFacadeRemote {

    void create(Producto producto);

    void edit(Producto producto);

    void remove(Producto producto);

    Producto find(Object id);

    List<Producto> findAll();

    void calculoIva(Object id);


}
