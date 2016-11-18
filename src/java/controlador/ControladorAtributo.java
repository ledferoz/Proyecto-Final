/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entitis.Atributos;
import facade.AtributosFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorAtributo")
@RequestScoped
public class ControladorAtributo {

    /**
     * Creates a new instance of ControladorAtributo
     */
    public ControladorAtributo() {
    }
    private Integer estado = 0;
    private Atributos atributo;
    
    @EJB
    private AtributosFacade facadeAtributo;
    
    @PostConstruct
    public void init(){
        atributo = new Atributos();
        
    }
    public void estado() {
        this.estado = 0;
    }

    public void borrarEstado() {
        estado = 0;
    }
    public void registrar(){
        try {
            facadeAtributo.create(atributo);
            estado = 1;
        } catch (Exception e) {
            estado = 2;
        }
        
        
    }
      public List<Atributos> getAll() {
        List<Atributos> li = facadeAtributo.findAll();
        return li;
    }
        public void eliminar() {
        try {
            facadeAtributo.remove(atributo);
            estado = 3;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Elimino el registro"));
        } catch (Exception e) {
            estado = 4;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " No se pudo eliminar el registro la base de datos"));
        }
    }

    /**
     * EN EL DE MODIFICAR IGUAL MENTE SE LE PASA EL OBJETO DE FACADE Y  LUEGO SE UTILIZA EL METODO DE EDIT
     * SE PASA EL OBJETO DE LA ENTIDAD
     */
    public void modificar() {
        try {
            facadeAtributo.edit(atributo);
            estado = 5;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Elimino el registro"));
        } catch (Exception e) {
            estado = 6;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " No se pudo eliminar el registro la base de datos"));
        }
    }
    public Atributos getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributos atributo) {
        this.atributo = atributo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    
    
    
}
