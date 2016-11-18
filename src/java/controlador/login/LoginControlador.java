/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.login;

import entitis.Roles;
import entitis.Usuarios;
import facade.UsuariosFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ADMIN
 */
@Named(value = "loginControlador")
@SessionScoped
public class LoginControlador implements Serializable{

    private Usuarios u;
    @EJB
   private UsuariosFacade facadeUsuario;
    
   @Size(min=4, max=10)
    @NotEmpty
    private String documento;
    private String password;
   private Roles  rol;
   private int estado= 0;
    /**
     * Creates a new instance of LoginControlador
     */
   
    public LoginControlador() {
    }
    public void estado(){
        this.estado=0;
    } 
    
    public void borrarEstado(){
        estado=0;
    }
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
public String iniciarSession(){
      u = facadeUsuario.login(documento, password);
         if(u == null){
             estado = 1;
            return "index";
           
        } else{
            rol= u.getIdRol();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
            return "protegido/index?faces-redirect=true";
        }
                
    }

//    public String inicioSesion(){
//       String url = "";
//        u = facadeUsuario.login(documento, password);
//        if (u != null) {
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogin", u);
//            
//        url = "protegido/index.xhtml" ;
//        } else {
//            
//        }
//        return  url;
//    }
    

     public String cerrarSesion() {
        u = null;
        rol= null;
       documento = null;
        password = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        return "/index?faces-redirect=true";
    }
     public boolean isLogin() {
        return ( u!= null);
    }

    public Usuarios getU() {
        return u;
    }

    public void setU(Usuarios u) {
        this.u = u;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
     
     
}
