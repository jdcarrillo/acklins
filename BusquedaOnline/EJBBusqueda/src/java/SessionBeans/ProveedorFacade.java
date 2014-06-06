/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Proveedor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAFER
 */
@Stateless
public class ProveedorFacade implements ProveedorFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Proveedor proveedor) {
        em.persist(proveedor);
    }

    public void edit(Proveedor proveedor) {
        em.merge(proveedor);
    }

    public void remove(Proveedor proveedor) {
        em.remove(em.merge(proveedor));
    }

    public Proveedor find(Object id) {
        return em.find(Proveedor.class, id);
    }

    public List<Proveedor> findAll() {
        return em.createQuery("select object(o) from Proveedor as o").getResultList();
    }

}
