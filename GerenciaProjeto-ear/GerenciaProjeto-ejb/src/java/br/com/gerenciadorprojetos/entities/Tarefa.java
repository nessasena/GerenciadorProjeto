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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vanessa
 */
@Entity
public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio")
    private Date dataInicio;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_prevista_termino")
    private Date dataPrevistaTermino;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_termino")
    private Date dataTermino;

    private ProfissionalProjeto profissionalProjeto;
    
    
    public Tarefa(String descricao, Date dataInicio, Date dataPrevistaTermino, Date dataTermino, ProfissionalProjeto profissionalProjeto) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataPrevistaTermino = dataPrevistaTermino;
        this.dataTermino = dataTermino;
        this.profissionalProjeto = profissionalProjeto;
    }

    public Tarefa() {
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

    public ProfissionalProjeto getProfissionalProjeto() {
        return profissionalProjeto;
    }

    public void setProfissionalProjeto(ProfissionalProjeto profissionalProjeto) {
        this.profissionalProjeto = profissionalProjeto;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Tarefa)) {
            return false;
        }
        Tarefa other = (Tarefa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gerenciadorprojetos.entities.Tarefa[ id=" + id + " ]";
    }
    
}
