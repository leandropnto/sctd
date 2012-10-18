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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author LeandroVBOX
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "Funcionario")
public class Funcionario implements Serializable {

    @Id
    @Column(name = "matricula")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer matricula;
    @Column(name = "nome", nullable = false, length = 60)
    String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dtNascimento", nullable = false)
    Date dataNascimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dtContratacao", nullable = false)
    Date dataContratacao;
    @Column(name = "salario")
    BigDecimal salario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCargo")
    @Fetch(FetchMode.JOIN)
    Cargo cargo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDepartamento")
    @Fetch(FetchMode.JOIN)
    Departamento departamento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idStatus")
    @Fetch(FetchMode.JOIN)
    FuncionarioStatus status;
    private String cpf;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEspecialidade")
    @Fetch(FetchMode.JOIN)
    private Especialidade especilidade;

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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public FuncionarioStatus getStatus() {
        return status;
    }

    public void setStatus(FuncionarioStatus status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Especialidade getEspecilidade() {
        return especilidade;
    }

    public void setEspecilidade(Especialidade especilidade) {
        this.especilidade = especilidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.matricula != null ? this.matricula.hashCode() : 0);
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

    @Override
    public String toString() {
        return nome.toUpperCase();
    }
}
