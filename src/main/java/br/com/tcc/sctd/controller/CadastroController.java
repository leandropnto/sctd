/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros")
public class CadastroController {
    
    private static final Logger LOG = LoggerFactory.getLogger(CadastroController.class);

    public CadastroController() {
    }
    
    @Path("/")
    public void index(){
        LOG.debug("/cadastros/index");
        
    }
    
    
    
    
}
