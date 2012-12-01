/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Pedido;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author leandro
 */
@Component
public class PedidoDao extends DaoGenericoImpl<Pedido> {

    public PedidoDao(Session sessao) {
        super(sessao);
    }
    
    public Pedido buscarParaJson(Integer numPedido){
        Criteria criterio = sessao.createCriteria(Pedido.class);
        criterio.add(Restrictions.eq("id", numPedido));
        criterio.setFetchMode("itens", FetchMode.JOIN);
        
        return (Pedido) criterio.uniqueResult();
        
    }
    
}
