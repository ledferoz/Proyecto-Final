/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import controlador.util.JsfUtil;
import entitis.Plantillas;
import facade.PlantillasFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;


/**
 *
 * @author Edgar Eduardo Gonzalez 
 */
@Named(value = "controladorPlantilla")
@SessionScoped
public class ControladorPlantilla implements Serializable{

    /**
     * Creates a new instance of ControladorPlantilla
     */
    public ControladorPlantilla() {
    }
    /**
     * El EJB SIRVE PARA INICIAR EL FACADE SIEMPRE SE DEBE UTLIZAR
     */
    
    private int estado = 0;
    private int selectedItemIndex;
    @EJB
    private PlantillasFacade plantillasFacade;
    /**
     * PLANTILLA SE LLAMA LA ENTIDAD Y SE CREA UN OBJETO DE PLANTILLA
     */
    Plantillas plantilla;

    /**
     * EN EL POSTCONSTRUCT ESTAMOS DICIENDO QUE PLANTILLA ES IGUAL A UNA NUEVA PLANTILLA ESTE METODO ES MUY IMPORTANTE
     */
    @PostConstruct
    public void init() {
        plantilla = new Plantillas();   
    }
    public void estado() {
        this.estado = 0;
    }

    public void borrarEstado() {
        estado = 0;
    }
     
    /**
     * EL METODO DE REGISTRAR SE EN CUENTRA UN TRY CAYTCH EN EL CUAL SE LE PASA LA PLANTILLA FACADE Y LUEGO
     * SE IMPLEMENTA EL METODO CREATE DE LA FACHADA Y POR ULTIMO SE LE ASIGNA LA ENTIDAD QUE VAMOS A PERSISTIR
     */
    public void registar() {
        try {
            estado = 1;
            plantillasFacade.create(plantilla);
        } catch (Exception e) {
            estado = 2;
        }
    }

    
    /**
     * METODO DE ENLISTAR EN ESTE METODO CREAMOS UNA LISTA Y LEPASAMOS NUESTRA ENTIDAD
     * LUEGO ULTILISAMOS EL FACADA Y EL METODO FINDALL PARA ENLISTAR TODOS NUESTROS USUARIOS
     * @return
     */
    public List<Plantillas> getAll() {
        List<Plantillas> li = plantillasFacade.findAll();
        return li;
    }

    /**
     * EL METODO ELIMINAR SE IMPLEMENTA EL OBJETO DE CREADO DE FACADA SE LE MASA EL METODO REMOVE Y LUEGO NUESTRA ENTIDAD
     */
    public void eliminar() {
        try {
            estado = 3;
            plantillasFacade.remove(plantilla);
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
            plantillasFacade.edit(plantilla);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Elimino el registro"));
        } catch (Exception e) {
            estado = 6;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " No se pudo eliminar el registro la base de datos"));
        }
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public Plantillas getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Plantillas plantilla) {
        this.plantilla = plantilla;
    }
    
   public Plantillas getSelected() {
       if (plantilla == null) {
           plantilla = new Plantillas();
           selectedItemIndex =-1;
       }
       return plantilla;
   }
   public SelectItem[] getItemsAvailableSelectOne() {
       return JsfUtil.getSelectItems(plantillasFacade.findAll(),false);
   }
   
    public Plantillas getPlantillas(java.lang.Integer id) {
       return plantillasFacade.find(id);
   }
   
    @FacesConverter(forClass = Plantillas.class)
   public static class PlantillaConvertidor implements Converter {

       @Override
       public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ControladorPlantilla controlador = (ControladorPlantilla) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "controladorPlantilla");
           return controlador.getPlantillas(getKey(value));
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
           if (object instanceof Plantillas) {
               Plantillas o = (Plantillas) object;
               return getStringKey(o.getIdPlantillas());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Plantillas.class.getName());
           }
       }

   }

   
}

