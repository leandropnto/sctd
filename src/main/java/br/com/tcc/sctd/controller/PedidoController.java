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
import br.com.tcc.sctd.constants.FormaPagamento;
import br.com.tcc.sctd.dao.*;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
    private final VendaDao vendas;
    private final PessoaFisicaDao pfs;
    private final PessoaJuridicaDao pjs;
    private final EnderecoDao enderecos;
    private final ClienteDao clientes;

    public PedidoController(Result result, Validator validator, ProdutoDao produtos, FuncionarioDao funcionarios, VendaDao vendas,
            PessoaFisicaDao pfs, PessoaJuridicaDao pjs, EnderecoDao enderecos, ClienteDao clientes) {
        this.result = result;
        this.validator = validator;
        this.produtos = produtos;
        this.funcionarios = funcionarios;
        this.vendas = vendas;
        this.pfs = pfs;
        this.pjs = pjs;
        this.enderecos = enderecos;
        this.clientes = clientes;
    }
    
    @Path("/")
    public void index(){
        LOG.debug("/pedido/index");
    }
    
    @Path("/venda")
    public void formularioVenda() throws DaoException{
        result.include("funcionarios", funcionarios.buscaFuncionariosAtivos());
        result.include("data", new Date(System.currentTimeMillis()));
        result.include("formasPagamento", FormaPagamento.values());
        
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
    
    @Path("/venda/buscacliente")
    public void buscarCliente(String term){
        LOG.debug("Buscando Cliente: " + term);
        List<Cliente> clienteList =  pfs.buscaPorCPF(term);
        
        if (clienteList == null || clienteList.isEmpty()){
            clienteList = pjs.buscaPorCNPJ(term);
        }
        
        
        result.use(json()).withoutRoot().from(clienteList).include("endereco").serialize();
    }
    
    
    @Path("/venda/registrar")
    public void registraVenda(Venda venda) throws DaoException{
        LOG.debug("/venda/registrar");
        BigDecimal totalVenda = new BigDecimal("0.00");
        totalVenda.setScale(2, RoundingMode.HALF_UP);
        List<ItemVenda> itens = venda.getItens();
        
        
        for (ItemVenda itemVenda : itens) {
            Produto p = produtos.buscarPorId(itemVenda.getProduto().getId());  
            BigDecimal calculado = new BigDecimal(p.getValor().multiply(new BigDecimal(itemVenda.getQuantidade().toString())).toString()); //p.getValor().multiply(new BigDecimal(itemVenda.getQuantidade().toString()));
            totalVenda = new BigDecimal(totalVenda.add(calculado).toString());
            itemVenda.setVenda(venda);
        }
        
        //Busca o cliente
        Cliente c = clientes.buscarPorId(venda.getCliente().getId());
        
        
        /*
         * Cadastrou um novo endereco, salva o endereco e atribui ao cliente
         */
        Endereco endereco = venda.getCliente().getEndereco();
        if (endereco != null && endereco.getId() == null){
            enderecos.salvar(endereco);
            c.setEndereco(endereco);
        }
        
        
        
        venda.setCliente(c);
        
        LOG.debug("Total da Venda: " + totalVenda);
        venda.setPrecoTotal(totalVenda);
        venda.setDataVenda(new Date(System.currentTimeMillis()));
        venda.setFuncionario(funcionarios.buscarPorId(venda.getFuncionario().getMatricula()));             
        
        vendas.salvar(venda);
        
     
        result.redirectTo(this).index();
        
    }
}
