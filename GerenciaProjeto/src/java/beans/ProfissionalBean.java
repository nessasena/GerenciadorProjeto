/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import br.com.gerenciadorprojetos.entities.Profissional;
import br.com.gerenciadorprojetos.entities.Usuario;
import br.com.gerenciadorprojetos.services.ProfissionalFacade;
import br.com.gerenciadorprojetos.services.ProfissionalFacadeLocal;
import br.com.gerenciadorprojetos.services.UsuarioFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import util.FuncaoEnum;
/**
 *
 * @author alunoruy
 */
@ManagedBean(name="profissionalBean")
public class ProfissionalBean {
    
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private Date dataAdmissao;
    private String funcao;
    private String grauInstrucao;
    private String email;
    private String senha;
    
    
    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/ProfissionalFacadeLocal")
    private ProfissionalFacadeLocal ejbProfissional;

    @EJB(name="java:comp/env/br/com/gerenciadorprojetos/services/UsuarioFacadeLocal")
    private UsuarioFacadeLocal ejbUsuario;
    
    public ProfissionalBean()
    {
        //usuario = new UsuarioBean();
    }
        
    
    public String salvar() {
        if (email.equals("")){
            ejbProfissional.create(new Profissional(cpf,nome,dataNascimento,dataAdmissao,funcao, grauInstrucao));
        } else {
            ejbUsuario.create(new Usuario( email, senha, cpf, nome,dataNascimento,dataAdmissao,funcao, grauInstrucao));
        }
        
        
        System.out.println("grauInstrucao: " + grauInstrucao + " funcao: " + funcao+ " cpf "+cpf);
        //RegraDoNegocio.getInstance().cadastrarProfissional(p);
        return "sucesso";
    }
    
    public List<Profissional> listarProfissionais() throws Exception {
        return ejbProfissional.findAll();        
    }
    
    public List<Profissional> listarGerentes() throws Exception {
        return ejbProfissional.listarProfissionalPorFuncao(FuncaoEnum.GERENTE.toString());        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getGrauInstrucao() {
        return grauInstrucao;
    }

    public void setGrauInstrucao(String grauInstrucao) {
        this.grauInstrucao = grauInstrucao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String editar(Profissional p) {
       
       nome = p.getNome();
       cpf = p.getCpf();
       dataNascimento = p.getDataNascimento();
       dataAdmissao = p.getDataAdmissao();
       funcao = p.getFuncao();
       grauInstrucao = p.getGrauInstrucao();
       
       return "profissional";
    }
    
}