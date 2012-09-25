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
public class Projeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer codigo;
    
    private String nome;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_abertura")
    private Date dataAbertura;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_termino")
    private Date dataTermino;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_fechamento")
    private Date dataFechamento;
    
    private Profissional gerente;

    public Projeto(Integer codigo, String nome, Date dataAbertura, Date dataTermino, Date dataFechamento, Profissional gerente) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataAbertura = dataAbertura;
        this.dataTermino = dataTermino;
        this.dataFechamento = dataFechamento;
        this.gerente = gerente;
    }

    public Projeto() {
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

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gerenciadorprojetos.entities.Projeto[ codigo=" + codigo + " ]";
    }
    
}
