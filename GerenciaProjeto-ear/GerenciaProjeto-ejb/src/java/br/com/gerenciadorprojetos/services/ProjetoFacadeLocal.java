/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Projeto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vanessa
 */
@Local
public interface ProjetoFacadeLocal {

    void create(Projeto projeto);

    void edit(Projeto projeto);

    void remove(Projeto projeto);

    Projeto find(Object id);

    List<Projeto> findAll();

    List<Projeto> findRange(int[] range);

    int count();

    public int findByCodigo(Integer codigo);

    
}
