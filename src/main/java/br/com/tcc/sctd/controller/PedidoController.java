/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import static br.com.caelum.vraptor.view.Results.json;
import br.com.tcc.sctd.dao.FuncionarioDao;
import br.com.tcc.sctd.dao.ProdutoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Produto;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lpinto
 */
@Resource
@Path("/pedidos")
public class PedidoController {
    private final Result result;
    private final Validator validator;
    private final static Logger LOG = LoggerFactory.getLogger(PedidoController.class);
    private final ProdutoDao produtos;
    private final FuncionarioDao funcionarios;

    public PedidoController(Result result, Validator validator, ProdutoDao produtos, FuncionarioDao funcionarios) {
        this.result = result;
        this.validator = validator;
        this.produtos = produtos;
        this.funcionarios = funcionarios;
    }
    
    @Path("/")
    public void index(){
        LOG.debug("/pedido/index");
    }
    
    @Path("/venda")
    public void formularioVenda() throws DaoException{
        result.include("funcionarios", funcionarios.buscaFuncionariosAtivos());
        result.include("data", new Date(System.currentTimeMillis()));
    }
    
    @Path("/venda/buscaproduto")
    public void buscarProdutos(String term){
        LOG.debug("Buscando produtos: " + term);
        List<Produto> prods = produtos.buscarPorNome(term);
        if (prods !=null && !prods.isEmpty()){
            LOG.debug("Produtos encontrados " + prods.size());
        }
        
        result.use(json()).withoutRoot().from(prods).serialize();
    }
}
