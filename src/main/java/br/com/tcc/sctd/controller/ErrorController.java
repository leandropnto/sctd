/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 *
 * @author leandro
 */
@Resource
public class ErrorController {
    private final Result result;

    public ErrorController(Result result) {
        this.result = result;
    }
    
    
    public void mensagem(String msg){
        result.include("msg", msg);
    }
    
}
