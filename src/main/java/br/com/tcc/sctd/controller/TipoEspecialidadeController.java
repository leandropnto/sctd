/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.CorDao;
import br.com.tcc.sctd.dao.TipoEspecialidadeDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Cor;
import br.com.tcc.sctd.model.TipoEspecialidade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/tipoespecialidade")
public class TipoEspecialidadeController {
    private static final Logger LOG = LoggerFactory.getLogger(TipoEspecialidadeController.class);
    private final Result result;
    private final Validator validator;
    private final TipoEspecialidadeDao especialidades;
    private static final int REG_POR_PAGINA = 20;

    public TipoEspecialidadeController(Result result, Validator validator, TipoEspecialidadeDao especialidades) {
        this.result = result;
        this.validator = validator;
        this.especialidades = especialidades;
    }
    
    @Path("/")
    public void index() throws DaoException{
        LOG.debug("/cadastros/especialidades/");
        
    }
    
    @Path("/filtrar")
    public void filtrar(TipoEspecialidade especialidade) throws DaoException{
        LOG.debug("/cadastros/especialidades/filtrar");        
        Long qtdEspecialidade = especialidades.qtdRegistros(especialidade);
        Long qtdPaginas = qtdEspecialidade / REG_POR_PAGINA;
        qtdPaginas += (qtdEspecialidade % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("especialidades", especialidades.buscarPorExemplo(especialidade));
        result.include("qtde", qtdEspecialidade);
        result.include("qtdPaginas", qtdPaginas);
       
        result.include("paginaAtual", 1);
        result.redirectTo(this).index();
        
        
    }
    
    public void incluir(){
        LOG.debug("/cadastros/especialidades/incluir");
        
    }
    
    
    @Post("/salvar")
    public void salvar(final TipoEspecialidade especialidade) throws DaoException{
        validator.checking(new Validations(){{
            that(especialidade != null && especialidade.getNome() !=null && !especialidade.getNome().isEmpty(), "Nome", "especialidade.nome.nao.informada");
            that(especialidade != null && especialidade.getDescricao() !=null && !especialidade.getDescricao().isEmpty(), "Descrição", "especialidade.descricao.nao.informada");
        }});
        
        validator.onErrorRedirectTo(this).incluir();
        
        especialidades.salvar(especialidade);
        result.redirectTo(this).filtrar(especialidade);
    }
    
    @Path("/excluir/{especialidade.id}")
    public void excluir(TipoEspecialidade especialidade) throws DaoException{
        if (especialidade != null && especialidade.getId() != null){
            especialidades.excluir(especialidade);
        }
        
        result.redirectTo(this).index();
    }
    
    @Path("/editar/{especialidade.id}")
    public void editar(TipoEspecialidade especialidade) throws DaoException{
        
        result.include("especialidade", especialidades.buscarPorId(especialidade.getId()));
    }
    
    @Path("/atualizar")
    public void atualizar(final TipoEspecialidade especialidade) throws DaoException{
        validator.checking(new Validations(){{
            that(especialidade != null && especialidade.getNome() != null && !especialidade.getNome().isEmpty(), "Nome", "especialidade.nome.nao.informado");
            that(especialidade != null && especialidade.getDescricao() != null && !especialidade.getDescricao().isEmpty(), "Descrição", "especialidade.descricao.nao.informado");
        }});
        
        validator.onErrorRedirectTo(this).editar(especialidade);
        
        especialidades.atualizar(especialidade);
        
        result.include("msg", "Tipo Especialidade atualizado com sucesso.");
        result.redirectTo(this).editar(especialidade);
    }
    
}
