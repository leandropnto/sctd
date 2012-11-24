/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Cliente;
import br.com.tcc.sctd.model.PessoaFisica;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author lpinto
 */
@Component
public class PessoaFisicaDao extends DaoGenericoImpl<PessoaFisica> {

    public PessoaFisicaDao(Session sessao) {
        super(sessao);
    }
    
    
    public List<Cliente> buscaPorCPF(String cpf){
        Criteria criterio = sessao.createCriteria(PessoaFisica.class);
        criterio.add(Restrictions.eq("cpf", cpf));
        return  criterio.list();
    }
            
}
