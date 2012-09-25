/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import br.com.gerenciadorprojetos.entities.Usuario;
import br.com.gerenciadorprojetos.services.UsuarioFacadeLocal;
import controller.Controller;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import util.FuncaoEnum;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
    
    private String nome;
    private String senha;
    private String email;
    private String confirmarsenha;
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/UsuarioFacadeLocal")
    private UsuarioFacadeLocal ejbUsuario;
    
    public boolean isGerente(){
        Usuario usuario = Controller.getInstancia().getUsuarioLogado();
        if( usuario!=null && usuario.getFuncao().equals(FuncaoEnum.GERENTE.toString())){
            return true;
        }
        return false;
    }
    
    public boolean isProfissional(){
        Usuario usuario = Controller.getInstancia().getUsuarioLogado();
        if( usuario!=null){
            return true;
        }
        return false;
    }
    
    public void logout(){
        Controller.getInstancia().setUsuarioLogado(null);
    }
    
    public String efetuarLogin() {
        Usuario usuario = ejbUsuario.findByLoginAndPassword(email, senha);
        
        System.out.println("Email: " + email);
        System.out.println("Password: " + senha);
        System.out.println("Usuario: " + usuario);
        if(usuario != null){
            Controller.getInstancia().setUsuarioLogado(usuario);
            return "login_sucesso";    
        }else{
            FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
            FacesContext.getCurrentInstance().addMessage("erro", msg);
            Controller.getInstancia().setUsuarioLogado(null);
            return null;
        }
    }
    
    public String salvar() {
        
        if(!senha.equalsIgnoreCase(confirmarsenha)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha e confirmação de senha não conferem",null));            
            return "usuario";
        }
        
        return "sucesso";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmarsenha() {
        return confirmarsenha;
    }

    public void setConfirmarsenha(String confirmarsenha) {
        this.confirmarsenha = confirmarsenha;
    }
}
