/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import entitis.Monitoreos;
import facade.MonitoreosFacade;
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
@Named(value = "controladorMonitoreo")
@SessionScoped
public class ControladorMonitoreo implements Serializable {

    /**
     * Creates a new instance of ControladorMonitoreo
     */
    public ControladorMonitoreo() {
    }
    private int selectedItemIndex;
    private Monitoreos monitoreo;
    
    @EJB
    private MonitoreosFacade facadeMonitoreo;
    
    
    @PostConstruct
    public void init (){
        monitoreo = new Monitoreos();
        
    }
    
    
    public void registrar(){
        try {
            facadeMonitoreo.create(monitoreo);
        } catch (Exception e) {
        }
    }
     public List<Monitoreos> getAll() {
        List<Monitoreos> li = facadeMonitoreo.findAll();
        return li;
    }
     
     
     
    public Monitoreos getSelected() {
       if (monitoreo == null) {
           monitoreo = new Monitoreos();
           selectedItemIndex =-1;
       }
       return monitoreo;
   }
   public SelectItem[] getItemsAvailableSelectOne() {
       return JsfUtil.getSelectItems(facadeMonitoreo.findAll(),false);
   }
   
    public Monitoreos getMonitoreos(java.lang.Integer id) {
       return facadeMonitoreo.find(id);
   }
   
    @FacesConverter(forClass = Monitoreos.class)
   public static class MonitoreoConvertidor implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladorMonitoreo controlador = (ControladorMonitoreo) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorMonitoreo");
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
           if (object instanceof Monitoreos) {
               Monitoreos o = (Monitoreos) object;
               return getStringKey(o.getIdMonitoreos());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Monitoreos.class.getName());
           }
       }

   }

    public ControladorMonitoreo(int selectedItemIndex, Monitoreos monitoreo, MonitoreosFacade facadeMonitoreo) {
        this.selectedItemIndex = selectedItemIndex;
        this.monitoreo = monitoreo;
        this.facadeMonitoreo = facadeMonitoreo;
    }
    
    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Monitoreos getMonitoreo() {
        return monitoreo;
    }

    public void setMonitoreo(Monitoreos monitoreo) {
        this.monitoreo = monitoreo;
    }

    public MonitoreosFacade getFacadeMonitoreo() {
        return facadeMonitoreo;
    }

    public void setFacadeMonitoreo(MonitoreosFacade facadeMonitoreo) {
        this.facadeMonitoreo = facadeMonitoreo;
    }
    
}
