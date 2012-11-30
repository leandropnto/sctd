/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.OrdemServico;
import org.hibernate.Session;

/**
 *
 * @author leandro
 */
@Component
public class OrdemDao extends DaoGenericoImpl<OrdemServico> {

    public OrdemDao(Session sessao) {
        super(sessao);
    }
    
}
