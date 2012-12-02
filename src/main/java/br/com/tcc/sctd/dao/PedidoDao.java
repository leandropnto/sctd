/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.constants.StatusItemPedido;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.ItemPedido;
import br.com.tcc.sctd.model.Pedido;
import java.lang.reflect.Field;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.*;

/**
 *
 * @author leandro
 */
@Component
public class PedidoDao extends DaoGenericoImpl<Pedido> {

    public PedidoDao(Session sessao) {
        super(sessao);
    }

    public Pedido buscarParaJson(Integer numPedido) {
        Criteria criterio = sessao.createCriteria(Pedido.class);
        criterio.add(Restrictions.eq("id", numPedido));
        criterio.setFetchMode("itens", FetchMode.JOIN);

        return (Pedido) criterio.uniqueResult();

    }

    public boolean existeItemEmProducao(Pedido pedido) {
//        Criteria criteria = sessao.createCriteria(ItemPedido.class, "i");
//        DetachedCriteria serviceCriteria = DetachedCriteria.forClass(Pedido.class, "p");
//        serviceCriteria.add(Restrictions.eq("id", pedido.getId()));
//        //serviceCriteria.add(Property.forName("i.id").eqProperty("p.id"));
//        criteria.add(Subqueries.exists(serviceCriteria.setProjection(Projections.property("p.id"))));
//        return criteria.list().isEmpty();


        Criteria criterio = sessao.createCriteria(Pedido.class, "p");
        criterio.add(Restrictions.eq("id", pedido.getId()));
        DetachedCriteria sub = DetachedCriteria.forClass(ItemPedido.class, "i");
        sub.add(Restrictions.eq("status", StatusItemPedido.PRODUCAO));
        sub.createAlias("pedido", "pi");
        sub.add(Restrictions.eqProperty("p.id", "pi.id"));
        criterio.add(Subqueries.exists(sub.setProjection(Projections.property("i.id"))));
        return criterio.list().isEmpty();
    }

    @Override
    public List<Pedido> buscarPorExemplo(Pedido objeto) {
        Criteria criterio = sessao.createCriteria(Pedido.class);
        Example example = Example.create(objeto).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
        criterio.add(example);

        if (objeto.getId() != null) {
            criterio.add(Restrictions.eq("id", objeto.getId()));
        }

        criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criterio.list();
    }

    @Override
    public Long qtdRegistros(Pedido objeto) {
        Criteria criterio = sessao.createCriteria(Pedido.class);
        Field f = objeto.getClass().getDeclaredFields()[0];
        if (objeto != null) {
            Example example = Example.create(objeto).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
            criterio.add(example);
            if (objeto.getId() != null) {
                criterio.add(Restrictions.eq("id", objeto.getId()));
            }

        }

        Long qtd = (Long) criterio.setProjection(Projections.count(f.getName())).uniqueResult();
        if (qtd == null) {
            qtd = 0L;
        }
        return qtd;
    }

    @Override
    public List<Pedido> buscarTodos() throws DaoException {

        Criteria criterio  = sessao.createCriteria(Pedido.class);
        try {
            criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            
            return criterio.list();
        } catch (Exception e) {
            throw new DaoException(e);
        }


    }
}
