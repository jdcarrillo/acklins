/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package licenciapixel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jcarrillo
 */
@Entity
@Table(name = "tienda", catalog = "dblicenciaspixel", schema = "")
@NamedQueries({
    @NamedQuery(name = "Tienda.findAll", query = "SELECT t FROM Tienda t"),
    @NamedQuery(name = "Tienda.findByIdtienda", query = "SELECT t FROM Tienda t WHERE t.idtienda = :idtienda"),
    @NamedQuery(name = "Tienda.findByTienda", query = "SELECT t FROM Tienda t WHERE t.tienda = :tienda"),
    @NamedQuery(name = "Tienda.findByStatus", query = "SELECT t FROM Tienda t WHERE t.status = :status")})
public class Tienda implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtienda")
    private Integer idtienda;
    @Basic(optional = false)
    @Column(name = "tienda")
    private String tienda;
    @Column(name = "status")
    private Boolean status;

    public Tienda() {
    }

    public Tienda(Integer idtienda) {
        this.idtienda = idtienda;
    }

    public Tienda(Integer idtienda, String tienda) {
        this.idtienda = idtienda;
        this.tienda = tienda;
    }

    public Integer getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(Integer idtienda) {
        Integer oldIdtienda = this.idtienda;
        this.idtienda = idtienda;
        changeSupport.firePropertyChange("idtienda", oldIdtienda, idtienda);
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        String oldTienda = this.tienda;
        this.tienda = tienda;
        changeSupport.firePropertyChange("tienda", oldTienda, tienda);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        Boolean oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtienda != null ? idtienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.idtienda == null && other.idtienda != null) || (this.idtienda != null && !this.idtienda.equals(other.idtienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "licenciapixel.Tienda[ idtienda=" + idtienda + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
