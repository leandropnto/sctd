/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.PessoaJuridica;
import org.hibernate.Session;

/**
 *
 * @author lpinto
 */
@Component
public class PessoaJuridicaDao extends DaoGenericoImpl<PessoaJuridica> {

    public PessoaJuridicaDao(Session sessao) {
        super(sessao);
    }
    
}
