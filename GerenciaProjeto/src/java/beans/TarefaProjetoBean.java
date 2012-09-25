package beans;

import br.com.gerenciadorprojetos.entities.ProfissionalProjeto;

import br.com.gerenciadorprojetos.entities.Tarefa;
import br.com.gerenciadorprojetos.services.ProfissionalProjetoFacadeLocal;
import br.com.gerenciadorprojetos.services.ProjetoFacadeLocal;
import br.com.gerenciadorprojetos.services.TarefaFacadeLocal;
import controller.Controller;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.FuncaoEnum;

/**
 *
 * @author vanessa
 */
@ManagedBean(name = "tarefaProjetoBean")
public class TarefaProjetoBean {

    private Long id;
    private String descricao;
    private Date dataInicio;
    private Date dataPrevistaTermino;
    private Date dataTermino;
    private Boolean mostrarConfirmacao;
    private ProfissionalProjeto profissionalProjeto;

    public TarefaProjetoBean() {
        profissionalProjeto = new ProfissionalProjeto();
        mostrarConfirmacao = false;
    }
    @EJB(name = "java:comp/env/br/com/gerenciadorprojetos/services/TarefaFacadeLocal")
    private TarefaFacadeLocal ejbTarefa;
    @EJB(name = "java:comp/env/br/com/gerenciadorprojetos/services/ProfissionalProjetoFacadeLocal")
    private ProfissionalProjetoFacadeLocal ejbProfissionalProjeto;
    @EJB(name = "java:comp/env/br/com/gerenciadorprojetos/services/ProjetoFacadeLocal")
    private ProjetoFacadeLocal ejbProjeto;

    public String salvar() {
        if (Controller.getInstancia().getUsuarioLogado().getFuncao().equals(FuncaoEnum.GERENTE.toString())) {
            ProfissionalProjeto profissionalProjeto = ejbProfissionalProjeto.find(this.profissionalProjeto.getId());
            this.profissionalProjeto = profissionalProjeto;

            if (profissionalProjeto.getProjeto().getDataFechamento() == null) {
                Tarefa tarefa = new Tarefa(this.descricao, dataInicio, dataPrevistaTermino, dataTermino, profissionalProjeto);

                Tarefa tarefaConflito = ejbTarefa.verificaConflitoTarefa(tarefa, profissionalProjeto.getProfissional());
                if (tarefaConflito == null) {

                    if (tarefa.getDataPrevistaTermino().after(tarefa.getProfissionalProjeto().getProjeto().getDataTermino())) {
                        this.profissionalProjeto.getProjeto().setDataTermino(tarefa.getDataPrevistaTermino());
                        ejbProjeto.edit(this.profissionalProjeto.getProjeto());
                        FacesMessage msg = new FacesMessage("A previsão de término do projeto foi atualizada!");
                        FacesContext.getCurrentInstance().addMessage("aviso", msg);
                    }
                    ejbTarefa.create(tarefa);
                    return "sucesso";


                } else {
                    FacesMessage msg = new FacesMessage("Esta tarefa está sobrepondo outra tarefa do profissional!");
                    FacesContext.getCurrentInstance().addMessage("erro", msg);
                    return null;
                    /*System.out.println("Pedir confirmação para salvar");
                    this.mostrarConfirmacao = true;
                    return null;*/
                }
            } else {
                FacesMessage msg = new FacesMessage("Não é possível adicionar tarefas para um projeto finalizado!");
                FacesContext.getCurrentInstance().addMessage("erro", msg);
                return null;
            }
        } else {
            FacesMessage msg = new FacesMessage("Apenas um gerente pode inserir uma tarefa ao projeto!");
            FacesContext.getCurrentInstance().addMessage("erro", msg);
            return null;
        }
    }

    public String confirmaSalvar() {
        System.out.println("Salvar confirmado...");
        Tarefa tarefa = new Tarefa(this.descricao, dataInicio, dataPrevistaTermino, dataTermino, profissionalProjeto);
        ejbTarefa.create(tarefa);
        this.mostrarConfirmacao = false;
        return "sucesso";
    }

    public String alterar() {
        return "sucesso";
    }

    public void limpar() {
    }

    public String desligar(ProfissionalProjeto profissionalProjeto) {
        return "sucesso";
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataPrevistaTermino() {
        return dataPrevistaTermino;
    }

    public void setDataPrevistaTermino(Date dataPrevistaTermino) {
        this.dataPrevistaTermino = dataPrevistaTermino;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfissionalProjeto getProfissionalProjeto() {
        return profissionalProjeto;
    }

    public void setProfissionalProjeto(ProfissionalProjeto profissionalProjeto) {
        this.profissionalProjeto = profissionalProjeto;
    }

    public Boolean getMostrarConfirmacao() {
        return mostrarConfirmacao;
    }

    public void setMostrarConfirmacao(Boolean mostrarConfirmacao) {
        this.mostrarConfirmacao = mostrarConfirmacao;
    }
}
