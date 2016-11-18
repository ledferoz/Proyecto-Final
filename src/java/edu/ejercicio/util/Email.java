/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ejercicio.util;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Ismael
 */
public class Email {
    
    public final static String HOST_EMAIL_GMAIL = "smtp.gmail.com";
    
    private String emailRemitente;
    private String passRemitente;
    private String emailDestinatario;
    
    
    private Session session;
    private MimeMessage mimeMessage;

    public Email() {
    }

    public Email(String emailRemitente, String passRemitente, String emailDestinatario) {
        this.emailRemitente = emailRemitente;
        this.passRemitente = passRemitente;
        this.emailDestinatario = emailDestinatario;
    }
    
    private void init(){
        try {
            Properties propiedades = new Properties();
            
            propiedades.setProperty("mail.smtp.host", HOST_EMAIL_GMAIL);
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "587");//587
            propiedades.setProperty("mail.smtp.user", this.emailRemitente);
            propiedades.setProperty("mail.smtp.auth", "true");
            
            session = Session.getDefaultInstance(propiedades);
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(emailRemitente));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean enviar(String asunto, String contenido){
        try {
            init();
            BodyPart texto = new MimeBodyPart();
            texto.setContent(contenido,"text/html");
            
            BodyPart adjunto = new MimeBodyPart();
            File file = new File("C:");
            adjunto.setDataHandler(new DataHandler(new FileDataSource(file)));
            adjunto.setFileName(file.getName());
            
            Multipart contenidoMensaje = new MimeMultipart();
            contenidoMensaje.addBodyPart(texto);
            contenidoMensaje.addBodyPart(adjunto);
            
            
            mimeMessage.setSubject(asunto);
            mimeMessage.setText(contenido);
            //mimeMessage.setContent(contenido, "text/html");
            
            Transport transport = session.getTransport("smtp");
            transport.connect(emailRemitente, passRemitente);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getEmailRemitente() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }

    public String getPassRemitente() {
        return passRemitente;
    }

    public void setPassRemitente(String passRemitente) {
        this.passRemitente = passRemitente;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }
    
}
