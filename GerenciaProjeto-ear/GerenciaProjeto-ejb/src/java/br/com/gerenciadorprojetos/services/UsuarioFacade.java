/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vanessa
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "GerenciaProjeto-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

     @Override
    public Usuario findByLoginAndPassword(String email, String password) {
        try{
            System.out.println("Efetuando Login de: " + email + " com senha: " + password);
            Query qr = em.createQuery("Select u From Usuario u where u.email = :email and u.senha = :senha");
            qr.setParameter("email", email);
            qr.setParameter("senha", password);
            return (Usuario)qr.getSingleResult();
        }catch(NoResultException e){
            System.out.println("EWRRROOOO");
            e.printStackTrace();
            return null;
        }
    }
    
    public UsuarioFacade() {
        super(Usuario.class);
    }
    
}
