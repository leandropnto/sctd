/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import br.com.tcc.sctd.constants.StatusOrdemServico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author leandro
 */
@Entity
@Table(catalog = "tcc", schema = "public", name = "OrdemServico")
public class OrdemServico implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="dataInicio", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name="dataEntrega")
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    @ManyToOne(optional=false)
    @JoinColumn(name="idProduto", referencedColumnName="id")
    private Produto produto;
    @OneToOne
    @JoinColumn(name="idItem", referencedColumnName="id")
    private ItemPedido item;
    @Column(name="quantidade", nullable=false)
    private Integer quantidade;
    @Enumerated(EnumType.ORDINAL)
    private StatusOrdemServico status;
    @ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(name = "OrdemServico_funcionario", joinColumns = {
        @JoinColumn(name = "id_ordem")
    }, inverseJoinColumns = {
        @JoinColumn(name = "id_funcionario")
    })
    private List<Funcionario> funcionarios;

    public OrdemServico() {
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemPedido getItem() {
        return item;
    }

    public void setItem(ItemPedido item) {
        this.item = item;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico other = (OrdemServico) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
}
