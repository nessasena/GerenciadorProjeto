/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;


/**
 *
 * @author vanessa
 */
@Entity
public class Usuario extends Profissional implements Serializable {
    protected static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    @Column(name="email")
    protected String email;
    protected String senha;

    public Usuario(String email, String senha, String cpf, String nome, Date dataNascimento, Date dataAdmissao, String funcao, String grauInstrucao) {
        super(cpf, nome, dataNascimento, dataAdmissao, funcao, grauInstrucao);
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCpf() != null ? getCpf().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.getCpf() == null && other.getCpf() != null) || (this.getCpf() != null && !this.getCpf().equals(other.getCpf()))) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Usuario[Email:"+email+";Senha:"+senha+"]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
}
