/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author leandro
 */
public enum StatusPedido {
    ABERTO("Aberto"), CANCELADO("Cancelado"), ANDAMENTO("Andamento"), CONCLUIDO("Concluído"), ENTREGUE("Entregue");
    
    private String statusPedido;

    private StatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    @Override
    public String toString() {
        return statusPedido;
    }
    
    
    
}
