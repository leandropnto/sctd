/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.TipoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Tipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/tiposmodelo")
public class TipoController {
    private static final Logger LOG = LoggerFactory.getLogger(TipoController.class);
    private final Result result;
    private final Validator validator;
    private final TipoDao tipos;
    private static final int REG_POR_PAGINA = 20;

    public TipoController(Result result, Validator validator, TipoDao tipos) {
        this.result = result;
        this.validator = validator;
        this.tipos = tipos;
    }
    
    @Path("/")
    public void index() throws DaoException{
        LOG.debug("/tiposmodelo/");
        
    }
    
    @Path("/filtrar")
    public void filtrar(Tipo tipo) throws DaoException{
        LOG.debug("/tiposmodelo/filtrar");        
        Long qdtTipos = tipos.qtdRegistros(tipo);
        Long qtdPaginas = qdtTipos / REG_POR_PAGINA;
        qtdPaginas += (qdtTipos % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("tipos", tipos.buscarPorExemplo(tipo));
        result.include("qtde", qdtTipos);
        result.include("qtdPaginas", qtdPaginas);
       
        result.include("paginaAtual", 1);
        result.redirectTo(this).index();
        
        
    }
    
    public void incluir(){
        LOG.debug("/tipos/incluir");
        
    }
    
    
    @Post("/salvar")
    public void salvar(final Tipo tipo) throws DaoException{
        validator.checking(new Validations(){{
            that(tipo != null && tipo.getNome() !=null && !tipo.getNome().isEmpty(), "Tipo", "tipo.nome.nao.informado");
        }});
        
        validator.onErrorRedirectTo(this).incluir();
        
        tipos.salvar(tipo);
        result.include("msg", "Tipo cadastrado com sucesso.");
        result.redirectTo(this).filtrar(tipo);
    }
    
    @Path("/excluir/{tipo.id}")
    public void excluir(Tipo tipo) throws DaoException{
        if (tipo != null && tipo.getId() != null){
            tipos.excluir(tipo);
        }
        
        result.include("msg", "Tipo excluído com sucesso.");
        result.redirectTo(this).index();
    }
    
    @Path("/editar/{tipo.id}")
    public void editar(Tipo tipo) throws DaoException{
        Tipo t = tipos.buscarPorId(tipo.getId());
        if (t == null){
            result.include("msg", "Tipo não encontrado.");
            result.notFound();
        }
        result.include("tipo", tipos.buscarPorId(tipo.getId()));
    }
    
    @Path("/atualizar")
    public void atualizar(final Tipo tipo) throws DaoException{
        validator.checking(new Validations(){{
            that(tipo != null && tipo.getNome() != null && !tipo.getNome().isEmpty(), "Tipo", "tipo.nome.nao.informado");
        }});
        
        validator.onErrorRedirectTo(this).editar(tipo);
        
        tipos.atualizar(tipo);
        
        result.include("msg", "Tipo atualizado com sucesso.");
        result.redirectTo(this).editar(tipo);
    }
    
}
