/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.ProfissionalProjeto;
import br.com.gerenciadorprojetos.entities.Projeto;
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
public class ProfissionalProjetoFacade extends AbstractFacade<ProfissionalProjeto> implements ProfissionalProjetoFacadeLocal {
    @PersistenceContext(unitName = "GerenciaProjeto-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfissionalProjetoFacade() {
        super(ProfissionalProjeto.class);
    }
    
    public List<ProfissionalProjeto> listarProfissionalProjetoPorProjeto(Projeto projeto) {
        Query qr = em.createQuery("SELECT p FROM ProfissionalProjeto p WHERE p.projeto = :projeto");
        qr.setParameter("projeto", projeto);
        return qr.getResultList();
    }

    @Override
    public int getQtdProjetosPorGerente(Profissional profissional) {
        Query qr = em.createQuery("SELECT count(p) FROM ProfissionalProjeto p WHERE p.projeto.gerente.cpf = :profissionalCpf and p.projeto.dataFechamento is null");
        qr.setParameter("profissionalCpf", profissional.getCpf());
        return Integer.parseInt(qr.getSingleResult().toString());
    }

    @Override
    public int getQtdProjetosProfissional(Profissional profissional) {
        Query qr = em.createQuery("SELECT count(p) FROM ProfissionalProjeto p WHERE p.profissional.cpf = :profissionalCpf and p.projeto.dataFechamento is null and p.dataDesligamento is null");
        qr.setParameter("profissionalCpf", profissional.getCpf());
        return Integer.parseInt(qr.getSingleResult().toString());
    }
    
}
