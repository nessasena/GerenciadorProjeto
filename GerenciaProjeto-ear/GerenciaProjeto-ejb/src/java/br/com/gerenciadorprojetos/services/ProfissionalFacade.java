/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Profissional;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vanessa
 */
@Stateless
public class ProfissionalFacade extends AbstractFacade<Profissional> implements ProfissionalFacadeLocal {
    @PersistenceContext(unitName = "GerenciaProjeto-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfissionalFacade() {
        super(Profissional.class);
    }
    
    public List<Profissional> listarProfissionalPorFuncao(String funcao) {
        Query qr = em.createQuery("SELECT u FROM Profissional u WHERE u.funcao=:funcao");
        qr.setParameter("funcao", funcao);
        return qr.getResultList();
    }
    
    
}
