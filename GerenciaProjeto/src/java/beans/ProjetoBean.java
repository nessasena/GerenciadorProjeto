/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.Projeto;
import br.com.gerenciadorprojetos.services.ProfissionalProjetoFacadeLocal;
import br.com.gerenciadorprojetos.services.ProjetoFacadeLocal;
import controller.Controller;
import java.util.Date;
import br.com.gerenciadorprojetos.services.TarefaFacadeLocal;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import util.FuncaoEnum;



/**
 *
 * @author alunoruy
 */

@ManagedBean (name="projetoBean")
public class ProjetoBean {
    
    private Integer codigo;
    private String nome;
    private Date dataAbertura;
    private Date dataTermino;
    private Date dataFechamento;
    private Profissional gerente;
    private Integer diasAtraso;
    private Float percentualTarefasConcluidas;
    private Projeto projeto;
    
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/TarefaFacadeLocal")
    private TarefaFacadeLocal ejbTarefa;
    
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/ProjetoFacadeLocal")
    private ProjetoFacadeLocal ejbProjeto;
    
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/ProfissionalProjetoFacadeLocal")
    private ProfissionalProjetoFacadeLocal ejbProfissionalProjeto;
    
    public String salvar() {
        if(Controller.getInstancia().getUsuarioLogado().getFuncao().equals(FuncaoEnum.GERENTE.toString())){
            if(!dataAbertura.before(dataTermino)){
                FacesMessage msg = new FacesMessage("A data de termino do projeto deve ser posterior a data de abertura!");
               FacesContext.getCurrentInstance().addMessage("erro", msg);
                return null;
            }
            
            if(isCodigoExistente(codigo)){
                FacesMessage msg = new FacesMessage("Já existe um projeto com este código!");
               FacesContext.getCurrentInstance().addMessage("erro", msg);
                return null;
            }
            
            if(ejbProfissionalProjeto.getQtdProjetosProfissional(gerente) > 0){
                 FacesMessage msg = new FacesMessage("Este gerente já participa de outro projeto!");
               FacesContext.getCurrentInstance().addMessage("erro", msg);
                return null;
            }else{
                Projeto p = new Projeto(codigo, nome, dataAbertura, dataTermino, null, gerente);  
                ejbProjeto.create(p);
                //RegraDoNegocio.getInstance().cadastrarProjeto(p);
                return "sucesso";
            }
        }else{
            FacesMessage msg = new FacesMessage("Apenas um gerente pode criar um projeto!");
            FacesContext.getCurrentInstance().addMessage("erro", msg);
            return null;
        }
    }
    
    public String encerrarProjeto(){
        if(Controller.getInstancia().getUsuarioLogado().getFuncao().equals(FuncaoEnum.GERENTE.toString())){
            Projeto p = ejbProjeto.find(projeto.getCodigo());
            p.setDataFechamento(this.projeto.getDataFechamento());
            this.setProjeto(p);
            ejbProjeto.edit(p);     
            Long atraso = this.projeto.getDataFechamento().getTime() - this.projeto.getDataTermino().getTime();
             if (atraso > 0){
                  atraso = atraso/1000/60/60/24;
             } else {
                     atraso = 0L;
             }
             this.diasAtraso = atraso.intValue();
             int total =  ejbTarefa.getTotalTarefas(p);
             if (total > 0){
                  this.percentualTarefasConcluidas = ((float)ejbTarefa.getTarefasConcluidas(p) / total)*100;
              } else {
                 this.percentualTarefasConcluidas = 100.0F;
             }
             System.out.println("Tarefas concluidas: "+ this.percentualTarefasConcluidas);
            return "sucesso";
        }else{
            FacesMessage msg = new FacesMessage("Apenas um gerente pode encerrar um projeto!");
            FacesContext.getCurrentInstance().addMessage("erro", msg);
            return null;
        }
    }
    
    public String defineProjeto() {        
        return "ok";
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
    
    public List<Projeto> listarProjetos() throws Exception {
        return ejbProjeto.findAll();        
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Profissional getGerente() {
        return gerente;
    }

    public void setGerente(Profissional gerente) {
        this.gerente = gerente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getDiasAtraso() {
        return diasAtraso;
    }
    
    public void setDiasAtraso(Integer diasAtraso) {
        this.diasAtraso = diasAtraso;
    }
    
     public Float getPercentualTarefasConcluidas() {
         return percentualTarefasConcluidas;
         
     }
     
     public void setPercentualTarefasConcluidas(Float percentualTarefasConcluidas) {
         this.percentualTarefasConcluidas = percentualTarefasConcluidas;
     }

    private boolean isCodigoExistente(Integer codigo) {
        int qtdProjetos = ejbProjeto.findByCodigo(codigo);
        System.out.println("Projeto: " + qtdProjetos);
        if(qtdProjetos > 0){
            return true;
        }
        return false;
    }
     
     
}
