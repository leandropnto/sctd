/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.exceptions.DaoException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

/**
 *
 * @author leandro
 */
@Component
public class DaoGenericoImpl<L> implements DaoGenerico<L> {

    protected final Session sessao;
    private Class classePersistente;

    public DaoGenericoImpl(Session sessao) {
        this.sessao = sessao;
        this.classePersistente = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void salvar(L objeto) throws DaoException {
        try {
            sessao.persist(objeto);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public L atualizar(L objeto) throws DaoException {
        try {
            L objAtualizado = (L) sessao.merge(objeto);
            return objAtualizado;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void excluir(L objeto) throws DaoException {
        try {
            sessao.delete(objeto);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<L> buscarTodos() throws DaoException {
        try {
            List<L> list = (List<L>) sessao.createCriteria(classePersistente).list();
            return list;
        } catch (Exception e) {
            throw new DaoException(e);
        }

    }

    @Override
    public L buscarPorId(Serializable id) throws DaoException {
        try {
            return (L) sessao.get(classePersistente, id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public List<L> buscarTodosOrdenado(Order... ordens) {

        Criteria criterio = sessao.createCriteria(classePersistente);
        for (Order order : ordens) {
            criterio.addOrder(order);
        }


        return criterio.list();

    }

    public List<L> buscaPaginada(int primeiroResultado, int maximoDeResultados, Order... ordens) {
        Criteria criterio = sessao.createCriteria(classePersistente);
        if (ordens != null) {
            for (Order order : ordens) {
                criterio.addOrder(order);
            }

        }

        criterio.setFirstResult(primeiroResultado);
        criterio.setMaxResults(maximoDeResultados);
        return criterio.list();

    }
    
      public List<L> buscarPorExemplo(L objeto) {
        Criteria criterio = sessao.createCriteria(classePersistente);
        Example example = Example.create(objeto).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
        criterio.add(example);
        
        return criterio.list();
        
    }
    
    public Long qtdRegistros(L objeto) {
        Criteria criterio = sessao.createCriteria(classePersistente);
        Field f =  classePersistente.getDeclaredFields()[0];
        if (objeto != null) {
            Example example = Example.create(objeto).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
            criterio.add(example);
            
        }
        
        return (Long) criterio.setProjection(Projections.count(f.getName())).uniqueResult();
    }
}
