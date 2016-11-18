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
import javax.persistence.Lob;
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
@Table(name = "capacitaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Capacitaciones.findAll", query = "SELECT c FROM Capacitaciones c"),
    @NamedQuery(name = "Capacitaciones.findByIdCapacitaciones", query = "SELECT c FROM Capacitaciones c WHERE c.idCapacitaciones = :idCapacitaciones"),
    @NamedQuery(name = "Capacitaciones.findByDescripcion", query = "SELECT c FROM Capacitaciones c WHERE c.descripcion = :descripcion")})
public class Capacitaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCapacitaciones")
    private Integer idCapacitaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "justificacion")
    private String justificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCapacitaciones")
    private List<Detallecapacitacion> detallecapacitacionList;

    public Capacitaciones() {
    }

    public Capacitaciones(Integer idCapacitaciones) {
        this.idCapacitaciones = idCapacitaciones;
    }

    public Capacitaciones(Integer idCapacitaciones, String descripcion, String justificacion) {
        this.idCapacitaciones = idCapacitaciones;
        this.descripcion = descripcion;
        this.justificacion = justificacion;
    }

    public Integer getIdCapacitaciones() {
        return idCapacitaciones;
    }

    public void setIdCapacitaciones(Integer idCapacitaciones) {
        this.idCapacitaciones = idCapacitaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    @XmlTransient
    public List<Detallecapacitacion> getDetallecapacitacionList() {
        return detallecapacitacionList;
    }

    public void setDetallecapacitacionList(List<Detallecapacitacion> detallecapacitacionList) {
        this.detallecapacitacionList = detallecapacitacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitaciones != null ? idCapacitaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitaciones)) {
            return false;
        }
        Capacitaciones other = (Capacitaciones) object;
        if ((this.idCapacitaciones == null && other.idCapacitaciones != null) || (this.idCapacitaciones != null && !this.idCapacitaciones.equals(other.idCapacitaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Capacitaciones[ idCapacitaciones=" + idCapacitaciones + " ]";
    }
    
}
