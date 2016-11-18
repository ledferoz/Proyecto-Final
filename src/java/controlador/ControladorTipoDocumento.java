/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entitis.Tipodocumento;
import facade.TipodocumentoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author ADMIN
 */
@Named(value = "controladorTipoDocumento")
@SessionScoped
public class ControladorTipoDocumento implements Serializable {

    /**
     * Creates a new instance of ControladorTipoDocumento
     */
    Tipodocumento current;
    @EJB
    private TipodocumentoFacade tipoDocumentoFacade;
    private int selectedItemIndex;
    public ControladorTipoDocumento() {
    }
    
    @PostConstruct
    public void init(){
        current = new Tipodocumento();
    }
    
       public Tipodocumento getSelected() {
        if (current == null) {
            current = new Tipodocumento();
            selectedItemIndex = -1;
        }
        return current;
    }
       
        public List<Tipodocumento> getAll(){
        List<Tipodocumento> li = tipoDocumentoFacade.findAll();
        return li;
    }
    public Tipodocumento getTipoDocumento(String id){
        return tipoDocumentoFacade.find(id);
    }

    public Tipodocumento getCurrent() {
        return current;
    }

    public void setCurrent(Tipodocumento current) {
        this.current = current;
    }

    public TipodocumentoFacade getTipoDocumentoFacade() {
        return tipoDocumentoFacade;
    }

    public void setTipoDocumentoFacade(TipodocumentoFacade tipoDocumentoFacade) {
        this.tipoDocumentoFacade = tipoDocumentoFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }
    
    
    
}
