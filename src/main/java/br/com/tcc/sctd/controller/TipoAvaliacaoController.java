/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.TipoAvaliacaoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.TipoAvaliacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/tipoavaliacao")
public class TipoAvaliacaoController {

    private static final Logger LOG = LoggerFactory.getLogger(TipoAvaliacaoController.class);
    private final Result result;
    private final Validator validator;
    private final TipoAvaliacaoDao tipos;
    private static final int REG_POR_PAGINA = 20;

    public TipoAvaliacaoController(Result result, Validator validator, TipoAvaliacaoDao tipos) {
        this.result = result;
        this.validator = validator;
        this.tipos = tipos;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/tipoavaliacao/");

    }

    @Path("/filtrar")
    public void filtrar(TipoAvaliacao tipo) throws DaoException {
        LOG.debug("/cadastros/tipoavaliacao/filtrar");
        Long qtdTipos = tipos.qtdRegistros(tipo);
        Long qtdPaginas = qtdTipos / REG_POR_PAGINA;
        qtdPaginas += (qtdTipos % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("tipos", tipos.buscarPorExemplo(tipo));
        result.include("qtde", qtdTipos);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() {
        LOG.debug("/cadastros/tipos/incluir");

    }

    @Post("/salvar")
    public void salvar(final TipoAvaliacao tipo) throws DaoException {
        validator.checking(new Validations() {

            {
                that(tipo != null && tipo.getDescricao() != null && !tipo.getDescricao().isEmpty(), "Descrição", "tipo.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).incluir();

        tipos.salvar(tipo);
        result.include("msg", "Tipo de Avaliação cadastrado com sucesso.");
        result.redirectTo(this).filtrar(tipo);
    }

    @Path("/excluir/{tipo.id}")
    public void excluir(TipoAvaliacao tipo) throws DaoException {
        if (tipo == null || tipo.getId() == null) {
            result.include("msg", "TipoAvaliacao não encontrada.");
            result.notFound();
        }

        tipos.excluir(tipo);
        result.include("msg", "TipoAvaliacao excluída com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/editar/{tipo.id}")
    public void editar(TipoAvaliacao tipo) throws DaoException {
        if (tipo == null || tipo.getId() == null) {
            result.include("msg", "Tipo de Avaliacao não encontrado.");
            result.notFound();
        }
        result.include("tipo", tipos.buscarPorId(tipo.getId()));
    }

    @Path("/atualizar")
    public void atualizar(final TipoAvaliacao tipo) throws DaoException {
        validator.checking(new Validations() {

            {
                that(tipo != null && tipo.getDescricao() != null && !tipo.getDescricao().isEmpty(), "Descrição", "tipo.nome.nao.informado");
            }
        });

        validator.onErrorRedirectTo(this).editar(tipo);

        tipos.atualizar(tipo);
        result.include("msg", "Tipo de Avaliação atualizado com sucesso.");
        result.redirectTo(this).filtrar(tipo);
    }
}
