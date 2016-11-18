/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import entitis.Roles;
import facade.RolesFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorRoles")
@SessionScoped
public class ControladorRoles implements Serializable{

    /**
     * Creates a new instance of ControladorRoles
     */
    public ControladorRoles() {
    }
    
    private Roles rol;
    
    @EJB
    private RolesFacade facadeRol;
    
    @PostConstruct
    public void init(){
        rol = new Roles();
        
    }
    
    public void registrar(){
        try {
            facadeRol.create(rol);
        } catch (Exception e) {
        }
    }
     public List<Roles> getAll() {
        List<Roles> li = facadeRol.findAll();
        return li;
    }
    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
       public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(facadeRol.findAll(), false);
    }

    public Roles getRoles(java.lang.Integer id) {
        return facadeRol.find(id);
    }

    @FacesConverter(forClass = Roles.class)
    public static class RolConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ControladorRoles controlador = (ControladorRoles) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "controladorRoles");
            return controlador.getRoles(getKey(value));
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
            if (object instanceof Roles) {
                Roles o = (Roles) object;
                return getStringKey(o.getIdRoles());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Roles.class.getName());
            }
        }

    }
}
