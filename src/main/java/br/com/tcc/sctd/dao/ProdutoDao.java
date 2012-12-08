/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Produto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lpinto
 */
@Component
public class ProdutoDao extends DaoGenericoImpl<Produto> {

    public ProdutoDao(Session sessao) {
        super(sessao);
    }

    public List<Produto> buscarPorNome(String term) {
        Criteria criterio = sessao.createCriteria(Produto.class);
        criterio.add(Restrictions.ilike("nome", "%" + term + "%"));
        return criterio.list();
    }

    public List<Produto> buscarPorNomeComQuantidadeMaiorQueZero(String term) {
        Criteria criterio = sessao.createCriteria(Produto.class);
        criterio.add(Restrictions.ilike("nome", term, MatchMode.ANYWHERE));
        criterio.add(Restrictions.gt("quantidade", 0));
        return criterio.list();
    }
}
