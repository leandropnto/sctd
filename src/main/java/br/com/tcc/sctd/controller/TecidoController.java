/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.TecidoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Tecido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/tecidos")
public class TecidoController {

    private static final Logger LOG = LoggerFactory.getLogger(TecidoController.class);
    private final Result result;
    private final Validator validator;
    private final TecidoDao tecidos;
    private static final int REG_POR_PAGINA = 20;

    public TecidoController(Result result, Validator validator, TecidoDao tecidos) {
        this.result = result;
        this.validator = validator;
        this.tecidos = tecidos;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/tecidos/");

    }

    @Path("/filtrar")
    public void filtrar(Tecido tecido) throws DaoException {
        LOG.debug("/cadastros/tecidos/filtrar");
        Long qtdTecidos = tecidos.qtdRegistros(tecido);
        Long qtdPaginas = qtdTecidos / REG_POR_PAGINA;
        qtdPaginas += (qtdTecidos % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("tecidos", tecidos.buscarPorExemplo(tecido));
        result.include("qtde", qtdTecidos);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() {
        LOG.debug("/cadastros/tecidos/incluir");

    }

    @Post("/salvar")
    public void salvar(final Tecido tecido) throws DaoException {
        validator.checking(new Validations() {

            {
                that(tecido != null && tecido.getNome() != null && !tecido.getNome().isEmpty(), "Cor", "cor.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).incluir();

        tecidos.salvar(tecido);
        result.include("msg", "Tecido cadastrado com sucesso.");
        result.redirectTo(this).filtrar(tecido);
    }

    @Path("/excluir/{tecido.id}")
    public void excluir(Tecido tecido) throws DaoException {
        if (tecido == null && tecido.getId() == null) {
            result.include("msg", "Tecido não encontrado.");
            result.notFound();
        }

        tecidos.excluir(tecido);
        result.include("msg", "Tecido excluído com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/editar/{tecido.id}")
    public void editar(Tecido tecido) throws DaoException {
        Tecido tecidoEncontrado = tecidos.buscarPorId(tecido.getId());
        if (tecidoEncontrado == null && tecidoEncontrado.getId() == null) {
            result.include("msg", "Tecido não encontrado.");
            result.notFound();
        }
        result.include("tecido", tecidoEncontrado);
    }

    @Path("/atualizar")
    public void atualizar(final Tecido tecido) throws DaoException {
        validator.checking(new Validations() {

            {
                that(tecido != null && tecido.getNome() != null && !tecido.getNome().isEmpty(), "Tecido", "tecido.nome.nao.informado");
            }
        });

        validator.onErrorRedirectTo(this).editar(tecido);

        tecidos.atualizar(tecido);
        result.include("msg", "Tecido atualizado com sucesso.");
        result.redirectTo(this).filtrar(tecido);
    }
}
