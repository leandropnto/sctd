/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dto;

import java.math.BigDecimal;

/**
 *
 * @author Praia
 */
public class DepartamentoDto {
    private String nome;
    private Integer quantidade;
    private Integer ativos;
    private Integer inativos;
    private BigDecimal salarios;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getAtivos() {
        return ativos;
    }

    public void setAtivos(Integer ativos) {
        this.ativos = ativos;
    }

    public Integer getInativos() {
        return inativos;
    }

    public void setInativos(Integer inativos) {
        this.inativos = inativos;
    }

    public BigDecimal getSalarios() {
        return salarios;
    }

    public void setSalarios(BigDecimal salarios) {
        this.salarios = salarios;
    }

    public DepartamentoDto(String nome, Integer quantidade, Integer ativos, Integer inativos, BigDecimal salarios) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.ativos = ativos;
        this.inativos = inativos;
        this.salarios = salarios;
    }

    public DepartamentoDto() {
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DepartamentoDto other = (DepartamentoDto) obj;
        if (this.nome != other.nome && (this.nome == null || !this.nome.equals(other.nome))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return nome;
    }
}
