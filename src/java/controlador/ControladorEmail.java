/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import controlador.util.Email;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorEmail")
@RequestScoped
public class ControladorEmail {

    private Email email;
    
    private String asunto;
    private String contenido;
    private String nombres;
    private String apellidos;
    private String correo;
    public ControladorEmail() {
         email = new Email();
    }
   
    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
     public String enviarMensaje(){
         email.setEmailRemitente("isqm.system@gmail.com");
         email.setPassRemitente("12345isqm");
         email.setEmailDestinatario("isqm.system@gmail.com");
         setAsunto("Contacto");
         setContenido("buena");
        if(email.enviar(asunto, contenido ,nombres,apellidos,correo)){
            return "index";
          
        } else{
            return "error";
           
        }
    }


    
    
    
}
