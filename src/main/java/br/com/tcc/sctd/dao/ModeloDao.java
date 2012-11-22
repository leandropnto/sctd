/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.tcc.sctd.model.Modelo;
import org.hibernate.Session;

/**
 *
 * @author leandro
 */
@Component
public class ModeloDao extends DaoGenericoImpl<Modelo> {

    public ModeloDao(Session sessao) {
        super(sessao);
    }
    
}
