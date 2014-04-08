/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Catalogo;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author MAFER
 */
@Remote
public interface CatalogoFacadeRemote {

    void create(Catalogo catalogo);

    void edit(Catalogo catalogo);

    void remove(Catalogo catalogo);

    Catalogo find(Object id);

    List<Catalogo> findAll();

}
