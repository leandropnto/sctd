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
@Table(catalog = "tcc", schema = "public", name = "PessoaFisica")
public class PessoaFisica extends Cliente{
   
    
    @Column(name="cpf", nullable=false, length=11)
    private String cpf;

    public PessoaFisica() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
    
}
