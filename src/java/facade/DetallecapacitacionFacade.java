/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitis.Detallecapacitacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMIN
 */
@Stateless
public class DetallecapacitacionFacade extends AbstractFacade<Detallecapacitacion> {

    @PersistenceContext(unitName = "ISQMPrubaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallecapacitacionFacade() {
        super(Detallecapacitacion.class);
    }
    
}
