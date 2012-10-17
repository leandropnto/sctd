/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.FuncionarioStatus;
import org.hibernate.Session;

/**
 *
 * @author LeandroVBOX
 */
@Component
public class FuncionarioStatusDao extends DaoGenericoImpl<FuncionarioStatus> {

    public FuncionarioStatusDao(Session sessao) {
        super(sessao);
    }
    
}
