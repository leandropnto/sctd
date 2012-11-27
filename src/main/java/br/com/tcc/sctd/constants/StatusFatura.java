/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author leandro
 */
public enum StatusFatura {
    ANDAMENTO("Andamento"), QUITADO("Quidato"), ATRASADO("Atrasado");
    
    private String statusFatura;

    private StatusFatura(String statusFatura) {
        this.statusFatura = statusFatura;
    }

    public String getStatusFatura() {
        return statusFatura;
    }
    
    
    
}
