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
@Table(name = "plantillas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plantillas.findAll", query = "SELECT p FROM Plantillas p"),
    @NamedQuery(name = "Plantillas.findByIdPlantillas", query = "SELECT p FROM Plantillas p WHERE p.idPlantillas = :idPlantillas"),
    @NamedQuery(name = "Plantillas.findByNombrePlantilla", query = "SELECT p FROM Plantillas p WHERE p.nombrePlantilla = :nombrePlantilla"),
    @NamedQuery(name = "Plantillas.findByFechaCreacionPlantilla", query = "SELECT p FROM Plantillas p WHERE p.fechaCreacionPlantilla = :fechaCreacionPlantilla"),
    @NamedQuery(name = "Plantillas.findByValorPlantilla", query = "SELECT p FROM Plantillas p WHERE p.valorPlantilla = :valorPlantilla")})
public class Plantillas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlantillas")
    private Integer idPlantillas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombrePlantilla")
    private String nombrePlantilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaCreacionPlantilla")
    private String fechaCreacionPlantilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorPlantilla")
    private double valorPlantilla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlantillas")
    private List<Categorias> categoriasList;

    public Plantillas() {
    }

    public Plantillas(Integer idPlantillas) {
        this.idPlantillas = idPlantillas;
    }

    public Plantillas(Integer idPlantillas, String nombrePlantilla, String fechaCreacionPlantilla, double valorPlantilla) {
        this.idPlantillas = idPlantillas;
        this.nombrePlantilla = nombrePlantilla;
        this.fechaCreacionPlantilla = fechaCreacionPlantilla;
        this.valorPlantilla = valorPlantilla;
    }

    public Integer getIdPlantillas() {
        return idPlantillas;
    }

    public void setIdPlantillas(Integer idPlantillas) {
        this.idPlantillas = idPlantillas;
    }

    public String getNombrePlantilla() {
        return nombrePlantilla;
    }

    public void setNombrePlantilla(String nombrePlantilla) {
        this.nombrePlantilla = nombrePlantilla;
    }

    public String getFechaCreacionPlantilla() {
        return fechaCreacionPlantilla;
    }

    public void setFechaCreacionPlantilla(String fechaCreacionPlantilla) {
        this.fechaCreacionPlantilla = fechaCreacionPlantilla;
    }

    public double getValorPlantilla() {
        return valorPlantilla;
    }

    public void setValorPlantilla(double valorPlantilla) {
        this.valorPlantilla = valorPlantilla;
    }

    @XmlTransient
    public List<Categorias> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(List<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlantillas != null ? idPlantillas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantillas)) {
            return false;
        }
        Plantillas other = (Plantillas) object;
        if ((this.idPlantillas == null && other.idPlantillas != null) || (this.idPlantillas != null && !this.idPlantillas.equals(other.idPlantillas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Plantillas[ idPlantillas=" + idPlantillas + " ]";
    }
    
}
