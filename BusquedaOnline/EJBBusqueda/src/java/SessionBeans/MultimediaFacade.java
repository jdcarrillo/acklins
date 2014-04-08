/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Multimedia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAFER
 */
@Stateless
public class MultimediaFacade implements MultimediaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Multimedia multimedia) {
        em.persist(multimedia);
    }

    public void edit(Multimedia multimedia) {
        em.merge(multimedia);
    }

    public void remove(Multimedia multimedia) {
        em.remove(em.merge(multimedia));
    }

    public Multimedia find(Object id) {
        return em.find(Multimedia.class, id);
    }

    public List<Multimedia> findAll() {
        return em.createQuery("select object(o) from Multimedia as o").getResultList();
    }

}
