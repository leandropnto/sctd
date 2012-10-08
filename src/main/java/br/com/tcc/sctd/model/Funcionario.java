/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author LeandroVBOX
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "Funcionario")
public class Funcionario implements Serializable {

    @Id
    @Column(name = "matricula")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer matricula;
    @Column(name = "nome", nullable = false, length = 60)
    String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dtNascimento", nullable = false)
    Date dataNascimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dtContratacao", nullable = false)
    Date dataContratacao;
    @Column(name="salario")
    BigDecimal salario;
    @Column(name="cargo", length=100)
    String cargo;
    @Column(name="departamento", length=100)
    String departamento;

    public Funcionario() {
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.matricula != null ? this.matricula.hashCode() : 0);
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
        final Funcionario other = (Funcionario) obj;
        if (this.matricula != other.matricula && (this.matricula == null || !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }
    
    
}
