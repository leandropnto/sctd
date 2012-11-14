/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Praia
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "Modelo")
public class Modelo implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column
    private Float tamanho;

    @Column(length = 150)
    private String descricao;

    @Column
    private String molde;

    @Column
    private Character sexo;

    @ManyToOne(fetch = FetchType.EAGER)    
    private Tipo tipo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "especialidade", joinColumns = { @JoinColumn(name = "idModelo") }, inverseJoinColumns = { @JoinColumn(name = "idEspecialidade")})
    private Set<TipoEspecialidade> especialidade;

    public Modelo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMolde() {
        return molde;
    }

    public void setMolde(String molde) {
        this.molde = molde;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Float getTamanho() {
        return tamanho;
    }

    public void setTamanho(Float tamanho) {
        this.tamanho = tamanho;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Set<TipoEspecialidade> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Set<TipoEspecialidade> especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Modelo other = (Modelo) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome.toUpperCase();
    }
}
