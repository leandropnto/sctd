/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.constants;

/**
 *
 * @author lpinto
 */
public enum StatusFuncionario {
    ALOCADO("Alocado"), DESALOCADO("Desalocado"), LICENCA("Licença"), DESLIGADO("Desligado");
    
    private String statusFuncionario;

    private StatusFuncionario(String sattusFuncionario) {
        this.statusFuncionario = sattusFuncionario;
    }

    @Override
    public String toString() {
        return statusFuncionario;
    }
    
    
}
