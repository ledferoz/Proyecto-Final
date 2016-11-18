/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import entitis.Tipomonitoreo;
import facade.TipomonitoreoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorTipoMonitoreo")
@SessionScoped
public class ControladorTipoMonitoreo implements Serializable{

    /**
     * Creates a new instance of ControladorTipoMonitoreo
     */
    public ControladorTipoMonitoreo() {
    }
    private Integer selectedItemIndex;
    private Tipomonitoreo tipoMonitoreo;
  
    @EJB
    private TipomonitoreoFacade facadeTipoMonitoreo;
    
    
     public List<Tipomonitoreo> getAll() {
        List<Tipomonitoreo> li = facadeTipoMonitoreo.findAll();
        return li;
    }
     public Tipomonitoreo getSelected() {
       if (tipoMonitoreo == null) {
           tipoMonitoreo = new Tipomonitoreo();
           selectedItemIndex =-1;
       }
       return tipoMonitoreo;
   }
   public SelectItem[] getItemsAvailableSelectOne() {
       return JsfUtil.getSelectItems(facadeTipoMonitoreo.findAll(),false);
   }
   
    public Tipomonitoreo getMonitoreos(java.lang.Integer id) {
       return facadeTipoMonitoreo.find(id);
   }
   
    @FacesConverter(forClass = Tipomonitoreo.class)
   public static class TipoMonitoreConvertidor implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladorMonitoreo controlador = (ControladorMonitoreo) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorTipoMonitoreo");
           return controlador.getMonitoreos(getKey(value));
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
           if (object instanceof Tipomonitoreo) {
               Tipomonitoreo o = (Tipomonitoreo) object;
               return getStringKey(o.getIdTipoMonitoreo());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Tipomonitoreo.class.getName());
           }
       }

   }

    public ControladorTipoMonitoreo(Integer selectedItemIndex, Tipomonitoreo tipoMonitoreo, TipomonitoreoFacade facadeTipoMonitoreo) {
        this.selectedItemIndex = selectedItemIndex;
        this.tipoMonitoreo = tipoMonitoreo;
        this.facadeTipoMonitoreo = facadeTipoMonitoreo;
    }
    
    public Integer getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(Integer selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Tipomonitoreo getTipoMonitoreo() {
        return tipoMonitoreo;
    }

    public void setTipoMonitoreo(Tipomonitoreo tipoMonitoreo) {
        this.tipoMonitoreo = tipoMonitoreo;
    }

    public TipomonitoreoFacade getFacadeTipoMonitoreo() {
        return facadeTipoMonitoreo;
    }

    public void setFacadeTipoMonitoreo(TipomonitoreoFacade facadeTipoMonitoreo) {
        this.facadeTipoMonitoreo = facadeTipoMonitoreo;
    }
    
    
}
