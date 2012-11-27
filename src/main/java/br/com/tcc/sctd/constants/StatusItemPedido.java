/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author leandro
 */
public enum StatusItemPedido {
    PRODUCAO("Produção"), CONCLUIDO("Concluído"), FECHADA("Fechada");
    
    private String statusItemPedido;

    private StatusItemPedido(String statusItemPedido) {
        this.statusItemPedido = statusItemPedido;
    }
    
    
    
}
