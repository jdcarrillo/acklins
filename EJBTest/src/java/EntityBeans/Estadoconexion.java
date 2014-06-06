/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "estadoconexion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadoconexion.findAll", query = "SELECT e FROM Estadoconexion e"),
    @NamedQuery(name = "Estadoconexion.findByEstadoConex", query = "SELECT e FROM Estadoconexion e WHERE e.estadoConex = :estadoConex"),
    @NamedQuery(name = "Estadoconexion.findByFecha", query = "SELECT e FROM Estadoconexion e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "Estadoconexion.findByIdEstadoConex", query = "SELECT e FROM Estadoconexion e WHERE e.idEstadoConex = :idEstadoConex")})
public class Estadoconexion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estadoConex")
    private boolean estadoConex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoConex")
    private Integer idEstadoConex;
    @JoinColumn(name = "idTienda", referencedColumnName = "idtienda")
    @ManyToOne(optional = false)
    private Tienda idTienda;

    public Estadoconexion() {
    }

    public Estadoconexion(Integer idEstadoConex) {
        this.idEstadoConex = idEstadoConex;
    }

    public Estadoconexion(Integer idEstadoConex, boolean estadoConex, Date fecha) {
        this.idEstadoConex = idEstadoConex;
        this.estadoConex = estadoConex;
        this.fecha = fecha;
    }

    public boolean getEstadoConex() {
        return estadoConex;
    }

    public void setEstadoConex(boolean estadoConex) {
        this.estadoConex = estadoConex;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdEstadoConex() {
        return idEstadoConex;
    }

    public void setIdEstadoConex(Integer idEstadoConex) {
        this.idEstadoConex = idEstadoConex;
    }

    public Tienda getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Tienda idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoConex != null ? idEstadoConex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadoconexion)) {
            return false;
        }
        Estadoconexion other = (Estadoconexion) object;
        if ((this.idEstadoConex == null && other.idEstadoConex != null) || (this.idEstadoConex != null && !this.idEstadoConex.equals(other.idEstadoConex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Estadoconexion[ idEstadoConex=" + idEstadoConex + " ]";
    }
    
}
