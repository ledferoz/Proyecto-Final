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
import javax.persistence.Id;
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
@Table(name = "tipoatributo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoatributo.findAll", query = "SELECT t FROM Tipoatributo t"),
    @NamedQuery(name = "Tipoatributo.findByIdTipoAtributo", query = "SELECT t FROM Tipoatributo t WHERE t.idTipoAtributo = :idTipoAtributo"),
    @NamedQuery(name = "Tipoatributo.findByNombreTipoAtributo", query = "SELECT t FROM Tipoatributo t WHERE t.nombreTipoAtributo = :nombreTipoAtributo")})
public class Tipoatributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTipoAtributo")
    private Integer idTipoAtributo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombreTipoAtributo")
    private String nombreTipoAtributo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoAtributo")
    private List<Atributos> atributosList;

    public Tipoatributo() {
    }

    public Tipoatributo(Integer idTipoAtributo) {
        this.idTipoAtributo = idTipoAtributo;
    }

    public Tipoatributo(Integer idTipoAtributo, String nombreTipoAtributo) {
        this.idTipoAtributo = idTipoAtributo;
        this.nombreTipoAtributo = nombreTipoAtributo;
    }

    public Integer getIdTipoAtributo() {
        return idTipoAtributo;
    }

    public void setIdTipoAtributo(Integer idTipoAtributo) {
        this.idTipoAtributo = idTipoAtributo;
    }

    public String getNombreTipoAtributo() {
        return nombreTipoAtributo;
    }

    public void setNombreTipoAtributo(String nombreTipoAtributo) {
        this.nombreTipoAtributo = nombreTipoAtributo;
    }

    @XmlTransient
    public List<Atributos> getAtributosList() {
        return atributosList;
    }

    public void setAtributosList(List<Atributos> atributosList) {
        this.atributosList = atributosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAtributo != null ? idTipoAtributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoatributo)) {
            return false;
        }
        Tipoatributo other = (Tipoatributo) object;
        if ((this.idTipoAtributo == null && other.idTipoAtributo != null) || (this.idTipoAtributo != null && !this.idTipoAtributo.equals(other.idTipoAtributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Tipoatributo[ idTipoAtributo=" + idTipoAtributo + " ]";
    }
    
}
