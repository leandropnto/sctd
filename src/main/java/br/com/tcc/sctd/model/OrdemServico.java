/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.model;

import br.com.tcc.sctd.constants.StatusOrdemServico;
import java.util.Date;

/**
 *
 * @author leandro
 */
public class OrdemServico {
    
    private Integer id;
    private Date dataInicio;
    private Date dataEntrega;
    private Produto produto;
    private ItemPedido item;
    private Integer quantidade;
    private StatusOrdemServico status;
    
}
