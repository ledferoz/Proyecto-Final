/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.Email;
import controlador.util.Funcionalidades;
import controlador.util.JsfUtil;
import entitis.Usuarios;
import facade.UsuariosFacade;
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
@Named(value = "controladorUsuario")
@SessionScoped
public class ControladorUsuario implements Serializable {

    private String asunto;
    private String contenido;
    private String clave;
    private Email email;
    /**
     * Creates a new instance of ControladorUsuario
     */
    private Integer selectedItemIndex;
    private int estado = 0;

    @EJB
    private UsuariosFacade usuarioEntityFacade;
    Usuarios user;

    public ControladorUsuario() {
           email = new Email();
    }

    public void estado() {
        this.estado = 0;
    }

    public void borrarEstado() {
        estado = 0;
    }

    @PostConstruct
    public void init() {
        user = new Usuarios();
        email = new Email();
    }

    public void registar() {
        try {

            user.setEstado("1");
            if (usuarioEntityFacade.existeUsuario(user.getDocumento()) == false) {
                usuarioEntityFacade.create(user);
                estado = 1;
                email.setEmailRemitente("isqm.system@gmail.com");
                email.setPassRemitente("12345isqm");
                email.setEmailDestinatario(user.getCorreo());
                String con = user.getContrasena();
                String cor = user.getCorreo();
                setAsunto("Notificacion Cuenta");
                setContenido(" El correo Registrado fue " + cor + " Su contrase;a es " + con);
                if (estado == 1) {
                    if (email.enviarNotificacion(asunto, contenido)) {
                        return ;

                    } else {
                        return;

                    }

                } else {
                    estado = 3;
                }

            }
            else{
                estado=4;
            }
        } catch (Exception e) {

            estado = 2;

        }

    }

    public List<Usuarios> getAll() {
        List<Usuarios> li = usuarioEntityFacade.findAll();
        return li;
    }

    public void modificar() {
        try {
            usuarioEntityFacade.edit(user);

            estado = 3;
        } catch (Exception e) {
            estado = 4;

        }
    }

    public void eliminar() {
        try {
            usuarioEntityFacade.remove(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Actualizo el registro"));
            estado = 5;

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " No se pudo actualizar del usuario en la base de datos"));
            estado = 6;
        }
    }

    public UsuariosFacade getUsuarioEntityFacade() {
        return usuarioEntityFacade;
    }

    public void setUsuarioEntityFacade(UsuariosFacade usuarioEntityFacade) {
        this.usuarioEntityFacade = usuarioEntityFacade;
    }

    public Usuarios getUser() {
        return user;
    }

    public ControladorUsuario(UsuariosFacade usuarioEntityFacade, Usuarios user) {
        this.usuarioEntityFacade = usuarioEntityFacade;
        this.user = user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    //converter usuario
    public Usuarios getSelected() {
        if (user == null) {
            user = new Usuarios();
            selectedItemIndex = -1;
        }
        return user;
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(usuarioEntityFacade.findAll(), false);
    }

    public Usuarios getUsuarios(java.lang.Integer id) {
        return usuarioEntityFacade.find(id);
    }

    @FacesConverter(forClass = Usuarios.class)
    public static class UsuarioConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ControladorUsuario controlador = (ControladorUsuario) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "controladorUsuario");
            return controlador.getUsuarios(getKey(value));
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
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getIdUsuarios());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuarios.class.getName());
            }
        }

    }

    public void delete() {
        try {
            usuarioEntityFacade.remove(user);
        } catch (Exception e) {
        }
    }

    public void recordarContrasena() {

        estado = 1;
        if (usuarioEntityFacade.existeCorrreo(user.getCorreo())) {
            email.setEmailRemitente("isqm.system@gmail.com");
            email.setPassRemitente("12345isqm");
            email.setEmailDestinatario(user.getCorreo());
            setAsunto("Recordar Contrasena");
            Funcionalidades fun = new Funcionalidades();
            String aleatorio = fun.contrasenaAleatoria();
            setContenido(" Su contrase;a nueva es " + aleatorio);
             try {
                usuarioEntityFacade.edit(user);
               
            } catch (Exception e) {
            }
            if (estado == 1) {
                if (email.enviarNotificacion(asunto, contenido)) {
                    return;

                } else {
                    return;

                }
            }

        }else{
            System.out.println("no envio");
        }
        
    }
    
}
