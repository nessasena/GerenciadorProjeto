/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.com.gerenciadorprojetos.entities.Usuario;

/**
 *
 * @author schkrabnote
 */
public class Controller {
    private static Controller instancia;
    private Usuario usuarioLogado = null;

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public static Controller getInstancia(){
        if(instancia == null){
            instancia = new Controller();
        }
        return instancia;
    }
    
    
    
    private Controller(){
        
    }
}
