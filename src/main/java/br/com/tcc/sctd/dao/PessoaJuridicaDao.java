/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Cliente;
import br.com.tcc.sctd.model.PessoaJuridica;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lpinto
 */
@Component
public class PessoaJuridicaDao extends DaoGenericoImpl<PessoaJuridica> {

    public PessoaJuridicaDao(Session sessao) {
        super(sessao);
    }

    public List<Cliente> buscaPorCNPJ(String cnpj) {
         Criteria criterio = sessao.createCriteria(PessoaJuridica.class);
        criterio.add(Restrictions.eq("cnpj", cnpj));
        return  criterio.list();
    }
    
}
