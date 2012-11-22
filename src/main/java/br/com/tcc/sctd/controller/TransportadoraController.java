/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.TransportadoraDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Transportadora;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/transportadoras")
public class TransportadoraController {

    private static final Logger LOG = LoggerFactory.getLogger(TransportadoraController.class);
    private final Result result;
    private final Validator validator;
    private final TransportadoraDao transportadoras;
    private static final int REG_POR_PAGINA = 20;

    public TransportadoraController(Result result, Validator validator, TransportadoraDao transportadoras) {
        this.result = result;
        this.validator = validator;
        this.transportadoras = transportadoras;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/transportadoras/");

    }

    @Path("/filtrar")
    public void filtrar(Transportadora transportadora) throws DaoException {
        LOG.debug("/cadastros/transportadoras/filtrar");
        Long qtdTransportadoras = transportadoras.qtdRegistros(transportadora);
        Long qtdPaginas = qtdTransportadoras / REG_POR_PAGINA;
        qtdPaginas += (qtdTransportadoras % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("transportadoras", transportadoras.buscarPorExemplo(transportadora));
        result.include("qtde", qtdTransportadoras);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() {
        LOG.debug("/cadastros/transportadoras/incluir");

    }

    @Post("/salvar")
    public void salvar(final Transportadora transportadora) throws DaoException {
        validator.checking(new Validations() {

            {
                that(transportadora != null && transportadora.getNome() != null && !transportadora.getNome().isEmpty(), "Nome", "transportadora.nome.nao.informada");
                that(transportadora != null && transportadora.getCnpj() != null && !transportadora.getCnpj().isEmpty(), "CNPJ", "transportadora.cnpj.nao.informada");
                that(transportadora != null && transportadora.getTelefone() != null && !transportadora.getTelefone().isEmpty(), "Telefone", "transportadora.telefone.nao.informada");
                that(transportadora != null && transportadora.getEmail() != null && !transportadora.getEmail().isEmpty(), "E-mail", "transportadora.email.nao.informada");
                that(transportadora != null && transportadora.getResponsavel() != null && !transportadora.getResponsavel().isEmpty(), "Responsável", "transportadora.responsavel.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).incluir();

        
        transportadora.setCnpj(transportadora.getCnpj().replaceAll("[-/\\.]", ""));
        transportadora.setTelefone(transportadora.getTelefone().replaceAll("[()-]", ""));
        transportadoras.salvar(transportadora);
        result.include("msg", "Transportadora cadastrada com sucesso.");
        result.redirectTo(this).filtrar(transportadora);
    }

    @Path("/excluir/{transportadora.id}")
    public void excluir(Transportadora transportadora) throws DaoException {
        if (transportadora == null || transportadora.getId() == null) {
            result.include("msg", "Transportadora não encontrada.");
            result.notFound();
        }

        transportadoras.excluir(transportadora);
        result.include("msg", "Transportadora excluída com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/editar/{transportadora.id}")
    public void editar(Transportadora transportadora) throws DaoException {
        if (transportadora == null || transportadora.getId() == null) {
            result.include("msg", "Transportadora não encontrada.");
            result.notFound();
        }
        result.include("transportadora", transportadoras.buscarPorId(transportadora.getId()));
    }

    @Path("/atualizar")
    public void atualizar(final Transportadora transportadora) throws DaoException {
        validator.checking(new Validations() {

            {
                that(transportadora != null && transportadora.getNome() != null && !transportadora.getNome().isEmpty(), "Nome", "transportadora.nome.nao.informada");
                that(transportadora != null && transportadora.getCnpj() != null && !transportadora.getCnpj().isEmpty(), "CNPJ", "transportadora.cnpj.nao.informada");
                that(transportadora != null && transportadora.getTelefone() != null && !transportadora.getTelefone().isEmpty(), "Telefone", "transportadora.telefone.nao.informada");
                that(transportadora != null && transportadora.getEmail() != null && !transportadora.getEmail().isEmpty(), "E-mail", "transportadora.email.nao.informada");
                that(transportadora != null && transportadora.getResponsavel() != null && !transportadora.getResponsavel().isEmpty(), "Responsável", "transportadora.responsavel.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).editar(transportadora);

        /*
         * Retira as máscaras dos campos;
         *
         */

        transportadora.setCnpj(transportadora.getCnpj().replaceAll("[-/\\.]", ""));
        transportadora.setTelefone(transportadora.getTelefone().replaceAll("[()-]", ""));        
        transportadoras.atualizar(transportadora);
        result.include("msg", "Transportadora atualizada com sucesso.");
        result.redirectTo(this).filtrar(transportadora);
    }
}
