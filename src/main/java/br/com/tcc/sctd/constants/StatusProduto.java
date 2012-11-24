/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author lpinto
 */
public enum StatusProduto {
    DISPONIVEL("Disponível"), ESGOTADO("Esgotado"), EXCLUIDO("Excluído");
    
    private String statusProduto;

    private StatusProduto(String statusProduto) {
        this.statusProduto = statusProduto;
    }

    @Override
    public String toString() {
        return statusProduto;
    }
    
    
}
