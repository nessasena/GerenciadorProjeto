/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Projeto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vanessa
 */
@Stateless
public class ProjetoFacade extends AbstractFacade<Projeto> implements ProjetoFacadeLocal {
    @PersistenceContext(unitName = "GerenciaProjeto-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjetoFacade() {
        super(Projeto.class);
    }

    @Override
    public int findByCodigo(Integer codigo) {
        Query qr = em.createQuery("SELECT p FROM Projeto p "
                + "WHERE p.codigo = :codigo");
        qr.setParameter("codigo", codigo);
        return qr.getResultList().size();
        
    }
    
}
