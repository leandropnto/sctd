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
import br.com.tcc.sctd.dto.ProdutoDto;
import br.com.tcc.sctd.dto.TipoDto;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.*;
import java.math.BigDecimal;
import java.util.*;
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
    private final ItemPedidoDao itensPedido;
    private final PedidoDao pedidos;
    private final ParcelaDao parcelas;
    private final ProdutoDao produtos;

    public RelatorioController(FaturaDao faturas, JasperMaker jasperMaker, Result result, VendaDao vendas, ItemVendaDao itensVenda,
            ItemPedidoDao itensPedido, PedidoDao pedidos, ParcelaDao parcelas, ProdutoDao produtos) {
        this.faturas = faturas;
        this.jasperMaker = jasperMaker;
        this.result = result;
        this.vendas = vendas;
        this.itensVenda = itensVenda;
        this.itensPedido = itensPedido;
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
        Map<String, Object> mapa = new HashMap<String, Object>();

        List<ItemVenda> listVendas = itensVenda.buscarPorData(dataInicial, dataFinal);
        List<ItemPedido> listPedidos = itensPedido.buscarPorData(dataInicial, dataFinal);
        Long quantidadePorDataVenda = vendas.quantidadePorDataVenda(dataInicial, dataFinal);
        Long quantidadePorDataPedido = pedidos.quantidadePorDataPedido(dataInicial, dataFinal);
        int feminino = 0;
        int masculino = 0;

        List<TipoDto> tipos = new ArrayList<TipoDto>();

        List<ProdutoDto> listaProdutos = new ArrayList<ProdutoDto>();

        /*
         * Venda
         */
        for (ItemVenda item : listVendas) {
            /*
             * Monta o dto do tipo
             */
            ProdutoDto p = new ProdutoDto();
            p.setId(item.getProduto().getId());
            p.setNome(item.getProduto().getNome());
            p.setQuantidade(item.getQuantidade());
            p.setValorUnitario(item.getProduto().getValor());
            p.setValorTotal(item.getProduto().getValor().multiply(new BigDecimal(String.valueOf(item.getQuantidade()))));

            if (item.getProduto().getModelo().getSexo().equals('F')) {
                feminino += item.getQuantidade();
            } else if (item.getProduto().getModelo().getSexo().equals('M')) {
                masculino += item.getQuantidade();
            }

            TipoDto tipo = new TipoDto();
            tipo.setId(item.getProduto().getModelo().getTipo().getId());
            tipo.setNome(item.getProduto().getModelo().getTipo().getNome());
            tipo.setQuantidade(item.getQuantidade());

            /*
             * Se a lista nao contém o dto, adiciona os dtos de produto e tipo
             */
            if (!listaProdutos.contains(p)) {
                listaProdutos.add(p);

            } else {
                /*
                 * Senao, incrementa quantidades e valores para os dtos
                 */
                ProdutoDto dto = listaProdutos.get(listaProdutos.indexOf(p));
                dto.setQuantidade(dto.getQuantidade() + p.getQuantidade());
                dto.setValorTotal(p.getValorUnitario().multiply(new BigDecimal(String.valueOf(dto.getQuantidade()))));


            }

            if (!tipos.contains(tipo)) {
                tipos.add(tipo);
            } else {
                TipoDto dtot = tipos.get(tipos.indexOf(tipo));
                dtot.setQuantidade(dtot.getQuantidade() + tipo.getQuantidade());
            }
        }


        /*
         * Pedido
         */
        for (ItemPedido item : listPedidos) {
            /*
             * Monta o dto do tipo
             */
            ProdutoDto p = new ProdutoDto();
            p.setId(item.getProduto().getId());
            p.setNome(item.getProduto().getNome());
            p.setQuantidade(item.getQuantidade());
            p.setValorUnitario(item.getProduto().getValor());
            p.setValorTotal(item.getProduto().getValor().multiply(new BigDecimal(String.valueOf(item.getQuantidade()))));

            if (item.getProduto().getModelo().getSexo().equals('F')) {
                feminino += item.getQuantidade();
            } else if (item.getProduto().getModelo().getSexo().equals('M')) {
                masculino += item.getQuantidade();
            }

            TipoDto tipo = new TipoDto();
            tipo.setId(item.getProduto().getModelo().getTipo().getId());
            tipo.setNome(item.getProduto().getModelo().getTipo().getNome());
            tipo.setQuantidade(item.getQuantidade());

            /*
             * Se a lista nao contém o dto, adiciona os dtos de produto e tipo
             */
            if (!listaProdutos.contains(p)) {
                listaProdutos.add(p);

            } else {
                /*
                 * Senao, incrementa quantidades e valores para os dtos
                 */
                ProdutoDto dto = listaProdutos.get(listaProdutos.indexOf(p));
                dto.setQuantidade(dto.getQuantidade() + p.getQuantidade());
                dto.setValorTotal(p.getValorUnitario().multiply(new BigDecimal(String.valueOf(dto.getQuantidade()))));


            }

            if (!tipos.contains(tipo)) {
                tipos.add(tipo);
            } else {
                TipoDto dtot = tipos.get(tipos.indexOf(tipo));
                dtot.setQuantidade(dtot.getQuantidade() + tipo.getQuantidade());
            }
        }

        vendas.quantidadePorDataVenda(dataInicial, dataFinal);
        pedidos.quantidadePorDataPedido(dataInicial, dataFinal);

        mapa.put("venda", quantidadePorDataVenda);
        mapa.put("pedido", quantidadePorDataPedido);
        mapa.put("dataInicial", dataInicial);
        mapa.put("dataFinal", dataFinal);



        /*
         * Ordena tipos
         */
        Collections.sort(tipos, new Comparator() {

            @Override
            public int compare(Object t, Object t1) {
                TipoDto tipo1 = (TipoDto) t;
                TipoDto tipo2 = (TipoDto) t1;
                return tipo1.getQuantidade() < tipo2.getQuantidade() ? +1 : (tipo1.getQuantidade() > tipo2.getQuantidade() ? -1 : 0);
            }
        });


        /*
         * Ordena produtos
         */
        Collections.sort(listaProdutos, new Comparator() {

            @Override
            public int compare(Object t, Object t1) {
                ProdutoDto prod1 = (ProdutoDto) t;
                ProdutoDto prod2 = (ProdutoDto) t1;
                return prod1.getQuantidade() < prod2.getQuantidade() ? +1 : (prod1.getQuantidade() > prod2.getQuantidade() ? -1 : 0);
            }
        });

        for (int j = 0; j < 5; j++) {
            try {
                TipoDto t = tipos.get(j);
                mapa.put("tiposMaisVendidosNome" + j, t.getNome());
                mapa.put("tiposMaisVendidosQuantidade" + j, t.getQuantidade());
            } catch (IndexOutOfBoundsException e) {
                mapa.put("tiposMaisVendidosNome" + j, "");
                mapa.put("tiposMaisVendidosQuantidade" + j, null);
            }

        }

        mapa.put("tiposMaisVendidos", tipos);
        mapa.put("feminino", feminino);
        mapa.put("masculino", masculino);


        return jasperMaker.makePdf("ProdutosMaisVendidos.jasper", listaProdutos, "mais_vendidos.pdf", false, mapa);
    }

    @Path("/pagamentos")
    public Download gerarRelatorioPagamentos() throws DaoException {
        List<Parcela> parcelasList = parcelas.buscarPagos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("Pagamentos.jasper", parcelasList, "pagamentos.pdf", false, mapa);
    }

    /*
     * @Path("/ordensProducao") public Download
     * gerarRelatorioOrdensProducao()throws DaoException { List<Parcela>
     * ordensProducao = ordemProducaoDAO.buscarTodos(); Map<String, Object> mapa
     * = new HashMap<String, Object>(); return
     * jasperMaker.makePdf("AndamentoProducao.jasper", ordensProducao,
     * "ordensProducao.pdf", false, mapa); }
     */
    @Path("/pedidos")
    public Download gerarPedidos() throws DaoException {
        List<Pedido> pedidosList = pedidos.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("Pedidos.jasper", pedidosList, "pedidos.pdf", false, mapa);
    }

    @Path("/vendas")
    public Download gerarVendas() throws DaoException {
        List<Venda> vendasList = vendas.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("Vendas.jasper", vendasList, "vendas.pdf", false, mapa);
    }

    @Path("/estoque")
    public Download gerarEstoques() throws DaoException {
        List<Produto> produtoList = produtos.buscarTodos();

        List<Produto> produtoListFinal = new ArrayList<Produto>();
        for (Produto p : produtoList) {
            if (p.getQuantidade() > 0) {
                produtoListFinal.add(p);
            }
        }

        Map<String, Object> mapa = new HashMap<String, Object>();
        return jasperMaker.makePdf("EstoqueDeProdutos.jasper", produtoListFinal, "produtos.pdf", false, mapa);
    }
}
