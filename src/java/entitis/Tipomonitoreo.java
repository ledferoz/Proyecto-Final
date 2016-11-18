/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitis;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "tipomonitoreo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomonitoreo.findAll", query = "SELECT t FROM Tipomonitoreo t"),
    @NamedQuery(name = "Tipomonitoreo.findByIdTipoMonitoreo", query = "SELECT t FROM Tipomonitoreo t WHERE t.idTipoMonitoreo = :idTipoMonitoreo"),
    @NamedQuery(name = "Tipomonitoreo.findByNombreMonitoreo", query = "SELECT t FROM Tipomonitoreo t WHERE t.nombreMonitoreo = :nombreMonitoreo")})
public class Tipomonitoreo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoMonitoreo")
    private Integer idTipoMonitoreo;
    @Size(max = 25)
    @Column(name = "nombreMonitoreo")
    private String nombreMonitoreo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMonitoreo")
    private List<Monitoreos> monitoreosList;

    public Tipomonitoreo() {
    }

    public Tipomonitoreo(Integer idTipoMonitoreo) {
        this.idTipoMonitoreo = idTipoMonitoreo;
    }

    public Integer getIdTipoMonitoreo() {
        return idTipoMonitoreo;
    }

    public void setIdTipoMonitoreo(Integer idTipoMonitoreo) {
        this.idTipoMonitoreo = idTipoMonitoreo;
    }

    public String getNombreMonitoreo() {
        return nombreMonitoreo;
    }

    public void setNombreMonitoreo(String nombreMonitoreo) {
        this.nombreMonitoreo = nombreMonitoreo;
    }

    @XmlTransient
    public List<Monitoreos> getMonitoreosList() {
        return monitoreosList;
    }

    public void setMonitoreosList(List<Monitoreos> monitoreosList) {
        this.monitoreosList = monitoreosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMonitoreo != null ? idTipoMonitoreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomonitoreo)) {
            return false;
        }
        Tipomonitoreo other = (Tipomonitoreo) object;
        if ((this.idTipoMonitoreo == null && other.idTipoMonitoreo != null) || (this.idTipoMonitoreo != null && !this.idTipoMonitoreo.equals(other.idTipoMonitoreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Tipomonitoreo[ idTipoMonitoreo=" + idTipoMonitoreo + " ]";
    }
    
}
