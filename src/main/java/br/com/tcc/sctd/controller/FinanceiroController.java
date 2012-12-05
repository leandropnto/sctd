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
import br.com.tcc.sctd.constants.StatusFatura;
import br.com.tcc.sctd.dao.FaturaDao;
import br.com.tcc.sctd.dao.ParcelaDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Fatura;
import br.com.tcc.sctd.model.Parcela;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/financeiro")
public class FinanceiroController {
    
    private static final Logger LOG = LoggerFactory.getLogger(FinanceiroController.class);
    private final Result result;
    private final Validator validator;
    private final FaturaDao faturas;
    private final ParcelaDao parcelas;

    public FinanceiroController(Result result, Validator validator, FaturaDao faturas, ParcelaDao parcelas) {
        this.result = result;
        this.validator = validator;
        this.faturas = faturas;
        this.parcelas = parcelas;
    }
    
    @Path("/")
    public void index(){
        LOG.debug("/financeiro/index");
        
    }
    
    
    @Path("/pagamento")
    public void formPagamento(){
        
    }
    
    @Path("/filtrar")
    public void filtrar(Fatura fatura) throws DaoException{
        Fatura f = faturas.buscarPorId(fatura.getId());
        
        if (f == null){
            validator.add(new ValidationMessage("Fatura não encontrada.", "Fatura"));
        }
        
        validator.onErrorRedirectTo(this).index();
        
        List<Parcela> parcelaLista = parcelas.buscarPorFatura(f);
        
        result.include("fatura", f);
        result.include("parcelas", parcelaLista);
        
        result.redirectTo(this).formPagamento();
        
    }
    
    
    @Path("/baixar/{parcela.id}")
    public void baixar(Parcela parcela) throws DaoException{
        Parcela p = parcelas.buscarPorId(parcela.getId());
        
        if (p == null){
            validator.add(new ValidationMessage("Parcela não encontrada.", "Parcela"));
        }
        
        validator.onErrorRedirectTo(this).index();
        
        p.setDataPagamento(new Date(System.currentTimeMillis()));
        List<Parcela> parcelaLista = parcelas.buscarPorFatura(p.getFatura());
        
        boolean todasPagas = true;
        
        for (Parcela par : parcelaLista) {
            if (par.getDataPagamento() ==null){
                todasPagas = false;
                break;
            }
        }
        
        if (todasPagas){
            p.getFatura().setStatus(StatusFatura.QUITADO);
        } else {
            p.getFatura().setStatus(StatusFatura.ANDAMENTO);
        }
        
        
        parcelas.atualizar(p);
        
        result.include("msg", "Pagamento Registrado com sucesso.");
        result.redirectTo(this).filtrar(p.getFatura());
        
    }
    
}
