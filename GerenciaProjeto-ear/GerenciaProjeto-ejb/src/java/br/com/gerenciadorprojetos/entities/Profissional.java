/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vanessa
 */
@Entity
public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;
        
    @Id    
    private String cpf;
    
    
    private String nome;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_admissao")
    private Date dataAdmissao;
    
    private String funcao;
    
    @Column(name = "grau_instrucao")
    private String grauInstrucao;
    //private Usuario  usuario;

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

    public Profissional(String cpf, String nome, Date dataNascimento, Date dataAdmissao, String funcao, String grauInstrucao) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataAdmissao = dataAdmissao;
        this.funcao = funcao;
        this.grauInstrucao = grauInstrucao;
    }

    public Profissional() {
    }
    

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissional)) {
            return false;
        }
        Profissional other = (Profissional) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profissional[ cpf=" + cpf + " ]";
    }
    
}
