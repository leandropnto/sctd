/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Funcionario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;

/**
 *
 * @author LeandroVBOX
 */
@Component
public class FuncionarioDao extends DaoGenericoImpl<Funcionario> {

    public FuncionarioDao(Session sessao) {
        super(sessao);
    }

    public Long getQuantidadeDeFuncionarios() {
        Criteria criterio = sessao.createCriteria(Funcionario.class);
        return (Long) criterio.setProjection(Projections.count("matricula")).uniqueResult();
    }

    public List<Funcionario> buscarPorExemplo(Funcionario funcionario) {
        Criteria criterio = sessao.createCriteria(Funcionario.class);
        Example example = Example.create(funcionario)
                .enableLike(MatchMode.ANYWHERE)
                .ignoreCase()
                .excludeZeroes();
        criterio.add(example);
        return criterio.list();

    }
}
