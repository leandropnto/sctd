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
@Path("/relatorios")
public class RelatorioController {
    
    private Logger LOG = LoggerFactory.getLogger(RelatorioController.class);
    
    @Path("/")
    public void index(){
        LOG.debug("/relatorios/index");
        
    }
    
    public void alocacao(){
        
    }
    
}
