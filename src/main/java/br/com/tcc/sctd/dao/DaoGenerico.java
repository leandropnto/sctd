/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.dao;

import br.com.tcc.sctd.exceptions.DaoException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author leandro
 */
public interface DaoGenerico<L> {
            
    public void salvar(L objeto) throws DaoException;
    public L atualizar(L objeto) throws DaoException;
    public void excluir(L objeto) throws DaoException;
    public List<L> buscarTodos() throws DaoException;
    public L buscarPorId(Serializable id) throws DaoException;
    
}
