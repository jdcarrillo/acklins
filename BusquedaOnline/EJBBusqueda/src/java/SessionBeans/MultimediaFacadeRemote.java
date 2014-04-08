/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Multimedia;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author MAFER
 */
@Remote
public interface MultimediaFacadeRemote {

    void create(Multimedia multimedia);

    void edit(Multimedia multimedia);

    void remove(Multimedia multimedia);

    Multimedia find(Object id);

    List<Multimedia> findAll();

}
