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
@Table(name = "multimedia")
@NamedQueries({@NamedQuery(name = "Multimedia.findAll", query = "SELECT m FROM Multimedia m"), @NamedQuery(name = "Multimedia.findByIdmultimedia", query = "SELECT m FROM Multimedia m WHERE m.idmultimedia = :idmultimedia"), @NamedQuery(name = "Multimedia.findByTipocontenido", query = "SELECT m FROM Multimedia m WHERE m.tipocontenido = :tipocontenido"), @NamedQuery(name = "Multimedia.findByDescripcion", query = "SELECT m FROM Multimedia m WHERE m.descripcion = :descripcion"), @NamedQuery(name = "Multimedia.findByApi", query = "SELECT m FROM Multimedia m WHERE m.api = :api")})
public class Multimedia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDMULTIMEDIA")
    private Integer idmultimedia;
    @Column(name = "TIPOCONTENIDO")
    private String tipocontenido;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "API")
    private String api;
    @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
    @ManyToOne
    private Producto idproducto;

    public Multimedia() {
    }

    public Multimedia(Integer idmultimedia) {
        this.idmultimedia = idmultimedia;
    }

    public Integer getIdmultimedia() {
        return idmultimedia;
    }

    public void setIdmultimedia(Integer idmultimedia) {
        this.idmultimedia = idmultimedia;
    }

    public String getTipocontenido() {
        return tipocontenido;
    }

    public void setTipocontenido(String tipocontenido) {
        this.tipocontenido = tipocontenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
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
        hash += (idmultimedia != null ? idmultimedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multimedia)) {
            return false;
        }
        Multimedia other = (Multimedia) object;
        if ((this.idmultimedia == null && other.idmultimedia != null) || (this.idmultimedia != null && !this.idmultimedia.equals(other.idmultimedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Multimedia[idmultimedia=" + idmultimedia + "]";
    }

}
