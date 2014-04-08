/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityBeans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MAFER
 */
@Entity
@Table(name = "producto")
@NamedQueries({@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"), @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto"), @NamedQuery(name = "Producto.findByMarca", query = "SELECT p FROM Producto p WHERE p.marca = :marca"), @NamedQuery(name = "Producto.findByGarantia", query = "SELECT p FROM Producto p WHERE p.garantia = :garantia"), @NamedQuery(name = "Producto.findByNombreproducto", query = "SELECT p FROM Producto p WHERE p.nombreproducto = :nombreproducto"), @NamedQuery(name = "Producto.findByPreciosiniva", query = "SELECT p FROM Producto p WHERE p.preciosiniva = :preciosiniva"), @NamedQuery(name = "Producto.findByPrecioconiva", query = "SELECT p FROM Producto p WHERE p.precioconiva = :precioconiva")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPRODUCTO")
    private Integer idproducto;
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "GARANTIA")
    private String garantia;
    @Column(name = "NOMBREPRODUCTO")
    private String nombreproducto;
    @Column(name = "PRECIOSINIVA")
    private Float preciosiniva;
    @Column(name = "PRECIOCONIVA")
    private Float precioconiva;
    @OneToMany(mappedBy = "idproducto")
    private Collection<Promocion> promocionCollection;
    @JoinColumn(name = "IDCATALOGO", referencedColumnName = "IDCATALOGO")
    @ManyToOne
    private Catalogo idcatalogo;
    @OneToMany(mappedBy = "idproducto")
    private Collection<Proveedor> proveedorCollection;

    public Producto() {
    }

    public Producto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Float getPreciosiniva() {
        return preciosiniva;
    }

    public void setPreciosiniva(Float preciosiniva) {
        this.preciosiniva = preciosiniva;
    }

    public Float getPrecioconiva() {
        return precioconiva;
    }

    public void setPrecioconiva(Float precioconiva) {
        this.precioconiva = precioconiva;
    }

    public Collection<Promocion> getPromocionCollection() {
        return promocionCollection;
    }

    public void setPromocionCollection(Collection<Promocion> promocionCollection) {
        this.promocionCollection = promocionCollection;
    }

    public Catalogo getIdcatalogo() {
        return idcatalogo;
    }

    public void setIdcatalogo(Catalogo idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }

    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Producto[idproducto=" + idproducto + "]";
    }

}
