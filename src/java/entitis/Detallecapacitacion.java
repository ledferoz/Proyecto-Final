/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitis;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "detallecapacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallecapacitacion.findAll", query = "SELECT d FROM Detallecapacitacion d"),
    @NamedQuery(name = "Detallecapacitacion.findByIdDetalleMonitoreo", query = "SELECT d FROM Detallecapacitacion d WHERE d.idDetalleMonitoreo = :idDetalleMonitoreo"),
    @NamedQuery(name = "Detallecapacitacion.findByComentarios", query = "SELECT d FROM Detallecapacitacion d WHERE d.comentarios = :comentarios"),
    @NamedQuery(name = "Detallecapacitacion.findByFechaCapacitacion", query = "SELECT d FROM Detallecapacitacion d WHERE d.fechaCapacitacion = :fechaCapacitacion")})
public class Detallecapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleMonitoreo")
    private Integer idDetalleMonitoreo;
    @Size(max = 255)
    @Column(name = "Comentarios")
    private String comentarios;
    @Column(name = "fechaCapacitacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCapacitacion;
    @JoinColumn(name = "idCapacitaciones", referencedColumnName = "idCapacitaciones")
    @ManyToOne(optional = false)
    private Capacitaciones idCapacitaciones;
    @JoinColumn(name = "idUsuarios", referencedColumnName = "idUsuarios")
    @ManyToOne(optional = false)
    private Usuarios idUsuarios;

    public Detallecapacitacion() {
    }

    public Detallecapacitacion(Integer idDetalleMonitoreo) {
        this.idDetalleMonitoreo = idDetalleMonitoreo;
    }

    public Integer getIdDetalleMonitoreo() {
        return idDetalleMonitoreo;
    }

    public void setIdDetalleMonitoreo(Integer idDetalleMonitoreo) {
        this.idDetalleMonitoreo = idDetalleMonitoreo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaCapacitacion() {
        return fechaCapacitacion;
    }

    public void setFechaCapacitacion(Date fechaCapacitacion) {
        this.fechaCapacitacion = fechaCapacitacion;
    }

    public Capacitaciones getIdCapacitaciones() {
        return idCapacitaciones;
    }

    public void setIdCapacitaciones(Capacitaciones idCapacitaciones) {
        this.idCapacitaciones = idCapacitaciones;
    }

    public Usuarios getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Usuarios idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleMonitoreo != null ? idDetalleMonitoreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecapacitacion)) {
            return false;
        }
        Detallecapacitacion other = (Detallecapacitacion) object;
        if ((this.idDetalleMonitoreo == null && other.idDetalleMonitoreo != null) || (this.idDetalleMonitoreo != null && !this.idDetalleMonitoreo.equals(other.idDetalleMonitoreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Detallecapacitacion[ idDetalleMonitoreo=" + idDetalleMonitoreo + " ]";
    }
    
}
