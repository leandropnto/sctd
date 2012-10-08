/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.tcc.sctd.dao.FuncionarioDao;
import br.com.tcc.sctd.dao.UsuarioDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Funcionario;
import br.com.tcc.sctd.model.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author leandro
 */
@Resource
public class IndexController {

    private final Result result;
    private static final Logger LOG = Logger.getLogger(IndexController.class);
    //private final MenuAdminDao menus;
    private final UsuarioDao usuarios;
    private final FuncionarioDao funcionarios;

    /**
     * Classe que faz o mapeamento da url "/". É o responsável por montar a
     * página inicial.
     *
     * @param result
     * @param usuarios
     * @param menus
     */
//    public IndexController(Result result, UsuarioDao usuarios, MenuAdminDao menus) {
//        this.result = result;
//        this.usuarios = usuarios;
//        this.menus = menus;
//    }
    public IndexController(Result result, UsuarioDao usuarios, FuncionarioDao funcionarios) {
        this.result = result;
        this.usuarios = usuarios;
        this.funcionarios = funcionarios;

    }

    @Path("/")
    public void index() throws DaoException {


        result.include("itensMenu", "");
        Usuario usuario = usuarios.buscarPorId(1);
        LOG.info(usuario.getNome());


        Funcionario f = new Funcionario();
        f.setNome("Fulaninho de Teste");
        f.setCargo("Administrador");
        f.setDataNascimento(new Date(System.currentTimeMillis()));
        f.setDataContratacao(new Date(System.currentTimeMillis()));
        f.setDepartamento("Financeiro");
        f.setSalario(new BigDecimal("2500.56"));
        
        //funcionarios.salvar(f);
    }
    

}
