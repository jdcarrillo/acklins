/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAFER
 */
@Stateless
public class ProductoFacade implements ProductoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Producto producto) {
        em.persist(producto);
    }

    public void edit(Producto producto) {
        em.merge(producto);
    }

    public void remove(Producto producto) {
        em.remove(em.merge(producto));
    }

    public Producto find(Object id) {
        return em.find(Producto.class, id);
    }

    public List<Producto> findAll() {
        return em.createQuery("select object(o) from Producto as o").getResultList();
    }

    public void calculoIva(Object id) {
        Producto producto;
        producto = em.find(Producto.class, id);
        em.refresh(producto);

        float sub;
        float total;
        sub = (float) (producto.getPreciosiniva()* 0.12);
        total= (float)(producto.getPreciosiniva()+sub);
        producto.setPrecioconiva(total);

    }
}
