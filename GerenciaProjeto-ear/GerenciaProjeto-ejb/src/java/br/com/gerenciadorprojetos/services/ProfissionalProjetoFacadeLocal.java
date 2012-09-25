/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.ProfissionalProjeto;
import br.com.gerenciadorprojetos.entities.Projeto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vanessa
 */
@Local
public interface ProfissionalProjetoFacadeLocal {

    void create(ProfissionalProjeto profissionalProjeto);

    void edit(ProfissionalProjeto profissionalProjeto);

    void remove(ProfissionalProjeto profissionalProjeto);

    ProfissionalProjeto find(Object id);

    List<ProfissionalProjeto> findAll();

    List<ProfissionalProjeto> findRange(int[] range);

    int count();
    
    List<ProfissionalProjeto> listarProfissionalProjetoPorProjeto(Projeto projeto);
    int getQtdProjetosPorGerente(Profissional profissional);
    int getQtdProjetosProfissional(Profissional profissional);
    
}
