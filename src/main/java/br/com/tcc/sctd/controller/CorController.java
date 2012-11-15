/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.dao.CorDao;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Cor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leandro
 */
@Resource
@Path("/cadastros/cores")
public class CorController {

    private static final Logger LOG = LoggerFactory.getLogger(CorController.class);
    private final Result result;
    private final Validator validator;
    private final CorDao cores;
    private static final int REG_POR_PAGINA = 20;

    public CorController(Result result, Validator validator, CorDao cores) {
        this.result = result;
        this.validator = validator;
        this.cores = cores;
    }

    @Path("/")
    public void index() throws DaoException {
        LOG.debug("/cadastros/cores/");

    }

    @Path("/filtrar")
    public void filtrar(Cor cor) throws DaoException {
        LOG.debug("/cadastros/cores/filtrar");
        Long qtdCores = cores.qtdRegistros(cor);
        Long qtdPaginas = qtdCores / REG_POR_PAGINA;
        qtdPaginas += (qtdCores % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("cores", cores.buscarPorExemplo(cor));
        result.include("qtde", qtdCores);
        result.include("qtdPaginas", qtdPaginas);

        result.include("paginaAtual", 1);
        result.redirectTo(this).index();


    }

    public void incluir() {
        LOG.debug("/cadastros/cores/incluir");

    }

    @Post("/salvar")
    public void salvar(final Cor cor) throws DaoException {
        validator.checking(new Validations() {

            {
                that(cor != null && cor.getNome() != null && !cor.getNome().isEmpty(), "Cor", "cor.nao.informada");
            }
        });

        validator.onErrorRedirectTo(this).incluir();

        cores.salvar(cor);
        result.include("msg", "Cor cadastrada com sucesso.");
        result.redirectTo(this).filtrar(cor);
    }

    @Path("/excluir/{cor.id}")
    public void excluir(Cor cor) throws DaoException {
        if (cor == null || cor.getId() == null) {
            result.include("msg", "Cor não encontrada.");
            result.notFound();
        }

        cores.excluir(cor);
        result.include("msg", "Cor excluída com sucesso.");
        result.redirectTo(this).index();
    }

    @Path("/editar/{cor.id}")
    public void editar(Cor cor) throws DaoException {
        if (cor == null || cor.getId() == null) {
            result.include("msg", "Cor não encontrada.");
            result.notFound();
        }
        result.include("cor", cores.buscarPorId(cor.getId()));
    }

    @Path("/atualizar")
    public void atualizar(final Cor cor) throws DaoException {
        validator.checking(new Validations() {

            {
                that(cor != null && cor.getNome() != null && !cor.getNome().isEmpty(), "Cor", "cor.nome.nao.informado");
            }
        });

        validator.onErrorRedirectTo(this).editar(cor);

        cores.atualizar(cor);
        result.include("msg", "Cor atualizada com sucesso.");
        result.redirectTo(this).filtrar(cor);
    }
}
