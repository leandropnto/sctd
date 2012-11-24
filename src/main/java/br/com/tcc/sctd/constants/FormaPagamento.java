/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author leandro
 */
public enum FormaPagamento {
    
    DINHEIRO("Dinheiro"), CHEQUE("Cheque"), CARTAO_CREDITO("Cartão de Crédito"), CARTAO_DEBITO("Cartão Débito");
    
    private String formaPagamento;

    private FormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return formaPagamento;
    }
    
    
    
}
