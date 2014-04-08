/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MAFER
 */
@Entity
@Table(name = "promocion")
@NamedQueries({@NamedQuery(name = "Promocion.findAll", query = "SELECT p FROM Promocion p"), @NamedQuery(name = "Promocion.findByIdpromocion", query = "SELECT p FROM Promocion p WHERE p.idpromocion = :idpromocion"), @NamedQuery(name = "Promocion.findByTipo", query = "SELECT p FROM Promocion p WHERE p.tipo = :tipo"), @NamedQuery(name = "Promocion.findByDescuento", query = "SELECT p FROM Promocion p WHERE p.descuento = :descuento")})
public class Promocion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPROMOCION")
    private Integer idpromocion;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DESCUENTO")
    private Float descuento;
    @Column(name = "TOTALDESCUENTO")
    private Float totaldescuento;
    @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
    @ManyToOne
    private Producto idproducto;

    public Promocion() {
    }

    public Promocion(Integer idpromocion) {
        this.idpromocion = idpromocion;
    }

    public Integer getIdpromocion() {
        return idpromocion;
    }

    public void setIdpromocion(Integer idpromocion) {
        this.idpromocion = idpromocion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

     public Float getTotalDescuento() {
        return totaldescuento;
    }

    public void setTotalDescuento(Float totaldescuento) {
        this.totaldescuento = totaldescuento;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpromocion != null ? idpromocion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promocion)) {
            return false;
        }
        Promocion other = (Promocion) object;
        if ((this.idpromocion == null && other.idpromocion != null) || (this.idpromocion != null && !this.idpromocion.equals(other.idpromocion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Promocion[idpromocion=" + idpromocion + "]";
    }

}
