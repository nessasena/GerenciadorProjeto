/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.services;

import br.com.gerenciadorprojetos.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vanessa
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);
    Usuario findByLoginAndPassword(String email, String password);
    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
}
