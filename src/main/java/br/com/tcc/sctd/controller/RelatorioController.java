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
import br.com.tcc.sctd.constants.StatusFuncionario;
import br.com.tcc.sctd.constants.StatusItemPedido;
import br.com.tcc.sctd.dao.*;
import br.com.tcc.sctd.dto.DepartamentoDto;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    private final FuncionarioDao funcionarios;

    public RelatorioController(FaturaDao faturas, JasperMaker jasperMaker, Result result, VendaDao vendas, ItemVendaDao itensVenda,
            ItemPedidoDao itensPedido, PedidoDao pedidos, ParcelaDao parcelas, ProdutoDao produtos, FuncionarioDao funcionarios) {
        this.faturas = faturas;
        this.jasperMaker = jasperMaker;
        this.result = result;
        this.vendas = vendas;
        this.itensVenda = itensVenda;
        this.itensPedido = itensPedido;
        this.pedidos = pedidos;
        this.parcelas = parcelas;
        this.produtos = produtos;
        this.funcionarios = funcionarios;
    }

    @Path("/")
    public void index() {
        LOG.debug("/relatorios/index");

    }

    @Path("/alocacao")
    public Download alocacao() throws Exception {
        Map<String, Object> mapa = new HashMap<String, Object>();
        ArrayList<DepartamentoDto> list = new ArrayList<DepartamentoDto>();
        List<Funcionario> funcionariosList = funcionarios.buscarTodos();

        Integer quantDesativos = 0;
        BigDecimal salario = new BigDecimal(0);
        HashMap<String, Integer> cargos = new HashMap<String, Integer>();
        HashMap<String, Integer> especialidades = new HashMap<String, Integer>();

        HashMap<String, DepartamentoDto> map = new HashMap<String, DepartamentoDto>();

        for (Funcionario f : funcionariosList) {
            if (!f.getStatus().equals(StatusFuncionario.DESALOCADO)) {

                if (!map.containsKey(f.getDepartamento().getDescricao())) {
                    DepartamentoDto d = new DepartamentoDto(f.getDepartamento().getDescricao(), 0, 0, 0, new BigDecimal(0));
                    map.put(f.getDepartamento().getDescricao(), d);
                }

                Integer q = map.get(f.getDepartamento().getDescricao()).getQuantidade();
                Integer a = map.get(f.getDepartamento().getDescricao()).getAtivos();
                Integer i = map.get(f.getDepartamento().getDescricao()).getInativos();
                BigDecimal s = map.get(f.getDepartamento().getDescricao()).getSalarios();

                q++;
                if (f.getStatus().equals(StatusFuncionario.ATIVO)) {
                    a++;
                } else {
                    i++;
                }
                s = s.add(f.getSalario());

                map.put(f.getDepartamento().getDescricao(), new DepartamentoDto(f.getDepartamento().getDescricao(), q, a, i, s));

                if (cargos.containsKey(f.getCargo().getDescricao())) {
                    Integer c = cargos.get(f.getCargo().getDescricao());
                    c++;
                    cargos.put(f.getCargo().getDescricao(), c);
                } else {
                    cargos.put(f.getCargo().getDescricao(), 1);
                }

                for (TipoEspecialidade t : f.getTipoEspecialidades()) {
                    if (especialidades.containsKey(t.getNome())) {
                        Integer e = especialidades.get(t.getNome());
                        e++;
                        especialidades.put(t.getNome(), e);
                    } else {
                        especialidades.put(t.getNome(), 1);
                    }
                }
                salario = salario.add(f.getSalario());
            } else {
                quantDesativos++;
            }
        }

        ArrayList<Integer> cargoQuant = new ArrayList<Integer>(cargos.values());
        ArrayList<Integer> espQuant = new ArrayList<Integer>(especialidades.values());

        Collections.sort(cargoQuant);
        Collections.reverse(cargoQuant);
        Collections.sort(espQuant);
        Collections.reverse(espQuant);

        ArrayList<String> cargoList = new ArrayList<String>(Arrays.asList("", "", "", "", ""));
        ArrayList<String> espList = new ArrayList<String>(Arrays.asList("", "", "", "", ""));

        for (String s : cargos.keySet()) {
            for (int i = 0; i < cargoQuant.size(); i++) {
                if (cargos.get(s).equals(cargoQuant.get(i))) {
                    if (cargoList.get(i).equals("")) {
                        cargoList.set(i, s);
                    } else {
                        cargoList.set(i, cargoList.get(i) + " / " + s);
                    }
                }
            }
        }

        for (String s : especialidades.keySet()) {
            for (int i = 0; i < espQuant.size(); i++) {
                if (especialidades.get(s).equals(espQuant.get(i))) {
                    if (espList.get(i).equals("")) {
                        espList.set(i, s);
                    } else {
                        espList.set(i, espList.get(i) + " / " + s);
                    }
                }
            }
        }

        if (cargoQuant.size() < 5) {
            int count = 5 - cargoQuant.size();
            for (int i = 0; i < count; i++) {
                cargoQuant.add(0);
            }
        }
        if (espQuant.size() < 5) {
            int count = 5 - espQuant.size();
            for (int i = 0; i < count; i++) {
                espQuant.add(0);
            }
        }

        Collections.sort(list, new Comparator() {

            @Override
            public int compare(Object t, Object t1) {
                DepartamentoDto tipo1 = (DepartamentoDto) t;
                DepartamentoDto tipo2 = (DepartamentoDto) t1;
                return tipo1.getQuantidade() < tipo2.getQuantidade() ? 1 : (tipo1.getQuantidade() > tipo2.getQuantidade() ? -1 : 0);
            }
        });

        mapa.put("desativos", quantDesativos);
        mapa.put("salario", salario);
        mapa.put("cargoList", cargoList);
        mapa.put("cargoQuant", cargoQuant);
        mapa.put("espList", espList);
        mapa.put("espQuant", espQuant);

        list.addAll(map.values());
        return jasperMaker.makePdf("Funcionarios.jasper", list, "funcionarios.pdf", false, mapa);
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

        List<Produto> listProduto = new ArrayList<Produto>();
        Integer masculino = 0;
        Integer feminino = 0;
        Integer unisex = 0;
        Integer pequeno = 0;
        Integer medio = 0;
        Integer grande = 0;
        Integer venda = 0;
        Integer pedido = 0;

        Long quantidadeTotal = new Long(0);
        BigDecimal valorTotal = new BigDecimal(0);

        Produto prod = new Produto();

        for (ItemVenda i : listVendas) {
            if (!listProduto.contains(i.getProduto())) {
                prod = i.getProduto();
                prod.setQuantidade(i.getQuantidade());
                listProduto.add(prod);
            } else {
                int posicao = listProduto.indexOf(i.getProduto());
                prod = listProduto.get(posicao);
                prod.setQuantidade(prod.getQuantidade() + i.getQuantidade());
                listProduto.set(posicao, prod);
            }
            venda += i.getQuantidade();
            quantidadeTotal += i.getQuantidade();
            valorTotal = valorTotal.add(prod.getValor().multiply(new BigDecimal(i.getQuantidade())));
        }

        for (ItemPedido i : listPedidos) {
            if (!listProduto.contains(i.getProduto())) {
                prod = i.getProduto();
                prod.setQuantidade(i.getQuantidade());
                listProduto.add(prod);
            } else if (!i.getStatus().equals(StatusItemPedido.FECHADA)) {
                int posicao = listProduto.indexOf(i.getProduto());
                prod = listProduto.get(posicao);
                prod.setQuantidade(prod.getQuantidade() + i.getQuantidade());
                listProduto.set(posicao, prod);
            }
            pedido += i.getQuantidade();
            quantidadeTotal += i.getQuantidade();
            valorTotal = valorTotal.add(prod.getValor().multiply(new BigDecimal(i.getQuantidade())));
        }

        Collections.sort(listProduto, new Comparator() {

            @Override
            public int compare(Object t, Object t1) {
                Produto tipo1 = (Produto) t;
                Produto tipo2 = (Produto) t1;
                return tipo1.getQuantidade() < tipo2.getQuantidade() ? 1 : (tipo1.getQuantidade() > tipo2.getQuantidade() ? -1 : 0);
            }
        });

        HashMap<String, Integer> mapTipo = new HashMap<String, Integer>();
        for (Produto p : listProduto) {
            if (mapTipo.isEmpty()) {
                mapTipo.put(p.getModelo().getTipo().getNome(), p.getQuantidade());
                if (p.getModelo().getSexo() != null) {
                    if (p.getModelo().getSexo().equals('M')) {
                        masculino += p.getQuantidade();
                    } else if (p.getModelo().getSexo().equals('F')) {
                        feminino += p.getQuantidade();
                    } else {
                        unisex += p.getQuantidade();
                    }
                }
                if (p.getModelo().getTamanho() != null) {
                    if (p.getModelo().getTamanho().equals('P')) {
                        pequeno += p.getQuantidade();
                    }
                    if (p.getModelo().getTamanho().equals('M')) {
                        medio += p.getQuantidade();
                    }
                    if (p.getModelo().getTamanho().equals('G')) {
                        grande += p.getQuantidade();
                    }
                }
            } else {
                if (mapTipo.containsKey(p.getModelo().getTipo().getNome())) {
                    Integer q = mapTipo.get(p.getModelo().getTipo().getNome()) + p.getQuantidade();
                    mapTipo.put(p.getModelo().getTipo().getNome(), q);
                    if (p.getModelo().getSexo() != null) {
                        if (p.getModelo().getSexo().equals('M')) {
                            masculino += p.getQuantidade();
                        } else if (p.getModelo().getSexo().equals('F')) {
                            feminino += p.getQuantidade();
                        } else {
                            unisex += p.getQuantidade();
                        }
                    }
                    if (p.getModelo().getTamanho() != null) {
                        if (p.getModelo().getTamanho().equals('P')) {
                            pequeno += p.getQuantidade();
                        }
                        if (p.getModelo().getTamanho().equals('M')) {
                            medio += p.getQuantidade();
                        }
                        if (p.getModelo().getTamanho().equals('G')) {
                            grande += p.getQuantidade();
                        }
                    }
                } else {
                    mapTipo.put(p.getModelo().getTipo().getNome(), p.getQuantidade());
                    if (p.getModelo().getSexo() != null) {
                        if (p.getModelo().getSexo().equals('M')) {
                            masculino += p.getQuantidade();
                        } else if (p.getModelo().getSexo().equals('F')) {
                            feminino += p.getQuantidade();
                        } else {
                            unisex += p.getQuantidade();
                        }
                    }
                    if (p.getModelo().getTamanho() != null) {
                        if (p.getModelo().getTamanho().equals('P')) {
                            pequeno += p.getQuantidade();
                        }
                        if (p.getModelo().getTamanho().equals('M')) {
                            medio += p.getQuantidade();
                        }
                        if (p.getModelo().getTamanho().equals('G')) {
                            grande += p.getQuantidade();
                        }
                    }
                }
            }
        }

        ArrayList<Integer> quant = new ArrayList<Integer>(mapTipo.values());

        Collections.sort(quant);
        Collections.reverse(quant);

        ArrayList<Integer> quantidade = new ArrayList<Integer>();
        if (quant.size() > 5) {
            quantidade.addAll(quant.subList(0, 5));
        } else {
            quantidade.addAll(quant);
        }

        ArrayList<String> tiposMaisVendidos = new ArrayList<String>(Arrays.asList("", "", "", "", ""));
        for (String s : mapTipo.keySet()) {
            for (int i = 0; i < quantidade.size(); i++) {
                if (mapTipo.get(s).equals(quantidade.get(i))) {
                    if (tiposMaisVendidos.get(i).equals("")) {
                        tiposMaisVendidos.set(i, s);
                    } else {
                        tiposMaisVendidos.set(i, tiposMaisVendidos.get(i) + " / " + s);
                    }
                }
            }
        }

        ArrayList<String> tiposQuantidade = new ArrayList<String>(Arrays.asList("", "", "", "", ""));
        for (int i = 0; i < quantidade.size(); i++) {
            tiposQuantidade.set(quantidade.indexOf(quantidade.get(i)), quantidade.get(i).toString());
        }

        mapa.put("dataInicial", dataInicial);
        mapa.put("dataFinal", dataFinal);
        mapa.put("tiposMaisVendidos", tiposMaisVendidos);
        mapa.put("tiposQuantidade", tiposQuantidade);
        mapa.put("masculino", masculino);
        mapa.put("feminino", feminino);
        mapa.put("unisex", unisex);
        mapa.put("pequeno", pequeno);
        mapa.put("medio", medio);
        mapa.put("grande", grande);
        mapa.put("venda", venda);
        mapa.put("pedido", pedido);
        mapa.put("valorTotal", valorTotal);
        mapa.put("quantidadeTotal", quantidadeTotal);
        return jasperMaker.makePdf("ProdutosMaisVendidos.jasper", listProduto.size() > 10 ? listProduto.subList(0, 10) : listProduto, "mais_vendidos.pdf", false, mapa);
    }

    @Path("/pagamentos")
    public Download gerarRelatorioPagamentos() throws DaoException, Exception {
        List<Parcela> parcelasList = parcelas.buscarTodos();
        Map<String, Object> mapa = new HashMap<String, Object>();

        /*
         * ============================== FORMATAÇÃO DOS DADOS DO RELATÓRIO
         * ==============================
         */
        String mes1 = "";
        String mes2 = "";
        String mes3 = "";
        String mesAnt1 = "";
        String mesAnt2 = "";
        String mesAnt3 = "";

        BigDecimal totalRecebido3 = new BigDecimal(0);
        BigDecimal totalRecebido2 = new BigDecimal(0);
        BigDecimal totalRecebido1 = new BigDecimal(0);
        BigDecimal totalRecebidoMesAtual = new BigDecimal(0);

        BigDecimal totalReceber5 = new BigDecimal(0);
        BigDecimal totalReceber15 = new BigDecimal(0);
        BigDecimal totalReceber30 = new BigDecimal(0);
        BigDecimal totalReceberMesAtual = new BigDecimal(0);
        BigDecimal totalReceberMes1 = new BigDecimal(0);
        BigDecimal totalReceberMes2 = new BigDecimal(0);
        BigDecimal totalReceberMes3 = new BigDecimal(0);
        BigDecimal totalReceber = new BigDecimal(0);

        BigDecimal totalAtraso5 = new BigDecimal(0);
        BigDecimal totalAtraso15 = new BigDecimal(0);
        BigDecimal totalAtraso30 = new BigDecimal(0);
        BigDecimal totalAtraso = new BigDecimal(0);

        Double pag1 = 0.0;
        Double atraso1 = 0.0;
        Double pag2 = 0.0;
        Double atraso2 = 0.0;
        Double pag3 = 0.0;
        Double atraso3 = 0.0;
        Double pagTotal = 0.0;
        Double atrasoTotal = 0.0;

        Double perc1 = 0.0;
        Double perc2 = 0.0;
        Double perc3 = 0.0;
        Double percTotal = 0.0;

        ArrayList<Parcela> pagas = new ArrayList<Parcela>();
        ArrayList<Parcela> atraso = new ArrayList<Parcela>();
        ArrayList<Parcela> aberto = new ArrayList<Parcela>();

        /*
         * ============================== CAPTURA DAS DATAS
         * ==============================
         */
        Date hoje = new Date();
        Integer d = Integer.valueOf(formatDate(hoje).split("/")[0]);
        Integer m = Integer.valueOf(formatDate(hoje).split("/")[1]) - 1;
        Integer y = Integer.valueOf(formatDate(hoje).split("/")[2]);

        Calendar cAnt4Mes = Calendar.getInstance();
        cAnt4Mes.set(y, m, 1);
        cAnt4Mes.add(Calendar.MONTH, -4);
        Date ant4Mes = cAnt4Mes.getTime();

        Calendar c1 = Calendar.getInstance();
        c1.set(y, m, 1);
        c1.add(Calendar.MONTH, -3);
        Date ant3Mes = c1.getTime();
        mesAnt3 = formatMounthExtensive(c1.getTime());

        Calendar c2 = Calendar.getInstance();
        c2.set(y, m, 1);
        c2.add(Calendar.MONTH, -2);
        Date ant2Mes = c2.getTime();
        mesAnt2 = formatMounthExtensive(c2.getTime());

        Calendar c3 = Calendar.getInstance();
        c3.set(y, m, 1);
        c3.add(Calendar.MONTH, -1);
        Date ant1Mes = c3.getTime();
        mesAnt1 = formatMounthExtensive(c3.getTime());

        Calendar cAnt30Dias = Calendar.getInstance();
        cAnt30Dias.set(y, m, d);
        cAnt30Dias.add(Calendar.DAY_OF_MONTH, -30);
        Date ant30Dias = cAnt30Dias.getTime();

        Calendar c4 = Calendar.getInstance();
        c4.set(y, m, d);
        c4.add(Calendar.DAY_OF_MONTH, -15);
        Date ant15Dias = c4.getTime();

        Calendar c5 = Calendar.getInstance();
        c5.set(y, m, d);
        c5.add(Calendar.DAY_OF_MONTH, -5);
        Date ant5Dias = c5.getTime();

        Calendar c6 = Calendar.getInstance();
        c6.set(y, m, 1);
        c6.add(Calendar.MONTH, 4);
        Date pos4Mes = c6.getTime();

        Calendar c7 = Calendar.getInstance();
        c7.set(y, m, 1);
        c7.add(Calendar.MONTH, 3);
        Date pos3Mes = c7.getTime();
        mes3 = formatMounthExtensive(c7.getTime());

        Calendar c8 = Calendar.getInstance();
        c8.set(y, m, 1);
        c8.add(Calendar.MONTH, 2);
        Date pos2Mes = c8.getTime();
        mes2 = formatMounthExtensive(c8.getTime());

        Calendar c9 = Calendar.getInstance();
        c9.set(y, m, 1);
        c9.add(Calendar.MONTH, 1);
        Date pos1Mes = c9.getTime();
        mes1 = formatMounthExtensive(c9.getTime());

        Calendar c10 = Calendar.getInstance();
        c10.set(y, m, d);
        c10.add(Calendar.DAY_OF_MONTH, 30);
        Date pos30Dias = c10.getTime();

        Calendar c11 = Calendar.getInstance();
        c11.set(y, m, d);
        c11.add(Calendar.DAY_OF_MONTH, 15);
        Date pos15Dias = c11.getTime();

        Calendar c12 = Calendar.getInstance();
        c12.set(y, m, d);
        c12.add(Calendar.DAY_OF_MONTH, 5);
        Date pos5Dias = c12.getTime();

        /*
         * ============================== CAPTURA DOS DADOS
         * ==============================
         */
        for (Parcela p : parcelasList) {
            if (p.getDataPagamento() != null) {
                pagas.add(p);

                if (p.getDataPagamento().before(ant3Mes)) {
                    if (p.getDataPagamento().after(new GregorianCalendar(y, m, 1).getGregorianChange())) {
                        totalRecebidoMesAtual = totalRecebidoMesAtual.add(p.getValor());
                    } else if (p.getDataPagamento().after(ant1Mes)) {
                        totalRecebido3 = totalRecebido3.add(p.getValor());
                    } else if (p.getDataPagamento().after(ant2Mes)) {
                        totalRecebido2 = totalRecebido2.add(p.getValor());
                    } else {
                        totalRecebido1 = totalRecebido1.add(p.getValor());
                    }
                }

                if (p.getDataVencimento().after(ant4Mes) && p.getDataVencimento().before(ant3Mes)) {
                    pag1 += 1;
                    if (p.getDataPagamento().after(p.getDataVencimento())) {
                        atraso3++;
                    }
                } else if (p.getDataPagamento().after(ant3Mes) && p.getDataPagamento().before(ant2Mes)) {
                    pag2++;
                    if (p.getDataPagamento().after(p.getDataVencimento())) {
                        atraso2++;
                    }
                } else if (p.getDataPagamento().after(ant1Mes) && p.getDataPagamento().before(ant1Mes)) {
                    pag1++;
                    if (p.getDataPagamento().after(p.getDataVencimento())) {
                        atraso1++;
                    }
                }

                pagTotal++;
                atrasoTotal++;
            } else if (p.getDataVencimento().before(hoje)) {
                atraso.add(p);

                if (p.getDataVencimento().after(ant5Dias)) {
                    totalAtraso5 = totalAtraso5.add(p.getValor());
                }
                if (p.getDataVencimento().after(ant15Dias)) {
                    totalAtraso15 = totalAtraso15.add(p.getValor());
                }
                if (p.getDataVencimento().after(ant30Dias)) {
                    totalAtraso30 = totalAtraso30.add(p.getValor());
                }
            } else {
                aberto.add(p);

                totalReceber = totalReceber.add(p.getValor());
                if (p.getDataVencimento().before(pos5Dias)) {
                    totalReceber5 = totalReceber5.add(p.getValor());
                }
                if (p.getDataVencimento().before(pos15Dias)) {
                    totalReceber15 = totalReceber15.add(p.getValor());
                }
                if (p.getDataVencimento().before(pos30Dias)) {
                    totalReceber30 = totalReceber30.add(p.getValor());
                }

                if (p.getDataVencimento().after(hoje) && p.getDataVencimento().before(pos1Mes)) {
                    totalReceberMesAtual = totalReceberMesAtual.add(p.getValor());
                } else if (p.getDataVencimento().after(pos1Mes) && p.getDataVencimento().before(pos2Mes)) {
                    totalReceberMes1 = totalReceberMes1.add(p.getValor());
                } else if (p.getDataVencimento().after(pos2Mes) && p.getDataVencimento().before(pos3Mes)) {
                    totalReceberMes2 = totalReceberMes2.add(p.getValor());
                } else if (p.getDataVencimento().after(pos3Mes) && p.getDataVencimento().before(pos4Mes)) {
                    totalReceberMes3 = totalReceberMes3.add(p.getValor());
                }
            }
        }

        if (atraso1 > 0) {
            perc1 = (atraso1 * 100) / pag1;
        }
        if (atraso2 > 0) {
            perc2 = (atraso2 * 100) / pag2;
        }
        if (atraso3 > 0) {
            perc3 = (atraso3 * 100) / pag3;
        }
        if (atrasoTotal > 0) {
            percTotal = (atrasoTotal * 100) / pagTotal;
        }

        mapa.put("mes1", mes1);
        mapa.put("mes2", mes2);
        mapa.put("mes3", mes3);
        mapa.put("mesAnt1", mesAnt1);
        mapa.put("mesAnt2", mesAnt2);
        mapa.put("mesAnt3", mesAnt3);

        mapa.put("totalRecebido3", totalRecebido3);
        mapa.put("totalRecebido2", totalRecebido2);
        mapa.put("totalRecebido1", totalRecebido1);
        mapa.put("totalRecebidoMesAtual", totalRecebidoMesAtual);

        mapa.put("totalReceber5", totalReceber5);
        mapa.put("totalReceber15", totalReceber15);
        mapa.put("totalReceber30", totalReceber30);
        mapa.put("totalReceberMesAtual", totalReceberMesAtual);
        mapa.put("totalReceberMes1", totalReceberMes1);
        mapa.put("totalReceberMes2", totalReceberMes2);
        mapa.put("totalReceberMes3", totalReceberMes3);
        mapa.put("totalReceber", totalReceber);

        mapa.put("totalAtraso5", totalAtraso5);
        mapa.put("totalAtraso15", totalAtraso15);
        mapa.put("totalAtraso30", totalAtraso30);
        mapa.put("totalAtraso", totalAtraso);

        mapa.put("perc1", perc1);
        mapa.put("perc2", perc2);
        mapa.put("perc3", perc3);
        mapa.put("percTotal", percTotal);

        /*
         * Ordena parcelas
         */
        Collections.sort(aberto, new Comparator() {

            @Override
            public int compare(Object p1, Object p2) {
                Parcela parc1 = (Parcela) p1;
                Parcela parc2 = (Parcela) p2;
                return parc1.getDataVencimento().before(parc2.getDataVencimento()) ? -1 : (parc1.getDataVencimento().after(parc2.getDataVencimento()) ? 1 : 0);
            }
        });

        return jasperMaker.makePdf("Pagamentos.jasper", aberto.size() > 10 ? aberto.subList(0, 10) : aberto, "pagamentos.pdf", false, mapa);
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

    public static String formatDate(Date date) throws Exception {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    private static Date formatDate(String date) throws Exception {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    private static String formatMounthExtensive(Date date) throws Exception {
        String mes = formatDate(date).split("/")[1];
        if (mes.equals("01")) {
            mes = "Janeiro";
        } else if (mes.equals("02")) {
            mes = "Fevereiro";
        } else if (mes.equals("03")) {
            mes = "Março";
        } else if (mes.equals("04")) {
            mes = "Abril";
        } else if (mes.equals("05")) {
            mes = "Maio";
        } else if (mes.equals("06")) {
            mes = "Junho";
        } else if (mes.equals("07")) {
            mes = "Julho";
        } else if (mes.equals("08")) {
            mes = "Agosto";
        } else if (mes.equals("09")) {
            mes = "Setembro";
        } else if (mes.equals("10")) {
            mes = "Outubro";
        } else if (mes.equals("11")) {
            mes = "Novembro";
        } else if (mes.equals("12")) {
            mes = "Dezembro";
        }
        return mes;
    }
}
