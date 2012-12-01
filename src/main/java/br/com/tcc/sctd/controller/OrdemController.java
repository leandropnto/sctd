/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import static br.com.caelum.vraptor.view.Results.json;
import br.com.tcc.sctd.constants.StatusOrdemServico;
import br.com.tcc.sctd.dao.FuncionarioDao;
import br.com.tcc.sctd.dao.OrdemDao;
import br.com.tcc.sctd.dao.PedidoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.ItemPedido;
import br.com.tcc.sctd.model.OrdemServico;
import br.com.tcc.sctd.model.Pedido;
import java.util.Date;
import java.util.List;
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
    private final Validator validator;
    private static final int REG_POR_PAGINA = 20;
    private final PedidoDao pedidos;
    private final FuncionarioDao funcionarios;

    public OrdemController(Result result, OrdemDao ordens, Validator validator, PedidoDao pedidos, FuncionarioDao funcionarios) {
        this.result = result;
        this.ordens = ordens;
        this.validator = validator;
        this.pedidos = pedidos;
        this.funcionarios = funcionarios;
    }

    @Path("/")
    public void index() {
        LOG.debug("/pedido/ordem/index");
    }

    @Path("/incluirvenda")
    public void incluirOrdemVenda() throws DaoException {
        LOG.debug("/pedido/ordem/incluirvenda");
        OrdemServico ordem = new OrdemServico();
        ordem.setDataInicio(new Date(System.currentTimeMillis()));
        ordem.setStatus(StatusOrdemServico.ANDAMENTO);
        result.include("ordem", ordem);
        result.include("funcionarios", funcionarios.buscarTodos());

    }

    @Path("/incluirpedido")
    public void incluirOrdemPedido() throws DaoException {
        LOG.debug("/pedido/ordem/incluirpedido");
        OrdemServico ordem = new OrdemServico();
        ordem.setDataInicio(new Date(System.currentTimeMillis()));
        ordem.setStatus(StatusOrdemServico.ANDAMENTO);
        result.include("ordem", ordem);
        result.include("funcionarios", funcionarios.buscarTodos());
    }

    @Path("/salvarordemvenda")
    public void salvarOrdemVenda(final OrdemServico ordem) throws DaoException {
        validator.checking(new Validations() {

            {
                that(ordem != null && ordem.getProduto() != null && ordem.getProduto().getId() != null, "Produto", "ordem.produto.nao.informado");

            }
        });

        if (validator.hasErrors()) {
            LOG.debug("Erros de validação encontrados.");
        }

        validator.onErrorRedirectTo(this).incluirOrdemVenda();


        ordens.salvar(ordem);
        result.include("msg", "Ordem de Serviço Registrada com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/filtrar")
    public void filtrar(OrdemServico ordem) throws DaoException {
        LOG.debug("/pedidos/ordem/filtrar");
        Long qtdOrdens = ordens.qtdRegistros(ordem);
        Long qtdPaginas = qtdOrdens / REG_POR_PAGINA;
        qtdPaginas += (qtdOrdens % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("ordens", ordens.buscarPorExemplo(ordem));
        result.include("qtde", qtdOrdens);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    @Path("/buscarpedido")
    public void buscarPedido(String term) throws DaoException {
        Integer numPedido = null;
        try {
            numPedido = Integer.parseInt(term);
        } catch (Exception e) {
            validator.add(new ValidationMessage("Número do pedido inválido", "Pedido"));
        }

        validator.onErrorRedirectTo(this).incluirOrdemPedido();
        
        Pedido pedido = pedidos.buscarParaJson(numPedido);
        List<ItemPedido> itensList = pedido.getItens();
        
        if (pedido == null){
            validator.add(new ValidationMessage("Número do pedido inválido", "Pedido"));
        }

        result.use(json()).withoutRoot().from(itensList).include("produto").serialize();
    }
}
