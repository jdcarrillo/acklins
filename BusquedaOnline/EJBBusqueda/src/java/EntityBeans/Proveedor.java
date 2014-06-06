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
@Table(name = "proveedor")
@NamedQueries({@NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"), @NamedQuery(name = "Proveedor.findByIdproveedor", query = "SELECT p FROM Proveedor p WHERE p.idproveedor = :idproveedor"), @NamedQuery(name = "Proveedor.findByNombreproveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreproveedor = :nombreproveedor"), @NamedQuery(name = "Proveedor.findByDireccionproveedor", query = "SELECT p FROM Proveedor p WHERE p.direccionproveedor = :direccionproveedor"), @NamedQuery(name = "Proveedor.findByCiudadproveedor", query = "SELECT p FROM Proveedor p WHERE p.ciudadproveedor = :ciudadproveedor"), @NamedQuery(name = "Proveedor.findByTelefonoproveedor", query = "SELECT p FROM Proveedor p WHERE p.telefonoproveedor = :telefonoproveedor"), @NamedQuery(name = "Proveedor.findByEmailproveedor", query = "SELECT p FROM Proveedor p WHERE p.emailproveedor = :emailproveedor")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPROVEEDOR")
    private Integer idproveedor;
    @Column(name = "NOMBREPROVEEDOR")
    private String nombreproveedor;
    @Column(name = "DIRECCIONPROVEEDOR")
    private String direccionproveedor;
    @Column(name = "CIUDADPROVEEDOR")
    private String ciudadproveedor;
    @Column(name = "TELEFONOPROVEEDOR")
    private String telefonoproveedor;
    @Column(name = "EMAILPROVEEDOR")
    private String emailproveedor;
    @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
    @ManyToOne
    private Producto idproducto;

    public Proveedor() {
    }

    public Proveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombreproveedor() {
        return nombreproveedor;
    }

    public void setNombreproveedor(String nombreproveedor) {
        this.nombreproveedor = nombreproveedor;
    }

    public String getDireccionproveedor() {
        return direccionproveedor;
    }

    public void setDireccionproveedor(String direccionproveedor) {
        this.direccionproveedor = direccionproveedor;
    }

    public String getCiudadproveedor() {
        return ciudadproveedor;
    }

    public void setCiudadproveedor(String ciudadproveedor) {
        this.ciudadproveedor = ciudadproveedor;
    }

    public String getTelefonoproveedor() {
        return telefonoproveedor;
    }

    public void setTelefonoproveedor(String telefonoproveedor) {
        this.telefonoproveedor = telefonoproveedor;
    }

    public String getEmailproveedor() {
        return emailproveedor;
    }

    public void setEmailproveedor(String emailproveedor) {
        this.emailproveedor = emailproveedor;
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
        hash += (idproveedor != null ? idproveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idproveedor == null && other.idproveedor != null) || (this.idproveedor != null && !this.idproveedor.equals(other.idproveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Proveedor[idproveedor=" + idproveedor + "]";
    }

}
