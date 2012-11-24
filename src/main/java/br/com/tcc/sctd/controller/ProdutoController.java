/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.constants.StatusProduto;
import br.com.tcc.sctd.dao.*;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/produtos")
public class ProdutoController {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoController.class);
    private final Result result;
    private final Validator validator;
    private final ProdutoDao produtos;
    private static final int REG_POR_PAGINA = 20;
    private final ModeloDao modelos;
    private final BotaoDao botoes;
    private final CorDao cores;
    private final LinhaDao linhas;

    public ProdutoController(Result result, Validator validator, ProdutoDao produtos, ModeloDao modelos, BotaoDao botoes, CorDao cores, LinhaDao linhas) {
        this.result = result;
        this.validator = validator;
        this.produtos = produtos;
        this.modelos = modelos;
        this.botoes = botoes;
        this.cores = cores;
        this.linhas = linhas;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/produtos/");

    }

    @Path("/filtrar")
    public void filtrar(Produto produto) throws DaoException {
        LOG.debug("/cadastros/produtos/filtrar");
        Long qtdTransportadoras = produtos.qtdRegistros(produto);
        Long qtdPaginas = qtdTransportadoras / REG_POR_PAGINA;
        qtdPaginas += (qtdTransportadoras % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("produtos", produtos.buscarPorExemplo(produto));
        result.include("qtde", qtdTransportadoras);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() throws DaoException {
        LOG.debug("/cadastros/produtos/incluir");
        preencherCombos();
    }

    @Post("/salvar")
    public void salvar(final Produto produto) throws DaoException {
        validator.checking(new Validations() {

            {
                that(produto != null && produto.getNome() != null && !produto.getNome().isEmpty(), "Nome", "produto.nome.nao.informada");
                that(produto != null && produto.getBotao() != null && produto.getBotao() != null, "Botão", "produto.botao.nao.informada");
                that(produto != null && produto.getCor() != null && produto.getCor() != null, "Cor", "produto.cor.nao.informada");
                that(produto != null && produto.getLinha() != null && produto.getLinha() != null, "Linha", "produto.Linhaai.nao.informada");


            }
        });

        validator.onErrorRedirectTo(this).incluir();


        produto.setQuantidade(0);
        produto.setStatus(StatusProduto.ESGOTADO);
        produtos.salvar(produto);
        result.include("msg", "Produto cadastrado com sucesso.");
        result.redirectTo(this).filtrar(produto);
    }

    @Path("/excluir/{produto.id}")
    public void excluir(Produto produto) throws DaoException {
        if (produto == null || produto.getId() == null) {
            result.include("msg", "Produto não encontrado.");
            result.notFound();
        }
        Produto produtoRecuperado = produtos.buscarPorId(produto.getId());
        if (produtoRecuperado == null) {
            result.include("msg", "Produto não encontrado.");
            result.notFound();
        }
        
        produtoRecuperado.setStatus(StatusProduto.EXCLUIDO);
        result.include("msg", "Produto excluída com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/editar/{produto.id}")
    public void editar(Produto produto) throws DaoException {
        if (produto == null || produto.getId() == null) {
            result.include("msg", "Produto não encontrada.");
            result.notFound();
        }
        result.include("produto", produtos.buscarPorId(produto.getId()));
        preencherCombos();
    }

    @Path("/atualizar")
    public void atualizar(final Produto produto) throws DaoException {
        validator.checking(new Validations() {

            {
                that(produto != null && produto.getNome() != null && !produto.getNome().isEmpty(), "Nome", "produto.nome.nao.informada");
                that(produto != null && produto.getBotao() != null && produto.getBotao() != null, "Botão", "produto.botao.nao.informada");
                that(produto != null && produto.getCor() != null && produto.getCor() != null, "Cor", "produto.cor.nao.informada");
                that(produto != null && produto.getLinha() != null && produto.getLinha() != null, "Linha", "produto.Linhaai.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).editar(produto);

        /*
         * Retira as máscaras dos campos;
         *
         */

        produtos.atualizar(produto);
        result.include("msg", "Produto atualizado com sucesso.");
        result.redirectTo(this).filtrar(produto);
    }

    private void preencherCombos() throws DaoException {
        result.include("modelos", modelos.buscarTodos());
        result.include("botoes", botoes.buscarTodos());
        result.include("cores", cores.buscarTodos());
        result.include("linhas", linhas.buscarTodos());
    }
}
