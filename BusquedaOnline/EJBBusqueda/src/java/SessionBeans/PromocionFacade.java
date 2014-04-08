/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBeans;

import EntityBeans.Promocion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAFER
 */
@Stateless
public class PromocionFacade implements PromocionFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Promocion promocion) {
        em.persist(promocion);
    }

    public void edit(Promocion promocion) {
        em.merge(promocion);
    }

    public void remove(Promocion promocion) {
        em.remove(em.merge(promocion));
    }

    public Promocion find(Object id) {
        return em.find(Promocion.class, id);
    }

    public List<Promocion> findAll() {
        return em.createQuery("select object(o) from Promocion as o").getResultList();
    }


    public void calculoDescuento(Object id) {
        Promocion promocion;
        promocion = em.find(Promocion.class, id);
        em.refresh(promocion);

        float des;
        float total;
        des = (float) (promocion.getDescuento()* 0.010);
        total= (float)(promocion.getDescuento()+des);
        promocion.setTotalDescuento(total);

    }

}
