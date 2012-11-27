/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import br.com.tcc.sctd.constants.StatusItemPedido;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author leandro
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "ItemPedido")
public class ItemPedido implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="quantidade", nullable=false)
    private Integer quantidade;
    @Column(name="quantidadePronta", nullable=true)
    private Integer quantidadePronta;
    @Enumerated(EnumType.ORDINAL)
    private StatusItemPedido status;
    
    @ManyToOne
    @JoinColumn(name="idProduto", referencedColumnName="id")
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name="idPedido", referencedColumnName="id")
    private Pedido pedido;
    

    public ItemPedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadePronta() {
        return quantidadePronta;
    }

    public void setQuantidadePronta(Integer quantidadePronta) {
        this.quantidadePronta = quantidadePronta;
    }

    public StatusItemPedido getStatus() {
        return status;
    }

    public void setStatus(StatusItemPedido status) {
        this.status = status;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPedido other = (ItemPedido) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

   
    
    
}
