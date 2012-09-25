/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.Projeto;
import br.com.gerenciadorprojetos.entities.Tarefa;
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
public class TarefaFacade extends AbstractFacade<Tarefa> implements TarefaFacadeLocal {
    @PersistenceContext(unitName = "GerenciaProjeto-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TarefaFacade() {
        super(Tarefa.class);
    }

    @Override
    public int getTotalTarefas(Projeto projeto) {        
        Query qr = em.createQuery("SELECT t FROM Tarefa t join t.profissionalProjeto pp join pp.projeto p "
                + "WHERE pp.projeto = :projeto");
        qr.setParameter("projeto", projeto);
        return qr.getResultList().size();
    }

    @Override
    public int getTarefasConcluidas(Projeto projeto) {
        Query qr = em.createQuery("SELECT t FROM Tarefa t join t.profissionalProjeto pp "
                + "WHERE pp.projeto = :projeto and t.dataTermino is not null");
        qr.setParameter("projeto", projeto);
        return qr.getResultList().size();
    }
    
    @Override
    public List<Tarefa> getTarefasPorProjeto(Projeto projeto) {
        Query qr = em.createQuery("SELECT t FROM Tarefa t join t.profissionalProjeto pp "
                + "WHERE pp.projeto = :projeto");
        qr.setParameter("projeto", projeto);
        return qr.getResultList();
    }
    
    public Tarefa verificaConflitoTarefa(Tarefa tarefa, Profissional profissional){
        Query qr = em.createQuery("SELECT t FROM Tarefa t join t.profissionalProjeto pp "
                + "WHERE pp.profissional = :profissional and "
                + "((:dataInicio between t.dataInicio and t.dataPrevistaTermino) or "
                    + "(:dataPrevistaTermino between t.dataInicio and t.dataPrevistaTermino))");
        qr.setParameter("profissional", profissional);
        qr.setParameter("dataInicio", tarefa.getDataInicio());
        qr.setParameter("dataPrevistaTermino", tarefa.getDataPrevistaTermino());
        List<Tarefa> resultado = qr.getResultList();
        if (resultado.size() > 0){
            return resultado.get(0);
        } 
        return null;
    }
    
}
