/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciadorprojetos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vanessa
 */
@Entity
public class ProfissionalProjeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Projeto projeto;
    
    private Profissional profissional;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrada")
    private Date dataEntrada;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_desligamento")
    private Date dataDesligamento;

    public ProfissionalProjeto(Long id, Projeto projeto, Profissional profissional, Date dataEntrada, Date dataDesligamento) {
        this.id = id;
        this.projeto = projeto;
        this.profissional = profissional;
        this.dataEntrada = dataEntrada;
        this.dataDesligamento = dataDesligamento;
    }

    
    
    
    public ProfissionalProjeto(Projeto projeto, Profissional profissional, Date dataEntrada, Date dataDesligamento) {
        this.projeto = projeto;
        this.profissional = profissional;
        this.dataEntrada = dataEntrada;
        this.dataDesligamento = dataDesligamento;
    }

    public ProfissionalProjeto() {
    }
      
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfissionalProjeto)) {
            return false;
        }
        ProfissionalProjeto other = (ProfissionalProjeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gerenciadorprojetos.entities.EquipeProjeto[ id=" + id + " ]";
    }
    
}
