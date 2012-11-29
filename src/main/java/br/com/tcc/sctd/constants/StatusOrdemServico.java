/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author leandro
 */
public enum StatusOrdemServico {
    ANDAMENTO("Andamento"), CANCELADA("Cancelada"), CONCLUIDO("Concluído");
    
    private String statusOrdemServico;

    private StatusOrdemServico(String statusOrdemServico) {
        this.statusOrdemServico = statusOrdemServico;
    }

    public String getStatusFatura() {
        return statusOrdemServico;
    }
    
    
    
}
