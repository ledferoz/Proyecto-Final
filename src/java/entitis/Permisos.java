/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitis;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p"),
    @NamedQuery(name = "Permisos.findByIdPermisos", query = "SELECT p FROM Permisos p WHERE p.idPermisos = :idPermisos"),
    @NamedQuery(name = "Permisos.findByNombrePermisos", query = "SELECT p FROM Permisos p WHERE p.nombrePermisos = :nombrePermisos"),
    @NamedQuery(name = "Permisos.findByTipo", query = "SELECT p FROM Permisos p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Permisos.findByEstdo", query = "SELECT p FROM Permisos p WHERE p.estdo = :estdo"),
    @NamedQuery(name = "Permisos.findByUrl", query = "SELECT p FROM Permisos p WHERE p.url = :url")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPermisos")
    private Integer idPermisos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombrePermisos")
    private String nombrePermisos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estdo")
    private boolean estdo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "url")
    private String url;
    @JoinTable(name = "rolespermisos", joinColumns = {
        @JoinColumn(name = "idPermisos", referencedColumnName = "idPermisos")}, inverseJoinColumns = {
        @JoinColumn(name = "idRoles", referencedColumnName = "idRoles")})
    @ManyToMany
    private List<Roles> rolesList;
    @OneToMany(mappedBy = "codigoSubmenu")
    private List<Permisos> permisosList;
    @JoinColumn(name = "codigoSubmenu", referencedColumnName = "idPermisos")
    @ManyToOne
    private Permisos codigoSubmenu;

    public Permisos() {
    }

    public Permisos(Integer idPermisos) {
        this.idPermisos = idPermisos;
    }

    public Permisos(Integer idPermisos, String nombrePermisos, String tipo, boolean estdo, String url) {
        this.idPermisos = idPermisos;
        this.nombrePermisos = nombrePermisos;
        this.tipo = tipo;
        this.estdo = estdo;
        this.url = url;
    }

    public Integer getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(Integer idPermisos) {
        this.idPermisos = idPermisos;
    }

    public String getNombrePermisos() {
        return nombrePermisos;
    }

    public void setNombrePermisos(String nombrePermisos) {
        this.nombrePermisos = nombrePermisos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getEstdo() {
        return estdo;
    }

    public void setEstdo(boolean estdo) {
        this.estdo = estdo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @XmlTransient
    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

    public Permisos getCodigoSubmenu() {
        return codigoSubmenu;
    }

    public void setCodigoSubmenu(Permisos codigoSubmenu) {
        this.codigoSubmenu = codigoSubmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisos != null ? idPermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idPermisos == null && other.idPermisos != null) || (this.idPermisos != null && !this.idPermisos.equals(other.idPermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Permisos[ idPermisos=" + idPermisos + " ]";
    }
    
}
