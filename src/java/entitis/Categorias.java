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
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"),
    @NamedQuery(name = "Categorias.findByIdCategorias", query = "SELECT c FROM Categorias c WHERE c.idCategorias = :idCategorias"),
    @NamedQuery(name = "Categorias.findByNombreCategoria", query = "SELECT c FROM Categorias c WHERE c.nombreCategoria = :nombreCategoria"),
    @NamedQuery(name = "Categorias.findByValorCategoria", query = "SELECT c FROM Categorias c WHERE c.valorCategoria = :valorCategoria")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategorias")
    private Integer idCategorias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreCategoria")
    private String nombreCategoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorCategoria")
    private double valorCategoria;
    @JoinColumn(name = "idPlantillas", referencedColumnName = "idPlantillas")
    @ManyToOne(optional = false)
    private Plantillas idPlantillas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategorias")
    private List<Atributos> atributosList;

    public Categorias() {
    }

    public Categorias(Integer idCategorias) {
        this.idCategorias = idCategorias;
    }

    public Categorias(Integer idCategorias, String nombreCategoria, double valorCategoria) {
        this.idCategorias = idCategorias;
        this.nombreCategoria = nombreCategoria;
        this.valorCategoria = valorCategoria;
    }

    public Integer getIdCategorias() {
        return idCategorias;
    }

    public void setIdCategorias(Integer idCategorias) {
        this.idCategorias = idCategorias;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public double getValorCategoria() {
        return valorCategoria;
    }

    public void setValorCategoria(double valorCategoria) {
        this.valorCategoria = valorCategoria;
    }

    public Plantillas getIdPlantillas() {
        return idPlantillas;
    }

    public void setIdPlantillas(Plantillas idPlantillas) {
        this.idPlantillas = idPlantillas;
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
        hash += (idCategorias != null ? idCategorias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.idCategorias == null && other.idCategorias != null) || (this.idCategorias != null && !this.idCategorias.equals(other.idCategorias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitis.Categorias[ idCategorias=" + idCategorias + " ]";
    }
    
}
