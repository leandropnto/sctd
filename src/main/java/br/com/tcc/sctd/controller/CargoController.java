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
@Path("/cadastros/cargos")
public class CargoController {

    private static final Logger LOG = LoggerFactory.getLogger(CargoController.class);
    private final Result result;
    private final Validator validator;
    private final CargoDao cargos;
    private final int REG_POR_PAGINA = 10;
    private List<Opcoes> opcoes;

    public CargoController(Result result, Validator validator, CargoDao cargos) {
        this.result = result;
        this.validator = validator;
        this.cargos = cargos;
        opcoes = new ArrayList<Opcoes>();
        opcoes.add(new Opcoes("/cargos/incluir", "Incluir"));
    }

    @Path("/")
    @Get
    public void index() throws DaoException {
        LOG.debug("/cargos/index");
        result.include("cargos", cargos.buscarTodos());
        List<Cargo> listaCargos = cargos.buscarPorExemplo(new Cargo());
        Long qtdCargos = cargos.qtdRegistros(new Cargo());
        Long qtdPaginas = qtdCargos / REG_POR_PAGINA;
        qtdPaginas += (qtdCargos % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("cargos", listaCargos);
        result.include("qtde", qtdCargos);
        result.include("qtdPaginas", qtdPaginas);

        result.include("opcoes", opcoes);
        result.include("paginaAtual", 1);
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
        result.redirectTo(this).filtrar(cargo);
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
        result.redirectTo(this).filtrar(cargo);
    }

    @Path("/incluir")
    public void incluir() {
        LOG.debug("/cargos/incluir");
    }
    
    
    @Path("/excluir/{cargo.id}")
    public void excluir(Cargo cargo) throws DaoException{
        LOG.debug("/cargo/excluir");
        cargos.excluir(cargo);
        result.redirectTo(this).filtrar(new Cargo());
        
        
    }

    @Path("/filtrar")
    public void filtrar(Cargo cargo) {
         LOG.debug("/cargos/filtrar");

        List<Cargo> listaCargos = cargos.buscarPorExemplo(cargo);
        Long qtdCargos = cargos.qtdRegistros(cargo);
        Long qtdPaginas = qtdCargos / REG_POR_PAGINA;
        qtdPaginas += (qtdCargos % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("cargos", listaCargos);
        result.include("qtde", qtdCargos);
        result.include("qtdPaginas", qtdPaginas);

        result.include("opcoes", opcoes);
        result.include("paginaAtual", 1);
    }
}
