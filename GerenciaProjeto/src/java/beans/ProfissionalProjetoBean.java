/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.ProfissionalProjeto;
import br.com.gerenciadorprojetos.entities.Projeto;
import br.com.gerenciadorprojetos.services.ProfissionalProjetoFacadeLocal;
import controller.Controller;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import util.FuncaoEnum;

/**
 *
 * @author vanessa
 */
@ManagedBean (name="profissionalProjetoBean")
@SessionScoped
public class ProfissionalProjetoBean {
    
    private Long id;
    
    private Projeto projeto;
    
    private Profissional profissional;
    
    private Date dataEntrada;
        
    private Date dataDesligamento;
    
    private boolean desligamento = false;
    
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/ProfissionalProjetoFacadeLocal")
    private ProfissionalProjetoFacadeLocal ejbProfissionalProjeto;
      
       
    public String salvar() {
        if(Controller.getInstancia().getUsuarioLogado().getFuncao().equals(FuncaoEnum.GERENTE.toString())){
            
            ProfissionalProjeto p = new ProfissionalProjeto(this.projeto, this.profissional, this.dataEntrada, this.dataDesligamento);
            if(ejbProfissionalProjeto.getQtdProjetosPorGerente(profissional) > 0){
                FacesMessage msg = new FacesMessage("Um gerente só pode fazer parte de um projeto em andamento!");
                FacesContext.getCurrentInstance().addMessage("erro", msg);
                return null;
            }else{
                
                ejbProfissionalProjeto.create(p);

                //RegraDoNegocio.getInstance().cadastrarProjeto(p);
                this.desligamento = false;
                return "sucesso";
            }
        }else{
            FacesMessage msg = new FacesMessage("Apenas um gerente pode adicionar um profissional ao projeto!");
            FacesContext.getCurrentInstance().addMessage("erro", msg);
            return null;
        }
    }
    
    public String alterar() {
       
        System.out.println("ID: " + id);
        System.out.println("pro: " + projeto.getNome());
        System.out.println("p: " + profissional.getNome());
        System.out.println("de: " + dataEntrada);
        System.out.println("dd: " + dataDesligamento);
        
        ProfissionalProjeto p = new ProfissionalProjeto(this.id, this.projeto, this.profissional, 
                this.dataEntrada, this.dataDesligamento);
        ejbProfissionalProjeto.edit(p);
        //RegraDoNegocio.getInstance().cadastrarProjeto(p);
        this.desligamento = false;
        return "sucesso";
    }
    
    public void limpar(){
        this.dataEntrada = null;
        this.dataDesligamento = null;
        this.desligamento = false;
    }
    
    public boolean isDesligamento(){
        return desligamento;
    }
    
    public void setDesligamento(boolean desligamento){
        this.desligamento = desligamento;
    }
            
    
    public String desligar(ProfissionalProjeto profissionalProjeto){
        System.out.println("CHAMANDO TELA DE DESLIGAMENTO");
        System.out.println(profissionalProjeto);
        this.dataEntrada = profissionalProjeto.getDataEntrada();
        this.dataDesligamento = profissionalProjeto.getDataDesligamento();
        this.profissional = profissionalProjeto.getProfissional();
        this.id = profissionalProjeto.getId();
        this.desligamento = true;
        return "tela_desligamento";
    }
    
    
    public List<ProfissionalProjeto> listarProfissionalProjeto(){
        System.out.println("Listando profissional");
        System.out.println("Em Listar Profissionais Projeto: Projeto nome: "+projeto.getNome());
        return ejbProfissionalProjeto.listarProfissionalProjetoPorProjeto(projeto);
    }
    
    public String addProfissional(){
        System.out.println("Adicionando profissional");
        //System.out.println("Em addProfissionaol. Projeto: "+this.projeto.getNome());
        this.profissional = new Profissional();
        limpar();
        return "sucesso";
    }
    

    public Date getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(Date dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
    
}
