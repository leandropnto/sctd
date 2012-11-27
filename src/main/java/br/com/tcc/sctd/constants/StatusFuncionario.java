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
    ATIVO("Ativo"), INATIVO("Inativo"), DESALOCADO("Desalocado");
    
    private String statusFuncionario;

    private StatusFuncionario(String sattusFuncionario) {
        this.statusFuncionario = sattusFuncionario;
    }

    @Override
    public String toString() {
        return statusFuncionario;
    }
    
    
}
