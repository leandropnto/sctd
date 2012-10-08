/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.tcc.sctd.dao.FuncionarioDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Funcionario;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author LeandroVBOX
 */
@Path("/funcionarios")
@Resource
public class FuncionarioController {
    private static final Logger LOG = Logger.getLogger(FuncionarioController.class);
    private final Result result;
    private final FuncionarioDao funcionarios;

    /**
     *
     * @param result
     * @param funcionarios
     */
    public FuncionarioController(Result result, FuncionarioDao funcionarios) {
        this.result = result;
        this.funcionarios = funcionarios;
    }
    
    @Path(value={"/", "/index"})
    public void index() throws DaoException{
        result.include("funcionarios", funcionarios.buscarTodos());
    }
    

    @Path("/form")
    public void form(){
        
    }    
    
    public void salvar(Funcionario funcionario) throws DaoException{
        funcionario.setDataContratacao(new Date(System.currentTimeMillis()));
        
        funcionarios.salvar(funcionario);
        
        result.redirectTo(this).index();
    }
    
    @Path("/editar/{funcionario.matricula}")
    public void editar(Funcionario funcionario) throws DaoException{
        Funcionario func = funcionarios.buscarPorId(funcionario.getMatricula());
        if (func == null){
            LOG.info("funcionario não encontrado");
            return;
        }
        
        
        result.redirectTo(this).formEdicao(func);
    }

    public void formEdicao(Funcionario f) {
         result.include("funcionario", f);
    }
    
    
    public void atualizar(Funcionario funcionario){
        try {
            funcionarios.atualizar(funcionario);
            result.redirectTo(this).index();
        } catch (DaoException ex) {
            LOG.error("Erro atualizando o funcionário. " + ex.getMessage());
            result.include("errors", "Erro atualizando o funcionário. " + ex.getMessage());
            result.redirectTo(this).formEdicao(funcionario);
        }
    }
    
}
