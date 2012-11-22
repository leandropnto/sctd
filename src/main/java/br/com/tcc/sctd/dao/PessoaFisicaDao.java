/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.PessoaFisica;
import org.hibernate.Session;

/**
 *
 * @author lpinto
 */
@Component
public class PessoaFisicaDao extends DaoGenericoImpl<PessoaFisica> {

    public PessoaFisicaDao(Session sessao) {
        super(sessao);
    }
    
}
