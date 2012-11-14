/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.DepartamentoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Departamento;
import br.com.tcc.sctd.service.Opcoes;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/departamentos")
public class DepartamentoController {

    private static final Logger LOG = LoggerFactory.getLogger(DepartamentoController.class);
    private static final int REG_POR_PAGINA = 10;
    private final Result result;
    private final Validator validator;
    private final DepartamentoDao departamentos;
    private List<Opcoes> opcoes;

    public DepartamentoController(Result result, Validator validator, DepartamentoDao departamentos) {
        this.result = result;
        this.validator = validator;
        this.departamentos = departamentos;
        opcoes = new ArrayList<Opcoes>();
        opcoes.add(new Opcoes("/departamentos/incluir", "Incluir Departamento"));
    }

    @Path("/")
    @Get
    public void index() throws DaoException {
        LOG.debug("/departamentos/index");
        List<Departamento> listaDepartamentos = departamentos.buscarTodos();
        Long qtdDestaques = departamentos.qtdRegistros(null);
        Long qtdPaginas = qtdDestaques / REG_POR_PAGINA;
        qtdPaginas += (qtdDestaques % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("departamentos", listaDepartamentos);
        result.include("qtde", qtdDestaques);
        result.include("qtdPaginas", qtdPaginas);

        result.include("opcoes", opcoes);
        result.include("paginaAtual", 1);


    }

    @Get
    public void incluir() {
        LOG.debug("/departamentos/incluir");
    }

    public void salvar(final Departamento departamento) throws DaoException {
        LOG.debug("/cargo/salvar");
        validator.checking(new Validations() {

            {
                that(departamento != null && !departamento.getDescricao().isEmpty(),
                        "Departamento", "departamento.descricao.nao.informado");
            }
        });

        if (validator.hasErrors()) {
            result.include("departamento", departamento);
        }
        validator.onErrorForwardTo(this).incluir();

        departamentos.salvar(departamento);
        result.redirectTo(this).filtrar(departamento);
    }

    @Post("/atualizar")
    public void atualizar(final Departamento departamento) throws DaoException {
        LOG.debug("/cargo/atualizar");
        validator.checking(new Validations() {

            {
                that(departamento != null && !departamento.getDescricao().isEmpty(),
                        "Departamento", "departamento.descricao.nao.informado");
            }
        });

        if (validator.hasErrors()) {
            result.include("departamento", departamento);
        }
        validator.onErrorForwardTo(this).incluir();

        departamentos.atualizar(departamento);
        result.redirectTo(this).filtrar(departamento);
    }

    @Path("/filtrar")
    public void filtrar(Departamento departamento) throws DaoException {
        LOG.debug("/departamentos/filtrar");

        List<Departamento> listaDepartamentos = departamentos.buscarPorExemplo(departamento);
        Long qtdDestaques = departamentos.qtdRegistros(departamento);
        Long qtdPaginas = qtdDestaques / REG_POR_PAGINA;
        qtdPaginas += (qtdDestaques % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("departamentos", listaDepartamentos);
        result.include("qtde", qtdDestaques);
        result.include("qtdPaginas", qtdPaginas);

        result.include("opcoes", opcoes);
        result.include("paginaAtual", 1);


    }

    @Path("/editar/{departamento.id}")
    public void editar(Departamento departamento) throws DaoException {
        Departamento dep = departamentos.buscarPorId(departamento.getId());
        if (dep == null) {
            LOG.info("Departamento não encontrado");
        }

        result.include("departamento", dep);
    }
     
    
    @Path("/excluir/{departamento.id}")
    public void excluir(Departamento departamento) throws DaoException {
        if (departamento != null && departamento.getId() >= 0){
            departamentos.excluir(departamento);
            result.include("msg" , "Departamento excluído com sucesso.");
        }
        
        result.redirectTo(this).filtrar(new Departamento());
    }
}
