/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.ItemVenda;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lpinto
 */
@Component
public class ItemVendaDao extends DaoGenericoImpl<ItemVenda> {

    public ItemVendaDao(Session sessao) {
        super(sessao);
    }

    public List<ItemVenda> buscarPorData(Date dataInicial, Date dataFinal) {
        Criteria criterio = sessao.createCriteria(ItemVenda.class);
        criterio.createAlias("produto", "p");
        criterio.createAlias("venda", "v");
              
        criterio.add(Restrictions.between("v.dataVenda", dataInicial, dataFinal));
        criterio.addOrder(Order.asc("v.dataVenda"));
        criterio.addOrder(Order.asc("p.nome"));
        
        criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                
        return criterio.list();
                
    }
    
}
