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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MAFER
 */
@Entity
@Table(name = "cliente")
@NamedQueries({@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"), @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"), @NamedQuery(name = "Cliente.findByNombrecliente", query = "SELECT c FROM Cliente c WHERE c.nombrecliente = :nombrecliente"), @NamedQuery(name = "Cliente.findByApellidocliente", query = "SELECT c FROM Cliente c WHERE c.apellidocliente = :apellidocliente"), @NamedQuery(name = "Cliente.findByCiudadcliente", query = "SELECT c FROM Cliente c WHERE c.ciudadcliente = :ciudadcliente"), @NamedQuery(name = "Cliente.findByDireccioncliente", query = "SELECT c FROM Cliente c WHERE c.direccioncliente = :direccioncliente"), @NamedQuery(name = "Cliente.findByTelefonocliente", query = "SELECT c FROM Cliente c WHERE c.telefonocliente = :telefonocliente"), @NamedQuery(name = "Cliente.findByEmailcliente", query = "SELECT c FROM Cliente c WHERE c.emailcliente = :emailcliente"), @NamedQuery(name = "Cliente.findByLogin", query = "SELECT c FROM Cliente c WHERE c.login = :login"), @NamedQuery(name = "Cliente.findByPassword", query = "SELECT c FROM Cliente c WHERE c.password = :password")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCLIENTE")
    private Integer idcliente;
    @Column(name = "NOMBRECLIENTE")
    private String nombrecliente;
    @Column(name = "APELLIDOCLIENTE")
    private String apellidocliente;
    @Column(name = "CIUDADCLIENTE")
    private String ciudadcliente;
    @Column(name = "DIRECCIONCLIENTE")
    private String direccioncliente;
    @Column(name = "TELEFONOCLIENTE")
    private String telefonocliente;
    @Column(name = "EMAILCLIENTE")
    private String emailcliente;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "idcliente")
    private Collection<Catalogo> catalogoCollection;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getApellidocliente() {
        return apellidocliente;
    }

    public void setApellidocliente(String apellidocliente) {
        this.apellidocliente = apellidocliente;
    }

    public String getCiudadcliente() {
        return ciudadcliente;
    }

    public void setCiudadcliente(String ciudadcliente) {
        this.ciudadcliente = ciudadcliente;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }

    public String getTelefonocliente() {
        return telefonocliente;
    }

    public void setTelefonocliente(String telefonocliente) {
        this.telefonocliente = telefonocliente;
    }

    public String getEmailcliente() {
        return emailcliente;
    }

    public void setEmailcliente(String emailcliente) {
        this.emailcliente = emailcliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Catalogo> getCatalogoCollection() {
        return catalogoCollection;
    }

    public void setCatalogoCollection(Collection<Catalogo> catalogoCollection) {
        this.catalogoCollection = catalogoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityBeans.Cliente[idcliente=" + idcliente + "]";
    }

}
