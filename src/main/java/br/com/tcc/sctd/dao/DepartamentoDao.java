/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Departamento;
import org.hibernate.Session;

/**
 *
 * @author LeandroVBOX
 */
@Component
public class DepartamentoDao extends DaoGenericoImpl<Departamento> {

    public DepartamentoDao(Session sessao) {
        super(sessao);
    }
    
    
   
}
