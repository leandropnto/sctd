/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.BotaoDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Botao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/botoes")
public class BotaoController {
    private static final Logger LOG = LoggerFactory.getLogger(BotaoController.class);
    private final Result result;
    private final Validator validator;
    private final BotaoDao botoes;
    private static final int REG_POR_PAGINA = 20;

    public BotaoController(Result result, Validator validator, BotaoDao botoes) {
        this.result = result;
        this.validator = validator;
        this.botoes = botoes;
    }
    
    @Path("/")
    public void index() throws DaoException{
        LOG.debug("/botoes/");
        
    }
    
    @Path("/filtrar")
    public void filtrar(Botao botao) throws DaoException{
        LOG.debug("/botoes/filtrar");        
        Long qtdBotoes = botoes.qtdRegistros(botao);
        Long qtdPaginas = qtdBotoes / REG_POR_PAGINA;
        qtdPaginas += (qtdBotoes % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("botoes", botoes.buscarPorExemplo(botao));
        result.include("qtde", qtdBotoes);
        result.include("qtdPaginas", qtdPaginas);
       
        result.include("paginaAtual", 1);
        result.redirectTo(this).index();
        
        
    }
    
    public void incluir(){
        LOG.debug("/botoes/incluir");
        
    }
    
    
    @Post("/salvar")
    public void salvar(final Botao botao) throws DaoException{
        validator.checking(new Validations(){{
            that(botao != null && botao.getNome() !=null && !botao.getNome().isEmpty(), "Botão", "botao.nome.nao.informado");
        }});
        
        validator.onErrorRedirectTo(this).incluir();
        
        botoes.salvar(botao);
        result.include("msg", "Botão cadastrado com sucesso.");
        result.redirectTo(this).filtrar(botao);
    }
    
    @Path("/excluir/{botao.id}")
    public void excluir(Botao botao) throws DaoException{
        validator.onErrorRedirectTo(this).index();
        if (botao != null && botao.getId() != null){
            botoes.excluir(botao);
        }
        
        result.include("msg", "Botão excluído com sucesso.");
        result.redirectTo(this).index();
    }
    
    @Path("/editar/{botao.id}")
    public void editar(Botao botao) throws DaoException{
        Botao b = botoes.buscarPorId(botao.getId());
        if (b == null){
            result.include("msg", "Botão não encontrado.");
            result.notFound();
        }
        result.include("botao", botoes.buscarPorId(botao.getId()));
    }
    
    @Path("/atualizar")
    public void atualizar(final Botao botao) throws DaoException{
        validator.checking(new Validations(){{
            that(botao != null && botao.getNome() != null && !botao.getNome().isEmpty(), "Botão", "botao.nome.nao.informado");
        }});
        
        validator.onErrorRedirectTo(this).editar(botao);
        
        botoes.atualizar(botao);
        
        result.include("msg", "Botão atualizada com sucesso.");
        result.redirectTo(this).editar(botao);
    }
    
}
