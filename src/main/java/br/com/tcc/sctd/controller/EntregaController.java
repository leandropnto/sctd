/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.tcc.sctd.constants.StatusPedido;
import br.com.tcc.sctd.dao.EntregaDao;
import br.com.tcc.sctd.dao.PedidoDao;
import br.com.tcc.sctd.dao.VendaDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Pedido;
import br.com.tcc.sctd.model.Venda;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/pedidos/entrega")
public class EntregaController {

    private static final Logger LOG = LoggerFactory.getLogger(EntregaController.class);
    private final Result result;
    private final EntregaDao entregas;
    private final VendaDao vendas;
    private final PedidoDao pedidos;
    private final Validator validator;

    public EntregaController(Result result, EntregaDao entregas, VendaDao vendas, PedidoDao pedidos, Validator validator) {
        this.result = result;
        this.entregas = entregas;
        this.vendas = vendas;
        this.pedidos = pedidos;
        this.validator = validator;
    }

    @Path("/recolhimento")
    public void recolhimentoParaEntrega() {
        LOG.debug("/pedidos/entrega");
        result.include("data", new Date(System.currentTimeMillis()));
    }

    @Path("/registrarrecolhimento")
    public void registrarRecolhimento(Venda venda, Pedido pedido, Date data) throws DaoException {
        LOG.debug("/pedidos/entrega/registrarrecolhimento");
        if ((venda.getId() != null) && (pedido.getId() != null) || (venda.getId() == null) && (pedido.getId() == null)) {
            validator.add(new ValidationMessage("Informe o número do pedido OU o número da venda", "Pedido/Venda"));
        }

        validator.onErrorRedirectTo(this).recolhimentoParaEntrega();

        if (venda != null && venda.getId() != null) {
            Venda vendaRecuperada = vendas.buscarPorId(venda.getId());
            if (vendaRecuperada == null) {
                validator.add(new ValidationMessage("Venda não encontrada.", "Venda"));
                LOG.debug("Venda não encontrada.");

            } else {
                if (vendaRecuperada.getEntrega() != null) {
                    venda.getEntrega().setDataRecolhimento(data);
                    vendas.atualizar(vendaRecuperada);
                    result.include("msg", "Recolhimento da venda registrado com sucesso.");
                } else {
                    validator.add(new ValidationMessage("Venda sem entrega registrada.", "Venda"));
                    LOG.debug("Venda sem entrega registrada.");
                }

            }
        }

        validator.onErrorRedirectTo(this).recolhimentoParaEntrega();

        if (pedido != null && pedido.getId() != null) {
            Pedido pedidoRecuperado = pedidos.buscarPorId(pedido.getId());
            if (pedido != null) {
                if (pedidoRecuperado.getStatus() == StatusPedido.CONCLUIDO) {
                    pedidoRecuperado.getEntrega().setDataRecolhimento(data);
                    pedidos.atualizar(pedidoRecuperado);
                    result.include("msg", "Recolhimento do pedido registrado com sucesso.");
                } else{
                    validator.add(new ValidationMessage("A entrega somente pode ser liberada para pedidos concluídos. "
                            + "Atualmente o status do pedido é: " + pedidoRecuperado.getStatus() , "Status Pedido"));
                }

            } else {
                validator.add(new ValidationMessage("Pedido não encontrado.", "Pedido"));
            }
        }

        validator.onErrorRedirectTo(this).recolhimentoParaEntrega();



        result.redirectTo(this).recolhimentoParaEntrega();

    }
}
