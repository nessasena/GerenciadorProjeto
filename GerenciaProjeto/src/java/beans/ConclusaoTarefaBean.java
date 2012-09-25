/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import br.com.gerenciadorprojetos.entities.Projeto;
import br.com.gerenciadorprojetos.entities.Tarefa;
import br.com.gerenciadorprojetos.services.ProjetoFacadeLocal;
import br.com.gerenciadorprojetos.services.TarefaFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vanessa
 */
@ManagedBean(name="conclusaoTarefaBean")
@ViewScoped
public class ConclusaoTarefaBean {
    
    private Projeto projeto;
    
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/TarefaFacadeLocal")
    private TarefaFacadeLocal ejbTarefa;
    
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/ProjetoFacadeLocal")
    private ProjetoFacadeLocal ejbProjeto;
    
    public List<Projeto> listarProjetos() throws Exception {
        return ejbProjeto.findAll();        
    }
    
    public List<Tarefa> listarTarefas(){        
        return ejbTarefa.getTarefasPorProjeto(this.projeto);
    }
    
    public String concluir(Tarefa tarefa){        
        System.out.println("Tarefa id "+tarefa.getId());
        System.out.println("Tarefa termino "+tarefa.getDataTermino());
        tarefa.setDataTermino(new Date());        
        ejbTarefa.edit(tarefa);
        return "ok";
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
    
}
