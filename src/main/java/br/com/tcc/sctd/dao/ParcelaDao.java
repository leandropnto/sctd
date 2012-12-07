package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Fatura;
import br.com.tcc.sctd.model.Parcela;
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
public class ParcelaDao extends DaoGenericoImpl<Parcela> {

    public ParcelaDao(Session sessao) {
        super(sessao);
    }

    public List<Parcela> buscarPorFatura(Fatura f) {
        Criteria criterio = sessao.createCriteria(Parcela.class, "p");
        criterio.createAlias("fatura", "f");
        criterio.add(Restrictions.eq("f.id", f.getId()));
        criterio.addOrder(Order.asc("p.id"));

        return criterio.list();
    }

    public List<Parcela> buscarPagos() {
        Criteria criterio = sessao.createCriteria(Parcela.class, "p");
        criterio.createAlias("fatura", "f");      
        criterio.add(Restrictions.isNull("p.dataPagamento"));
        criterio.addOrder(Order.asc("p.id"));
        
        return criterio.list();
    }
}
