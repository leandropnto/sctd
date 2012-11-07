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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/departamentos")
public class DepartamentoController {

    private static final Logger LOG = LoggerFactory.getLogger(DepartamentoController.class);
    private final Result result;
    private final Validator validator;
    private final DepartamentoDao departamentos;

    public DepartamentoController(Result result, Validator validator, DepartamentoDao departamentos) {
        this.result = result;
        this.validator = validator;
        this.departamentos = departamentos;
    }

    @Path("/")
    @Get
    public void index() throws DaoException {
        LOG.debug("/departamentos/index");
        result.include("departamentos", departamentos.buscarTodos());

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

        departamentos.salvar(departamento);
    }
}
