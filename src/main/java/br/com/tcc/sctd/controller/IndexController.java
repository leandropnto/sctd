/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
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
    public IndexController(Result result) {
        this.result = result;

    }

    @Path("/")
    public void index() {


        result.include("itensMenu", "");



    }
}
