/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entitis.Feedbacks;
import facade.FeedbacksFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorFeedbacks")
@SessionScoped
public class ControladorFeedbacks implements Serializable {

    /**
     * Creates a new instance of ControladorFeedbacks
     */
    public ControladorFeedbacks() {
    }
    private int estado = 0;
    Feedbacks feedbacks;
    @EJB
    FeedbacksFacade facadeFeedbacks;
    
    @PostConstruct
    public void init(){
        feedbacks = new Feedbacks();
    }
    public void estado() {
        this.estado = 0;
    }

    public void borrarEstado() {
        estado = 0;
    }
    public void registrar(){
        try {
            facadeFeedbacks.create(feedbacks);
            estado = 1;
        } catch (Exception e) {
            estado = 2;
        }
    }
 public List<Feedbacks> getAll() {
        List<Feedbacks> li = facadeFeedbacks.findAll();
        return li;
    }
    
 
    /**
     * EL METODO ELIMINAR SE IMPLEMENTA EL OBJETO DE CREADO DE FACADA SE LE MASA EL METODO REMOVE Y LUEGO NUESTRA ENTIDAD
     */
    public void eliminar() {
        try {
            facadeFeedbacks.remove(feedbacks);
            estado = 3;
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
            facadeFeedbacks.edit(feedbacks);
            estado = 5;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Elimino el registro"));
        } catch (Exception e) {
            estado = 6;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " No se pudo eliminar el registro la base de datos"));
        }
    }
    
    
    public Feedbacks getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedbacks feedbacks) {
        this.feedbacks = feedbacks;
    }

    public FeedbacksFacade getFacadeFeedbacks() {
        return facadeFeedbacks;
    }

    public void setFacadeFeedbacks(FeedbacksFacade facadeFeedbacks) {
        this.facadeFeedbacks = facadeFeedbacks;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public ControladorFeedbacks(Feedbacks feedbacks, FeedbacksFacade facadeFeedbacks) {
        this.feedbacks = feedbacks;
        this.facadeFeedbacks = facadeFeedbacks;
    }
    
}
