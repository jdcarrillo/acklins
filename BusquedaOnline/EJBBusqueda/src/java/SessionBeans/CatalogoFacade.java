/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Catalogo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAFER
 */
@Stateless
public class CatalogoFacade implements CatalogoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Catalogo catalogo) {
        em.persist(catalogo);
    }

    public void edit(Catalogo catalogo) {
        em.merge(catalogo);
    }

    public void remove(Catalogo catalogo) {
        em.remove(em.merge(catalogo));
    }

    public Catalogo find(Object id) {
        return em.find(Catalogo.class, id);
    }

    public List<Catalogo> findAll() {
        return em.createQuery("select object(o) from Catalogo as o").getResultList();
    }

}
