/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Cliente;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author MAFER
 */
@Remote
public interface ClienteFacadeRemote {

    void create(Cliente cliente);

    void edit(Cliente cliente);

    void remove(Cliente cliente);

    Cliente find(Object id);

    List<Cliente> findAll();

}
