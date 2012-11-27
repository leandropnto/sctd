/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import br.com.tcc.sctd.constants.StatusPedido;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author leandro
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "Pedido")
public class Pedido implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "precoTotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoTotal;
    @Column(name = "valorPago", nullable = true, precision = 10, scale = 2)
    private BigDecimal valorPago;
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    @Enumerated(EnumType.ORDINAL)
    private StatusPedido status;
    @ManyToOne()
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idFuncionario", referencedColumnName = "matricula")
    private Funcionario funcionario;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<ItemPedido> itens;
    
    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name="idFatura", referencedColumnName="id")
    private Fatura fatura;
    
    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name="idEntrega", referencedColumnName="id")
    private Entrega entrega;

    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
