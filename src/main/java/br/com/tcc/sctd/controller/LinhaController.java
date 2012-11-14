/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.LinhaDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Cor;
import br.com.tcc.sctd.model.Linha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/linhas")
public class LinhaController {
    private static final Logger LOG = LoggerFactory.getLogger(LinhaController.class);
    private final Result result;
    private final Validator validator;
    private final LinhaDao linhas;
    private static final int REG_POR_PAGINA = 20;

    public LinhaController(Result result, Validator validator, LinhaDao linhas) {
        this.result = result;
        this.validator = validator;
        this.linhas = linhas;
    }
    
    @Path("/")
    public void index() throws DaoException{
        LOG.debug("/cadastros/linhas/");
        
    }
    
    @Path("/filtrar")
    public void filtrar(Linha linhs) throws DaoException{
        LOG.debug("/cadastros/linhas/filtrar");        
        Long qtdLinhas = linhas.qtdRegistros(linhs);
        Long qtdPaginas = qtdLinhas / REG_POR_PAGINA;
        qtdPaginas += (qtdLinhas % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("linhas", linhas.buscarPorExemplo(linhs));
        result.include("qtde", qtdLinhas);
        result.include("qtdPaginas", qtdPaginas);
       
        result.include("paginaAtual", 1);
        result.redirectTo(this).index();
        
        
    }
    
    public void incluir(){
        LOG.debug("/cadastros/linhas/incluir");
        
    }
    
    
    @Post("/salvar")
    public void salvar(final Linha linha) throws DaoException{
        validator.checking(new Validations(){{
            that(linha != null && linha.getNome() !=null && !linha.getNome().isEmpty(), "Linha", "linha.nao.informada");
        }});
        
        validator.onErrorRedirectTo(this).incluir();
        
        linhas.salvar(linha);
        result.redirectTo(this).filtrar(linha);
    }
    
    @Path("/excluir/{linha.id}")
    public void excluir(Linha linha) throws DaoException{
        if (linha != null && linha.getId() != null){
            linhas.excluir(linha);
        }
        
        result.redirectTo(this).index();
    }
    
    @Path("/editar/{linha.id}")
    public void editar(Linha linha) throws DaoException{
        
        result.include("linha", linhas.buscarPorId(linha.getId()));
    }
    
    @Path("/atualizar")
    public void atualizar(final Linha linha) throws DaoException{
        validator.checking(new Validations(){{
            that(linha != null && linha.getNome() != null && !linha.getNome().isEmpty(), "Cor", "linha.nome.nao.informado");
        }});
        
        validator.onErrorRedirectTo(this).editar(linha);
        
        linhas.atualizar(linha);
        
        result.include("msg", "Linha atualizada com sucesso.");
        result.redirectTo(this).editar(linha);
    }
    
}
