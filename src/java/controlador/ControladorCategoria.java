/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import entitis.Categorias;
import entitis.Tipoatributo;
import facade.CategoriasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorCategoria")
@SessionScoped
public class ControladorCategoria implements Serializable {

    /**
     * Creates a new instance of ControladorCategoria
     */
    public ControladorCategoria() {
    }
    private Integer estado = 0;
    private Categorias categoria;
    private Integer selectedItemIndex;
    @EJB
    private CategoriasFacade facadeCategoria;
    
    public void estado() {
        this.estado = 0;
    }

    public void borrarEstado() {
        estado = 0;
    }
    
    @PostConstruct
    public void init(){
        categoria = new Categorias();
    }
    public void registrar(){
        try {
            estado = 1;
            facadeCategoria.create(categoria);
        } catch (Exception e) {
            estado = 2;
        }
    }
     public List<Categorias> getAll() {
        List<Categorias> li = facadeCategoria.findAll();
        return li;
    }
       /**
     * EL METODO ELIMINAR SE IMPLEMENTA EL OBJETO DE CREADO DE FACADA SE LE MASA EL METODO REMOVE Y LUEGO NUESTRA ENTIDAD
     */
    public void eliminar() {
        try {
            estado = 3;
            facadeCategoria.remove(categoria);
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
            estado = 5;
            facadeCategoria.edit(categoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Elimino el registro"));
        } catch (Exception e) {
            estado = 6;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " No se pudo eliminar el registro la base de datos"));
        }
    }
    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    
     public Categorias getSelected() {
       if (categoria == null) {
           categoria = new Categorias();
           selectedItemIndex =-1;
       }
       return categoria;
   }
   public SelectItem[] getItemsAvailableSelectOne() {
       return JsfUtil.getSelectItems(facadeCategoria.findAll(),false);
   }
   
    public Categorias getCategorias(java.lang.Integer id) {
       return facadeCategoria.find(id);
   }
   
    @FacesConverter(forClass = Categorias.class)
   public static class CategriaConverter implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladorCategoria controlador = (ControladorCategoria) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorCategoria");
           return controlador.getCategorias(getKey(value));
       }

       java.lang.Integer getKey(String value) {
           java.lang.Integer key;
           key = Integer.valueOf(value);
           return key;
       }

       String getStringKey(java.lang.Integer value) {
           StringBuilder sb = new StringBuilder();
           sb.append(value);
           return sb.toString();
       }

       @Override
       public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
           if (object == null) {
               return null;
           }
           if (object instanceof Categorias) {
              Categorias  o = (Categorias) object;
               return getStringKey(o.getIdCategorias());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Categorias.class.getName());
           }
       }

   }
}
