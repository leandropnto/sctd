/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import br.com.tcc.sctd.constants.FormaPagamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author lpinto
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "Venda")
public class Venda implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dataVenda", nullable=false)
    private Date dataVenda;
    @Column(name="precoTotal", nullable=false, precision=10, scale=2)
    private BigDecimal precoTotal;    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST, fetch= FetchType.LAZY) 
    @Fetch(FetchMode.JOIN)
    private List<ItemVenda> itens;
    @OneToOne
    private Entrega entrega;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idCliente", nullable=true)
    private Cliente cliente;
    
    @Column(name="formaPagamento")
    @Enumerated(EnumType.ORDINAL)
    private FormaPagamento formaPagamento;
    
    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name="idFatura")    
    private Fatura fatura;
    
    public Venda() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }
    
    
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + '}';
    }
    
    
    
}
