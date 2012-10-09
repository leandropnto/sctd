/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.service;

/**
 *
 * @author LeandroVBOX
 */
public class Opcoes {
    private String link;
    private String descricao;

    public Opcoes() {
    }

    public Opcoes(String link, String descricao) {
        this.link = link;
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
