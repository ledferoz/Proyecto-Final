/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entitis.Tipodocumento;
import controlador.ControladorTipoDocumento;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ADMIN
 */
@FacesConverter(forClass = Tipodocumento.class)
public class ConverterTipoDocumento implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        ControladorTipoDocumento td = (ControladorTipoDocumento) context.getApplication().getELResolver().getValue(context.getELContext(), null, "controladorTipoDocumento");
        Tipodocumento t = td.getTipoDocumento(value);
        return t;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null) {
            return null;

        }
        if (object instanceof Tipodocumento) {
            Tipodocumento dt = (Tipodocumento) object;
            return String.valueOf(dt.getIdTipoDocumento());

        }
        return null;
    }

  

}
