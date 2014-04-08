/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Proveedor;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author MAFER
 */
@Remote
public interface ProveedorFacadeRemote {

    void create(Proveedor proveedor);

    void edit(Proveedor proveedor);

    void remove(Proveedor proveedor);

    Proveedor find(Object id);

    List<Proveedor> findAll();

}
