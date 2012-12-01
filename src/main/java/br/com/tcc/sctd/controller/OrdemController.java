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
import br.com.tcc.sctd.constants.StatusItemPedido;
import br.com.tcc.sctd.constants.StatusOrdemServico;
import br.com.tcc.sctd.constants.StatusPedido;
import br.com.tcc.sctd.dao.FuncionarioDao;
import br.com.tcc.sctd.dao.ItemPedidoDao;
import br.com.tcc.sctd.dao.OrdemDao;
import br.com.tcc.sctd.dao.PedidoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.ItemPedido;
import br.com.tcc.sctd.model.OrdemServico;
import br.com.tcc.sctd.model.Pedido;
import br.com.tcc.sctd.model.Produto;
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
    private final ItemPedidoDao itens;

    public OrdemController(Result result, OrdemDao ordens, Validator validator, PedidoDao pedidos, FuncionarioDao funcionarios,
            ItemPedidoDao itens) {
        this.result = result;
        this.ordens = ordens;
        this.validator = validator;
        this.pedidos = pedidos;
        this.funcionarios = funcionarios;
        this.itens = itens;
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
                that(ordem != null && ordem.getProduto() != null && ordem.getProduto().getId() != null,
                        "Produto", "ordem.produto.nao.informado");

            }
        });

        validator.onErrorRedirectTo(this).incluirOrdemVenda();

        ordens.salvar(ordem);

        result.include("msg", "Ordem de Serviço Registrada com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/salvarordempedido")
    public void salvarOrdemPedido(final OrdemServico ordem) throws DaoException {
        validator.checking(new Validations() {

            {
                that(ordem != null && ordem.getProduto() != null && ordem.getProduto().getId() != null,
                        "Produto", "ordem.produto.nao.informado");
                that(ordem != null && ordem.getItem() != null && ordem.getItem().getId() != null,
                        "Produto", "ordem.item.nao.informado");

            }
        });


        validator.onErrorRedirectTo(this).incluirOrdemPedido();



        ItemPedido item = itens.buscarPorId(ordem.getItem().getId());
        StatusItemPedido statusItem = item.getStatus();
        StatusPedido statusPedido = item.getPedido().getStatus();
        if (statusItem == StatusItemPedido.CONCLUIDO) {
            validator.add(new ValidationMessage("Não é possível gerar uma ordem de serviço porque o item esta " + statusItem,
                    "Item de Pedido"));
        } else if (statusPedido == StatusPedido.CONCLUIDO || statusPedido == StatusPedido.ENTREGUE) {
            validator.add(new ValidationMessage("Não é possível gerar uma ordem de serviço porque o pedido esta " + statusPedido,
                    "Item de Pedido"));
        } else {
            item.getPedido().setStatus(StatusPedido.ANDAMENTO);
        }

        validator.onErrorRedirectTo(this).incluirOrdemPedido();

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

        if (pedido == null) {
            validator.add(new ValidationMessage("Número do pedido inválido", "Pedido"));
        }

        result.use(json()).withoutRoot().from(itensList).include("produto").serialize();
    }

    @Path("/baixar")
    public void formularioBaixaOrdemServico() {
        LOG.debug("/baixar");
    }

    @Path("/registrarbaixa")
    public void gerarBaixaOrdemServico(OrdemServico ordem) throws DaoException {
        if (ordem.getId() == null) {
            validator.add(new ValidationMessage("Informe o número da Ordem de Serviço.", "Ordem de Serviço"));
        }

        validator.onErrorRedirectTo(this).formularioBaixaOrdemServico();

        OrdemServico ordemRecuperada = ordens.buscarPorId(ordem.getId());

        if (ordemRecuperada == null) {
            validator.add(new ValidationMessage("Ordem de Serviço não encontrada.", "Ordem de Serviço"));
        }

        validator.onErrorRedirectTo(this).formularioBaixaOrdemServico();
        ItemPedido item = ordemRecuperada.getItem();
        Produto produto = ordemRecuperada.getProduto();

        LOG.debug("Atualizando quantidade do produto de " + produto.getQuantidade() + " para " + produto.getQuantidade()
                + ordemRecuperada.getQuantidade());
        produto.setQuantidade(produto.getQuantidade() + ordemRecuperada.getQuantidade());

        if (item != null) {
            LOG.debug("Atualizando status/quantidade do item.");

            Integer qtdPronta = item.getQuantidadePronta() != null ? item.getQuantidadePronta() : 0;
            item.setQuantidadePronta(qtdPronta + ordemRecuperada.getQuantidade());
            if (item.getQuantidadePronta() >= item.getQuantidade()) {
                item.setStatus(StatusItemPedido.CONCLUIDO);
            }
            Pedido pedido = item.getPedido();
            boolean existeItemEmProducao = pedidos.existeItemEmProducao(pedido);

            if (!existeItemEmProducao) {
                pedido.setStatus(StatusPedido.CONCLUIDO);
            } 


        }

        ordemRecuperada.setStatus(StatusOrdemServico.CONCLUIDO);
        ordens.atualizar(ordemRecuperada);

        result.include("msg", "Ordem de serviço concluída.");
        result.redirectTo(this).formularioBaixaOrdemServico();
    }
    
    
}
