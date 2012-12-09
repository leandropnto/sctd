/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.ItemPedido;
import br.com.tcc.sctd.model.ItemVenda;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author leandro
 */
@Component
public class ItemPedidoDao extends DaoGenericoImpl<ItemPedido> {

    public ItemPedidoDao(Session sessao) {
        super(sessao);
    }

    public List<ItemPedido> buscarPorData(Date dataInicial, Date dataFinal) {
         Criteria criterio = sessao.createCriteria(ItemPedido.class);
        criterio.createAlias("produto", "p");
        criterio.createAlias("pedido", "v");
              
        criterio.add(Restrictions.between("v.dataPedido", dataInicial, dataFinal));
        criterio.addOrder(Order.asc("v.dataPedido"));
        criterio.addOrder(Order.asc("p.nome"));
        
        criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                
        return criterio.list();
    }

  
    
}
