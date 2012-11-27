/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import br.com.tcc.sctd.constants.StatusFuncionario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCargo")
    @Fetch(FetchMode.JOIN)
    Cargo cargo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDepartamento")
    @Fetch(FetchMode.JOIN)
    Departamento departamento;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    StatusFuncionario status;
    
    @Column(name="cpf", unique=true, length=11)
    private String cpf;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "funcionario_tipoespecialidade", joinColumns = {
        @JoinColumn(name = "matricula")
    }, inverseJoinColumns = {
        @JoinColumn(name = "id_especialidade")
    })
    private List<TipoEspecialidade> tipoEspecialidades;

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

    public StatusFuncionario getStatus() {
        return status;
    }

    public void setStatus(StatusFuncionario status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<TipoEspecialidade> getTipoEspecialidades() {
        return tipoEspecialidades;
    }

    public void setTipoEspecialidades(List<TipoEspecialidade> tipoEspecialidades) {
        this.tipoEspecialidades = tipoEspecialidades;
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
