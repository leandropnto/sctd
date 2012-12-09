/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.ModeloDao;
import br.com.tcc.sctd.dao.TipoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Modelo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/modelos")
public class ModeloController {

    private static final Logger LOG = LoggerFactory.getLogger(ModeloController.class);
    private final Result result;
    private final Validator validator;
    private final ModeloDao modelos;
    private static final int REG_POR_PAGINA = 20;
    private final TipoDao tipos;

    public ModeloController(Result result, Validator validator, ModeloDao modelos, TipoDao tipos) {
        this.result = result;
        this.validator = validator;
        this.modelos = modelos;
        this.tipos = tipos;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/modelos/");
        result.include("tipos", tipos.buscarTodos());
    }

    @Path("/filtrar")
    public void filtrar(Modelo modelo) throws DaoException {
        LOG.debug("/cadastros/modelos/filtrar");
        Long qtdModelos = modelos.qtdRegistros(modelo);
        
        if (qtdModelos == 0){
            validator.add(new ValidationMessage("Modelo não encontrado", "Modelos"));
        }
        validator.onErrorRedirectTo(this).index();
        
        Long qtdPaginas = qtdModelos / REG_POR_PAGINA;
        qtdPaginas += (qtdModelos % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("modelos", modelos.buscarPorExemplo(modelo));
        result.include("qtde", qtdModelos);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() throws DaoException {
        LOG.debug("/cadastros/modelos/incluir");
        result.include("tipos", tipos.buscarTodos());

    }

    @Post("/salvar")
    public void salvar(final Modelo modelo) throws DaoException {
        validator.checking(new Validations() {

            {
                that(modelo != null && modelo.getNome() != null && !modelo.getNome().isEmpty(), "Modelo", "modelo.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).incluir();

        modelos.salvar(modelo);
        result.include("msg", "Modelo cadastrado com sucesso.");
        result.redirectTo(this).filtrar(modelo);
    }

    @Path("/excluir/{modelo.id}")
    public void excluir(Modelo modelo) throws DaoException {
        if (modelo == null || modelo.getId() == null) {
            result.include("msg", "Modelo não encontrada.");
            result.notFound();
        }

        modelos.excluir(modelo);
        result.include("msg", "Modelo excluído com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/editar/{modelo.id}")
    public void editar(Modelo modelo) throws DaoException {
        if (modelo == null || modelo.getId() == null) {
            result.include("msg", "Modelo não encontrada.");
            result.notFound();
        }
        result.include("modelo", modelos.buscarPorId(modelo.getId()));
        result.include("tipos", tipos.buscarTodos());
    }

    @Path("/atualizar")
    public void atualizar(final Modelo modelo) throws DaoException {
        validator.checking(new Validations() {

            {
                that(modelo != null && modelo.getNome() != null && !modelo.getNome().isEmpty(), "modelo", "modelo.nome.nao.informado");
            }
        });

        validator.onErrorRedirectTo(this).editar(modelo);

        modelos.atualizar(modelo);
        result.include("msg", "Modelo atualizado com sucesso.");
        result.redirectTo(this).filtrar(modelo);
    }
}
