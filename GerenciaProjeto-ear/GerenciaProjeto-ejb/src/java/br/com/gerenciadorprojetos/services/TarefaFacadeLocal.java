/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.Projeto;
import br.com.gerenciadorprojetos.entities.Tarefa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vanessa
 */
@Local
public interface TarefaFacadeLocal {

    void create(Tarefa tarefa);

    void edit(Tarefa tarefa);

    void remove(Tarefa tarefa);

    Tarefa find(Object id);

    List<Tarefa> findAll();

    List<Tarefa> findRange(int[] range);

    int count();
    
    int getTotalTarefas(Projeto projeto);
    
    int getTarefasConcluidas(Projeto projeto);
    
    List<Tarefa> getTarefasPorProjeto(Projeto projeto);
    
    Tarefa verificaConflitoTarefa(Tarefa tarefa, Profissional profissional);
}
