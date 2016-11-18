/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitis.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ADMIN
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "ISQMPrubaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
//    public Usuarios login (Long documento, String password){
//        Usuarios usuario = null;
//        TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.usuarioLogin",Usuarios.class);
//        query.setParameter("documento", documento.toString());
//        query.setParameter("contrasena", password);
//       List<Usuarios> usuarios = query.getResultList();
//        if (usuarios.size() == 1) {
//            usuario= usuarios.get(0);
//        }
//   return usuario;
//    }
     public Usuarios login (String documento, String password){
        Usuarios usuario = null;
        TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.usuarioLogin",Usuarios.class);
        query.setParameter("documento", documento);
        query.setParameter("contrasena", password);
       List<Usuarios> usuarios = query.getResultList();
        if (usuarios.size() == 1) {
            usuario= usuarios.get(0);
        }
   return usuario;
    }
//    public Usuarios login(Long documento, String password){
//        try {
//            TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.usuarioLogin", Usuarios.class);
//            query.setParameter("documento", documento.toString());
//            query.setParameter("password", password);
//            return query.getSingleResult();
//        } catch (Exception e) {
//            return null;
//        }
//    }
     
       public boolean existeUsuario(String documento){
        return findByDocumento(documento).size()>0;
       }
       
       public List<Usuarios> findByDocumento(String documento){
           return em.createNamedQuery("Usuarios.findByDocumento").setParameter("documento", documento).getResultList();
       }
        public boolean existeCorrreo(String correo){
        return findByCorreo(correo).size()>0;
       }
         public List<Usuarios> findByCorreo(String correo){
           return em.createNamedQuery("Usuarios.findByCorreo").setParameter("correo", correo).getResultList();
       }
       
         
}
