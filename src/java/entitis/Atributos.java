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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "atributos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atributos.findAll", query = "SELECT a FROM Atributos a"),
    @NamedQuery(name = "Atributos.findByIdAtributos", query = "SELECT a FROM Atributos a WHERE a.idAtributos = :idAtributos"),
    @NamedQuery(name = "Atributos.findByValorAtributo", query = "SELECT a FROM Atributos a WHERE a.valorAtributo = :valorAtributo")})
public class Atributos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtributos")
    private Integer idAtributos;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombreAtributo")
    private String nombreAtributo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorAtributo")
    private double valorAtributo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAtributo")
    private List<Monitoreos> monitoreosList;
    @JoinColumn(name = "idCategorias", referencedColumnName = "idCategorias")
    @ManyToOne(optional = false)
    private Categorias idCategorias;
    @JoinColumn(name = "idTipoAtributo", referencedColumnName = "idTipoAtributo")
    @ManyToOne(optional = false)
    private Tipoatributo idTipoAtributo;

    public Atributos() {
    }

    public Atributos(Integer idAtributos) {
        this.idAtributos = idAtributos;
    }

    public Atributos(Integer idAtributos, String nombreAtributo, double valorAtributo) {
        this.idAtributos = idAtributos;
        this.nombreAtributo = nombreAtributo;
        this.valorAtributo = valorAtributo;
    }

    public Integer getIdAtributos() {
        return idAtributos;
    }

    public void setIdAtributos(Integer idAtributos) {
        this.idAtributos = idAtributos;
    }

    public String getNombreAtributo() {
        return nombreAtributo;
    }

    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    public double getValorAtributo() {
        return valorAtributo;
    }

    public void setValorAtributo(double valorAtributo) {
        this.valorAtributo = valorAtributo;
    }

    @XmlTransient
    public List<Monitoreos> getMonitoreosList() {
        return monitoreosList;
    }

    public void setMonitoreosList(List<Monitoreos> monitoreosList) {
        this.monitoreosList = monitoreosList;
    }

    public Categorias getIdCategorias() {
        return idCategorias;
    }

    public void setIdCategorias(Categorias idCategorias) {
        this.idCategorias = idCategorias;
    }

    public Tipoatributo getIdTipoAtributo() {
        return idTipoAtributo;
    }

    public void setIdTipoAtributo(Tipoatributo idTipoAtributo) {
        this.idTipoAtributo = idTipoAtributo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtributos != null ? idAtributos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atributos)) {
            return false;
        }
        Atributos other = (Atributos) object;
        if ((this.idAtributos == null && other.idAtributos != null) || (this.idAtributos != null && !this.idAtributos.equals(other.idAtributos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Atributos[ idAtributos=" + idAtributos + " ]";
    }
    
}
