/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import entitis.Tipoatributo;
import facade.TipoatributoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorTipoAtributo")
@SessionScoped
public class ControladorTipoAtributo implements Serializable {

    /**
     * Creates a new instance of ControladorTipoAtributo
     */
    public ControladorTipoAtributo() {
    }
      private int selectedItemIndex;
    //Se declara el objeto de la entidad
    private Tipoatributo tipoAtributo;
    
    //Se utiliza la anotacion EJB de javax.ejb
    //Se crea el objeto deL Facde
    @EJB
    private TipoatributoFacade facadeTipoAtributo;
    
    //Se crea el ponsconstructor para la entidad
    @PostConstruct
    public void init(){
        //Se declara el objeto de la entidad y se le da en valor de new Entidad
        tipoAtributo = new Tipoatributo();
        
    }
    //Creo el metodo de registar
    public void registrar(){
        try {
            //Se llama el objeto de facade y se le pasa el create y dentro del create se le pasa el objeto de la entidad
            facadeTipoAtributo.create(tipoAtributo);
        } catch (Exception e) {
        }
    }
      public List<Tipoatributo> getAll() {
        List<Tipoatributo> li = facadeTipoAtributo.findAll();
        return li;
    }
    public Tipoatributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(Tipoatributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }
    
     public Tipoatributo getSelected() {
       if (tipoAtributo == null) {
           tipoAtributo = new Tipoatributo();
           selectedItemIndex =-1;
       }
       return tipoAtributo;
   }
   public SelectItem[] getItemsAvailableSelectOne() {
       return JsfUtil.getSelectItems(facadeTipoAtributo.findAll(),false);
   }
   
    public Tipoatributo getTipoatributo(java.lang.Integer id) {
       return facadeTipoAtributo.find(id);
   }
   
    @FacesConverter(forClass = Tipoatributo.class)
   public static class TipoAtributoConverter implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladorTipoAtributo controlador = (ControladorTipoAtributo) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorTipoAtributo");
           return controlador.getTipoatributo(getKey(value));
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
           if (object instanceof Tipoatributo) {
              Tipoatributo  o = (Tipoatributo) object;
               return getStringKey(o.getIdTipoAtributo());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Tipoatributo.class.getName());
           }
       }

   }
}
