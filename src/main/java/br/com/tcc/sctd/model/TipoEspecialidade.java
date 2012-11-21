/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Praia
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "TipoEspecialidade")
public class TipoEspecialidade implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 150)
    private String descricao;

   
    @ManyToMany(mappedBy = "tipoEspecialidades")
    private List<Funcionario> funcionarios;
    
    
    
    public TipoEspecialidade() {
    }

    public TipoEspecialidade(Integer id) {
        this.id = id;
    }

    public TipoEspecialidade(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

 
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoEspecialidade other = (TipoEspecialidade) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao.toUpperCase();
    }
    
    
}
