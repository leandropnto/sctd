/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Entrega;
import org.hibernate.Session;

/**
 *
 * @author lpinto
 */
@Component
public class EntregaDao extends DaoGenericoImpl<Entrega> {

    public EntregaDao(Session sessao) {
        super(sessao);
    }
    
}
