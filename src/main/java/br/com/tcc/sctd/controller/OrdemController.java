/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.tcc.sctd.constants.StatusOrdemServico;
import br.com.tcc.sctd.dao.OrdemDao;
import br.com.tcc.sctd.model.OrdemServico;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Path("/pedidos/ordem")
@Resource
public class OrdemController {
    
    private static final Logger LOG = LoggerFactory.getLogger(OrdemController.class);
    private final Result result;
    private final OrdemDao ordens;

    public OrdemController(Result result, OrdemDao ordens) {
        this.result = result;
        this.ordens = ordens;
    }
    
    @Path("/")
    public void index(){
        LOG.debug("/pedido/ordem/index");
    }
    
    
    @Path("/incluir")
    public void incluir(){
        LOG.debug("/botoes/incluir");
        OrdemServico ordem = new OrdemServico();
        ordem.setDataInicio(new Date(System.currentTimeMillis()));
        ordem.setStatus(StatusOrdemServico.ANDAMENTO);
        result.include("ordem", ordem);
        
    }
    
    
}
