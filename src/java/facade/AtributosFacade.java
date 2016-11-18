/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitis.Atributos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMIN
 */
@Stateless
public class AtributosFacade extends AbstractFacade<Atributos> {

    @PersistenceContext(unitName = "ISQMPrubaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtributosFacade() {
        super(Atributos.class);
    }
    
}
