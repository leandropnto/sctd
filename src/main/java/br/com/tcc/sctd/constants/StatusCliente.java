/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author lpinto
 */
public enum StatusCliente {
    ATIVO("Ativo"), INADIMPLENTE("Inadimplente"), DESATIVADO("Desativado");
    
    private String statusCliente;

    private StatusCliente(String statusCliente) {
        this.statusCliente = statusCliente;
    }

    @Override
    public String toString() {
        return statusCliente;
    }
    
    
}
