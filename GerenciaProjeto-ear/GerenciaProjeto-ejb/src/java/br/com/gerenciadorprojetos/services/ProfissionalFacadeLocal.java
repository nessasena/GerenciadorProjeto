/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.ProfissionalProjeto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vanessa
 */
@Local
public interface ProfissionalFacadeLocal {

    void create(Profissional profissional);

    void edit(Profissional profissional);

    void remove(Profissional profissional);

    Profissional find(Object id);

    List<Profissional> findAll();

    List<Profissional> findRange(int[] range);

    int count();
    
    List<Profissional> listarProfissionalPorFuncao(String funcao);
     
}
