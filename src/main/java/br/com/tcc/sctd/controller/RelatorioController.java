/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.tcc.sctd.components.JasperMaker;
import br.com.tcc.sctd.dao.*;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final FaturaDao faturas;
    private final JasperMaker jasperMaker;
    private final Result result;
    private final VendaDao vendas;
    private final ItemVendaDao itensVenda;
    private final PedidoDao pedidos;
    private final ParcelaDao parcelas;
    private final ProdutoDao produtos;

    public RelatorioController(FaturaDao faturas, JasperMaker jasperMaker, Result result, VendaDao vendas, ItemVendaDao itensVenda,
            PedidoDao pedidos, ParcelaDao parcelas, ProdutoDao produtos) {
        this.faturas = faturas;
        this.jasperMaker = jasperMaker;
        this.result = result;
        this.vendas = vendas;
        this.itensVenda = itensVenda;
        this.pedidos = pedidos;
        this.parcelas = parcelas;
        this.produtos = produtos;
    }

    @Path("/")
    public void index() {
        LOG.debug("/relatorios/index");

    }

    public void alocacao() {
    }

    @Path("/faturas")
    public Download faturas() throws DaoException {
        List<Fatura> faturasList = faturas.buscarTodos();

        Map<String, Object> mapa = new HashMap<String, Object>();
        

        return jasperMaker.makePdf("RelFaturaEmAberto.jasper", faturasList, "relatorio_faturas.pdf", false, mapa);
    }

    @Path("/produtos/maisvendidos")
    public void formularioProdutoMaisVendidos() {
        result.include("data", new Date());
    }

    @Path("/produtos/maisvendidos/gerar")
    public Download gerarRelatorioProdutosMaisVendidos(Date dataInicial, Date dataFinal) throws DaoException {
//        List<Venda> vendaList = vendas.buscarPorDataDeVenda(dataInicial, dataFinal);
//
        Map<String, Object> mapa = new HashMap<String, Object>();
//
//        return jasperMaker.makePdf("RelProdMaisVendido.jasper", vendaList, "relatorio_faturas.pdf", false, mapa);
        
        List<ItemVenda> itens = itensVenda.buscarPorData(dataInicial, dataFinal);
        
        return jasperMaker.makePdf("ProdutosVendas.jasper", itens, "relatorio_itens.pdf", false, mapa);
    }
    
    @Path("/pagamentos")
    public Download gerarRelatorioPagamentos()throws DaoException {
        List<Parcela> parcelasList = parcelas.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("Pagamentos.jasper", parcelasList, "pagamentos.pdf", false, mapa);
    }
    
    /*
    @Path("/ordensProducao")
    public Download gerarRelatorioOrdensProducao()throws DaoException {
        List<Parcela> ordensProducao = ordemProducaoDAO.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("AndamentoProducao.jasper", ordensProducao, "ordensProducao.pdf", false, mapa);
    }
    */
    
    @Path("/pedidos")
    public Download gerarPedidos()throws DaoException {
        List<Pedido> pedidosList = pedidos.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("Pedidos.jasper", pedidosList, "pedidos.pdf", false, mapa);
    }
    
    @Path("/vendas")
    public Download gerarVendas()throws DaoException {
        List<Venda> vendasList = vendas.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("Vendas.jasper", vendasList, "vendas.pdf", false, mapa);
    }
    
    @Path("/estoque")
    public Download gerarEstoques()throws DaoException {
        List<Produto> produtoList = produtos.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("EstoqueDeProdutos.jasper", produtoList, "produtos.pdf", false, mapa);
    }
}
