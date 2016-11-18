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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "feedbacks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedbacks.findAll", query = "SELECT f FROM Feedbacks f"),
    @NamedQuery(name = "Feedbacks.findByIdFeedbacks", query = "SELECT f FROM Feedbacks f WHERE f.idFeedbacks = :idFeedbacks"),
    @NamedQuery(name = "Feedbacks.findByFechaFeedback", query = "SELECT f FROM Feedbacks f WHERE f.fechaFeedback = :fechaFeedback")})
public class Feedbacks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFeedbacks")
    private Integer idFeedbacks;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaFeedback")
    private String fechaFeedback;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "recomendacion")
    private String recomendacion;
    @JoinColumn(name = "idMonitoreos", referencedColumnName = "idMonitoreos")
    @ManyToOne(optional = false)
    private Monitoreos idMonitoreos;

    public Feedbacks() {
    }

    public Feedbacks(Integer idFeedbacks) {
        this.idFeedbacks = idFeedbacks;
    }

    public Feedbacks(Integer idFeedbacks, String fechaFeedback, String recomendacion) {
        this.idFeedbacks = idFeedbacks;
        this.fechaFeedback = fechaFeedback;
        this.recomendacion = recomendacion;
    }

    public Integer getIdFeedbacks() {
        return idFeedbacks;
    }

    public void setIdFeedbacks(Integer idFeedbacks) {
        this.idFeedbacks = idFeedbacks;
    }

    public String getFechaFeedback() {
        return fechaFeedback;
    }

    public void setFechaFeedback(String fechaFeedback) {
        this.fechaFeedback = fechaFeedback;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public Monitoreos getIdMonitoreos() {
        return idMonitoreos;
    }

    public void setIdMonitoreos(Monitoreos idMonitoreos) {
        this.idMonitoreos = idMonitoreos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFeedbacks != null ? idFeedbacks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedbacks)) {
            return false;
        }
        Feedbacks other = (Feedbacks) object;
        if ((this.idFeedbacks == null && other.idFeedbacks != null) || (this.idFeedbacks != null && !this.idFeedbacks.equals(other.idFeedbacks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Feedbacks[ idFeedbacks=" + idFeedbacks + " ]";
    }
    
}
