/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import br.com.tcc.sctd.constants.StatusCliente;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author lpinto
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "Cliente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nome", length=100, nullable=false)
    private String nome;
    @Column(name="telefone", length=20, nullable=true)
    private String telefone;
    @Column(name="email", length=100, nullable=true)
    private String email;
    
    @Column(name="status") 
    @Enumerated(EnumType.ORDINAL)
    private StatusCliente status;
    
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="endereco_id")
    Endereco endereco;    

    public Cliente() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public StatusCliente getStatus() {
        return status;
    }

    public void setStatus(StatusCliente status) {
        this.status = status;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

  
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return nome.toUpperCase();
    }
    
    

    
    
}
