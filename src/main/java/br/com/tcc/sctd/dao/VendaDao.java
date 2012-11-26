/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Venda;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
    
    
    public Venda buscarVendaCompleta(Venda v){
        Criteria criterio = sessao.createCriteria(Venda.class);
        criterio.createAlias("itens", "it");
        
        
        criterio.add(Restrictions.eq("id", v.getId()));
        criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        return (Venda) criterio.uniqueResult();
    }
}
