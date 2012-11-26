/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Venda;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lpinto
 */
@Component
public class VendaDao extends DaoGenericoImpl<Venda> {

    public VendaDao(Session sessao) {
        super(sessao);
    }
    
    public Venda buscar(Venda venda){
        Criteria criterio = sessao.createCriteria(Venda.class);
        criterio.add(Restrictions.eq("id", venda.getId()));
        return (Venda) criterio.uniqueResult();
    }
    
}
