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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "monitoreos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitoreos.findAll", query = "SELECT m FROM Monitoreos m"),
    @NamedQuery(name = "Monitoreos.findByIdMonitoreos", query = "SELECT m FROM Monitoreos m WHERE m.idMonitoreos = :idMonitoreos"),
    @NamedQuery(name = "Monitoreos.findByFechaMonitoreo", query = "SELECT m FROM Monitoreos m WHERE m.fechaMonitoreo = :fechaMonitoreo"),
    @NamedQuery(name = "Monitoreos.findByCalificacionMonitoreo", query = "SELECT m FROM Monitoreos m WHERE m.calificacionMonitoreo = :calificacionMonitoreo"),
    @NamedQuery(name = "Monitoreos.findByTranscripcion", query = "SELECT m FROM Monitoreos m WHERE m.transcripcion = :transcripcion")})
public class Monitoreos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMonitoreos")
    private Integer idMonitoreos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaMonitoreo")
    private String fechaMonitoreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calificacionMonitoreo")
    private int calificacionMonitoreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transcripcion")
    private int transcripcion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "recomendacion")
    private String recomendacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonitoreos")
    private List<Feedbacks> feedbacksList;
    @JoinColumn(name = "idAnalistaDeCalidad", referencedColumnName = "idUsuarios")
    @ManyToOne(optional = false)
    private Usuarios idAnalistaDeCalidad;
    @JoinColumn(name = "idAsesor", referencedColumnName = "idUsuarios")
    @ManyToOne(optional = false)
    private Usuarios idAsesor;
    @JoinColumn(name = "idTipoMonitoreo", referencedColumnName = "idTipoMonitoreo")
    @ManyToOne(optional = false)
    private Tipomonitoreo idTipoMonitoreo;
    @JoinColumn(name = "idAtributo", referencedColumnName = "idAtributos")
    @ManyToOne(optional = false)
    private Atributos idAtributo;

    public Monitoreos() {
    }

    public Monitoreos(Integer idMonitoreos) {
        this.idMonitoreos = idMonitoreos;
    }

    public Monitoreos(Integer idMonitoreos, String fechaMonitoreo, int calificacionMonitoreo, int transcripcion) {
        this.idMonitoreos = idMonitoreos;
        this.fechaMonitoreo = fechaMonitoreo;
        this.calificacionMonitoreo = calificacionMonitoreo;
        this.transcripcion = transcripcion;
    }

    public Integer getIdMonitoreos() {
        return idMonitoreos;
    }

    public void setIdMonitoreos(Integer idMonitoreos) {
        this.idMonitoreos = idMonitoreos;
    }

    public String getFechaMonitoreo() {
        return fechaMonitoreo;
    }

    public void setFechaMonitoreo(String fechaMonitoreo) {
        this.fechaMonitoreo = fechaMonitoreo;
    }

    public int getCalificacionMonitoreo() {
        return calificacionMonitoreo;
    }

    public void setCalificacionMonitoreo(int calificacionMonitoreo) {
        this.calificacionMonitoreo = calificacionMonitoreo;
    }

    public int getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(int transcripcion) {
        this.transcripcion = transcripcion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    @XmlTransient
    public List<Feedbacks> getFeedbacksList() {
        return feedbacksList;
    }

    public void setFeedbacksList(List<Feedbacks> feedbacksList) {
        this.feedbacksList = feedbacksList;
    }

    public Usuarios getIdAnalistaDeCalidad() {
        return idAnalistaDeCalidad;
    }

    public void setIdAnalistaDeCalidad(Usuarios idAnalistaDeCalidad) {
        this.idAnalistaDeCalidad = idAnalistaDeCalidad;
    }

    public Usuarios getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Usuarios idAsesor) {
        this.idAsesor = idAsesor;
    }

    public Tipomonitoreo getIdTipoMonitoreo() {
        return idTipoMonitoreo;
    }

    public void setIdTipoMonitoreo(Tipomonitoreo idTipoMonitoreo) {
        this.idTipoMonitoreo = idTipoMonitoreo;
    }

    public Atributos getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(Atributos idAtributo) {
        this.idAtributo = idAtributo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonitoreos != null ? idMonitoreos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitoreos)) {
            return false;
        }
        Monitoreos other = (Monitoreos) object;
        if ((this.idMonitoreos == null && other.idMonitoreos != null) || (this.idMonitoreos != null && !this.idMonitoreos.equals(other.idMonitoreos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Monitoreos[ idMonitoreos=" + idMonitoreos + " ]";
    }
    
}
