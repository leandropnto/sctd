/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.constants.StatusCliente;
import br.com.tcc.sctd.dao.EnderecoDao;
import br.com.tcc.sctd.dao.PessoaFisicaDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Endereco;
import br.com.tcc.sctd.model.PessoaFisica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/pessoafisica")
public class PessoaFisicaController {

    private static final Logger LOG = LoggerFactory.getLogger(PessoaFisicaController.class);
    private final Result result;
    private final Validator validator;
    private final PessoaFisicaDao pessoas;
    private static final int REG_POR_PAGINA = 20;
    private final EnderecoDao enderecos;

    public PessoaFisicaController(Result result, Validator validator, PessoaFisicaDao pessoafisica, EnderecoDao enderecos) {
        this.result = result;
        this.validator = validator;
        this.pessoas = pessoafisica;
        this.enderecos = enderecos;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/pessoafisica/");

    }

    @Path("/filtrar")
    public void filtrar(PessoaFisica pessoa) throws DaoException {
        LOG.debug("/cadastros/pessoafisica/filtrar");
        Long qtdPessoaFisica = pessoas.qtdRegistros(pessoa);
        Long qtdPaginas = qtdPessoaFisica / REG_POR_PAGINA;
        qtdPaginas += (qtdPessoaFisica % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("pessoas", pessoas.buscarPorExemplo(pessoa));
        result.include("qtde", qtdPessoaFisica);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() {
        LOG.debug("/cadastros/pessoafisica/incluir");

    }

    @Post("/salvar")
    public void salvar(final PessoaFisica pessoa) throws DaoException {
        validator.checking(new Validations() {

            {

                that(pessoa != null && pessoa.getNome() != null && !pessoa.getNome().isEmpty(), "Nome", "pessoa.nome.nao.informada");
                that(pessoa != null && pessoa.getCpf() != null && !pessoa.getCpf().isEmpty(), "CPF", "pessoa.cpf.nao.informada");

            }
        });

        validator.onErrorRedirectTo(this).incluir();


        pessoa.setCpf(pessoa.getCpf().replaceAll("[-/\\.]", ""));
        pessoa.setTelefone(pessoa.getTelefone().replaceAll("[()-]", ""));
        pessoa.setStatus(StatusCliente.ATIVO);

        pessoas.salvar(pessoa);
        result.include("msg", "PessoaFisica cadastrada com sucesso.");
        result.redirectTo(this).filtrar(pessoa);
    }

    @Path("/excluir/{pessoa.id}")
    public void excluir(PessoaFisica pessoa) throws DaoException {
        if (pessoa == null || pessoa.getId() == null) {
            result.include("msg", "Pessoa não encontrada.");
            result.notFound();
        }
        PessoaFisica pessoaRecuperada = pessoas.buscarPorId(pessoa.getId());

        if (pessoaRecuperada == null) {
            result.include("msg", "Empresa não encontrada.");
            result.notFound();
        }

        pessoaRecuperada.setStatus(StatusCliente.DESATIVADO);

        result.include("msg", "Pessoa excluída com sucesso.");
        result.redirectTo(this).filtrar(pessoaRecuperada);
    }

    @Path("/editar/{pessoa.id}")
    public void editar(PessoaFisica pessoa) throws DaoException {
        if (pessoa == null || pessoa.getId() == null) {
            result.include("msg", "Pessoa não encontrada.");
            result.notFound();
        }
        result.include("pessoa", pessoas.buscarPorId(pessoa.getId()));
        
    }

    @Path("/atualizar")
    public void atualizar(final PessoaFisica pessoa) throws DaoException {
        validator.checking(new Validations() {

            {

                that(pessoa != null && pessoa.getNome() != null && !pessoa.getNome().isEmpty(), "Nome", "pessoa.nome.nao.informada");
                that(pessoa != null && pessoa.getCpf() != null && !pessoa.getCpf().isEmpty(), "CPF", "pessoa.cpf.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).editar(pessoa);

        /*
         * Retira as máscaras dos campos;
         *
         */

        pessoa.setCpf(pessoa.getCpf().replaceAll("[-/\\.]", ""));
        pessoa.setTelefone(pessoa.getTelefone().replaceAll("[()-]", ""));
        pessoas.atualizar(pessoa);
        result.include("msg", "Pessoa atualizada com sucesso.");
        result.redirectTo(this).filtrar(pessoa);
    }
    
    
    @Path("/endereco/{pessoa.id}/salvar")
    public void salvarEndereco(PessoaFisica pessoa, Endereco endereco) throws DaoException{
        PessoaFisica pessoaRecuperada = pessoas.buscarPorId(pessoa.getId());
        enderecos.salvar(endereco);
        pessoaRecuperada.setEndereco(endereco);
        
        result.include("msg", "Endereço Incluído com sucesso.");
        result.redirectTo(this).editar(pessoa);
    }
}
