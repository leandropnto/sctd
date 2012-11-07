/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.CargoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Cargo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cargo")
public class CargoController {

    private static final Logger LOG = LoggerFactory.getLogger(CargoController.class);
    private final Result result;
    private final Validator validator;
    private final CargoDao cargos;

    public CargoController(Result result, Validator validator, CargoDao cargos) {
        this.result = result;
        this.validator = validator;
        this.cargos = cargos;
    }

    @Path("/")
    @Get
    public void index() throws DaoException {
        LOG.debug("/cargos/index");
        result.include("cargos", cargos.buscarTodos());
    }

    @Path("/editar/{cargo.id}")
    public void editar(Cargo cargo) throws DaoException {
        Cargo cargoRecuperado = cargos.buscarPorId(cargo.getId());
        result.include("cargo", cargoRecuperado);
    }

    @Post
    public void atualizar(final Cargo cargo) throws DaoException {
        validator.checking(new Validations() {

            {
                that(cargo != null && !cargo.getDescricao().isEmpty(), "Cargo", "cargo.descricao.nao.informado");
            }
        });

        validator.onErrorRedirectTo(this).editar(cargo);

        cargos.atualizar(cargo);
    }

    public void salvar(final Cargo cargo) throws DaoException {
        LOG.debug("/cargo/salvar");
        validator.checking(new Validations() {

            {
                that(cargo != null && !cargo.getDescricao().isEmpty(), "Cargo", "cargo.descricao.nao.informado");
            }
        });

        validator.onErrorRedirectTo(this).editar(cargo);

        cargos.atualizar(cargo);
    }

    @Path("/incluir")
    public void incluir() {
        LOG.debug("/cargos/incluir");
    }
    
    
    @Path("/excluir/{cargo.id}")
    public void excluir(Cargo cargo) throws DaoException{
        LOG.debug("/cargo/excluir");
        cargos.excluir(cargo);
        
    }
}
