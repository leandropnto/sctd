/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author lpinto
 */
@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(catalog = "tcc", schema = "public", name = "PessoaJuridica")
public class PessoaJuridica extends Cliente{
    
    @Column(name="cnpj", length=14, nullable=false)    
    private String cnpj;
    @Column(name="responsavel", length=100, nullable=false)    
    private String responsavel;
    @Column(name="nomeOficial", length=100, nullable=false)    
    private String nomeOficial;

    public PessoaJuridica() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeOficial() {
        return nomeOficial;
    }

    public void setNomeOficial(String nomeOficial) {
        this.nomeOficial = nomeOficial;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return nomeOficial.toUpperCase();
    }
    
    
    
}
