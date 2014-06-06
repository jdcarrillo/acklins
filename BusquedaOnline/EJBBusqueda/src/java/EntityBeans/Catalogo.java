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
@Table(name = "catalogo")
@NamedQueries({@NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c"), @NamedQuery(name = "Catalogo.findByIdcatalogo", query = "SELECT c FROM Catalogo c WHERE c.idcatalogo = :idcatalogo"), @NamedQuery(name = "Catalogo.findByCategoria", query = "SELECT c FROM Catalogo c WHERE c.categoria = :categoria")})
public class Catalogo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCATALOGO")
    private Integer idcatalogo;
    @Column(name = "CATEGORIA")
    private String categoria;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
    @ManyToOne
    private Cliente idcliente;
    @OneToMany(mappedBy = "idcatalogo")
    private Collection<Producto> productoCollection;

    public Catalogo() {
    }

    public Catalogo(Integer idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    public Integer getIdcatalogo() {
        return idcatalogo;
    }

    public void setIdcatalogo(Integer idcatalogo) {
        this.idcatalogo = idcatalogo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcatalogo != null ? idcatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.idcatalogo == null && other.idcatalogo != null) || (this.idcatalogo != null && !this.idcatalogo.equals(other.idcatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Catalogo[idcatalogo=" + idcatalogo + "]";
    }

}
