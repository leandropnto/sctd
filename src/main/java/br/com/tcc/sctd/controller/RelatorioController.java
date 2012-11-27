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
import br.com.tcc.sctd.dao.FaturaDao;
import br.com.tcc.sctd.dao.VendaDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Fatura;
import br.com.tcc.sctd.model.Venda;
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

    public RelatorioController(FaturaDao faturas, JasperMaker jasperMaker, Result result, VendaDao vendas) {
        this.faturas = faturas;
        this.jasperMaker = jasperMaker;
        this.result = result;
        this.vendas = vendas;
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
        List<Venda> vendaList = vendas.buscarPorDataDeVenda(dataInicial, dataFinal);

        Map<String, Object> mapa = new HashMap<String, Object>();

        return jasperMaker.makePdf("RelProdMaisVendido.jasper", vendaList, "relatorio_faturas.pdf", false, mapa);
    }
}
