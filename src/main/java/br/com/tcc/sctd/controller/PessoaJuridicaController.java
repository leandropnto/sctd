/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.constants.StatusCliente;
import br.com.tcc.sctd.dao.EnderecoDao;
import br.com.tcc.sctd.dao.PessoaJuridicaDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Endereco;
import br.com.tcc.sctd.model.PessoaJuridica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Leandro
 */
@Resource
@Path("/cadastros/pessoajuridica")
public class PessoaJuridicaController {

    private static final Logger LOG = LoggerFactory.getLogger(PessoaJuridicaController.class);
    private final Result result;
    private final Validator validator;
    private final PessoaJuridicaDao pessoas;
    private static final int REG_POR_PAGINA = 20;
    private final EnderecoDao enderecos;

    public PessoaJuridicaController(Result result, Validator validator, PessoaJuridicaDao pessoas, EnderecoDao enderecos) {
        this.result = result;
        this.validator = validator;
        this.pessoas = pessoas;
        this.enderecos = enderecos;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/pessoajuridica/");

    }

    @Path("/filtrar")
    public void filtrar(PessoaJuridica pessoa) throws DaoException {
        LOG.debug("/cadastros/pessoajuridica/filtrar");
        Long qtdPessoaJuridica = pessoas.qtdRegistros(pessoa);
        Long qtdPaginas = qtdPessoaJuridica / REG_POR_PAGINA;
        qtdPaginas += (qtdPessoaJuridica % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("pessoas", pessoas.buscarPorExemplo(pessoa));
        result.include("qtde", qtdPessoaJuridica);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() {
        LOG.debug("/cadastros/pessoajuridica/incluir");

    }

    @Post("/salvar")
    public void salvar(final PessoaJuridica pessoa) throws DaoException {
        validator.checking(new Validations() {

            {

                that(pessoa != null && pessoa.getNome() != null && !pessoa.getNome().isEmpty(), "Nome", "pessoa.nome.nao.informada");
                that(pessoa != null && pessoa.getCnpj() != null && !pessoa.getCnpj().isEmpty(), "CNPJ", "pessoa.cnpj.nao.informada");
                that(pessoa != null && pessoa.getNomeOficial() != null && !pessoa.getNomeOficial().isEmpty(), "Nome Oficial", "pessoa.nomeoficial.nao.informada");
                that(pessoa != null && pessoa.getResponsavel() != null && !pessoa.getResponsavel().isEmpty(), "Responsável", "pessoa.responsavel.nao.informada");



            }
        });

        validator.onErrorRedirectTo(this).incluir();


        pessoa.setCnpj(pessoa.getCnpj().replaceAll("[-/\\.]", ""));
        pessoa.setTelefone(pessoa.getTelefone().replaceAll("[()-]", ""));
        pessoa.setStatus(StatusCliente.ATIVO);

        pessoas.salvar(pessoa);
        result.include("msg", "Empresa cadastrada com sucesso.");
        result.redirectTo(this).filtrar(pessoa);
    }

    @Path("/excluir/{pessoa.id}")
    public void excluir(PessoaJuridica pessoa) throws DaoException {
        if (pessoa == null || pessoa.getId() == null) {
            result.include("msg", "Empresa não encontrada.");
            result.notFound();
        }
        PessoaJuridica pessoaRecuperada = pessoas.buscarPorId(pessoa.getId());

        if (pessoaRecuperada == null) {
            result.include("msg", "Empresa não encontrada.");
            result.notFound();
        }

        pessoaRecuperada.setStatus(StatusCliente.EXCLUIDO);

        result.include("msg", "Empresa excluída com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/editar/{pessoa.id}")
    public void editar(PessoaJuridica pessoa) throws DaoException {
        if (pessoa == null || pessoa.getId() == null) {
            result.include("msg", "Empresa não encontrada.");
            result.notFound();
        }
        result.include("pessoa", pessoas.buscarPorId(pessoa.getId()));
    }

    @Path("/atualizar")
    public void atualizar(final PessoaJuridica pessoa) throws DaoException {
        validator.checking(new Validations() {

            {

                that(pessoa != null && pessoa.getNome() != null && !pessoa.getNome().isEmpty(), "Nome", "pessoa.nome.nao.informada");
                that(pessoa != null && pessoa.getCnpj() != null && !pessoa.getCnpj().isEmpty(), "CNPJ", "pessoa.cnpj.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).editar(pessoa);

        /*
         * Retira as máscaras dos campos;
         *
         */

        pessoa.setCnpj(pessoa.getCnpj().replaceAll("[-/\\.]", ""));
        pessoa.setTelefone(pessoa.getTelefone().replaceAll("[()-]", ""));
        pessoas.atualizar(pessoa);
        result.include("msg", "Empresa atualizada com sucesso.");
        result.redirectTo(this).filtrar(pessoa);
    }

    @Path("/endereco/{pessoa.id}/salvar")
    public void salvarEndereco(PessoaJuridica pessoa, Endereco endereco) throws DaoException {
        PessoaJuridica pessoaRecuperada = pessoas.buscarPorId(pessoa.getId());
        enderecos.salvar(endereco);
        pessoaRecuperada.setEndereco(endereco);

        result.include("msg", "Endereço Incluído com sucesso.");
        result.redirectTo(this).editar(pessoa);
    }
}