/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.sctd.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.tcc.sctd.constants.StatusFuncionario;
import br.com.tcc.sctd.dao.*;
import br.com.tcc.sctd.exceptions.DaoException;
import br.com.tcc.sctd.model.Funcionario;
import br.com.tcc.sctd.service.Opcoes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LeandroVBOX
 */
@Path("/cadastros/funcionarios")
@Resource
public class FuncionarioController {

    private static final Logger LOG = Logger.getLogger(FuncionarioController.class);
    private final Validator validator;
    private final Result result;
    private List<Opcoes> opcoes;
    private final DepartamentoDao departamentos;
    private final CargoDao cargos;
    private final FuncionarioDao funcionarios;    
    private final EspecialidadeDao especialidades;
    private static final int REG_POR_PAGINA = 20;   
    private final TipoEspecialidadeDao tipoEspecialidades;
    private final Integer DEP_PRODUCAO = 2;
    
    /**
     *
     * @param result
     * @param funcionarios
     */
    public FuncionarioController(Result result, FuncionarioDao funcionarios, DepartamentoDao departamentos,
            CargoDao cargos,
            EspecialidadeDao especialidades, Validator validator, TipoEspecialidadeDao tipoEspecialidades) {
        this.result = result;
        this.funcionarios = funcionarios;
        opcoes = new ArrayList<Opcoes>();
        opcoes.add(new Opcoes("/funcionarios/novo", "Incluir funcionário"));
        this.departamentos = departamentos;
        this.cargos = cargos;        
        this.especialidades = especialidades;
        this.validator = validator;
        this.tipoEspecialidades = tipoEspecialidades;

    }

    @Path(value = {"/", "/index"})
    public void index() throws DaoException {
        LOG.debug("/funcionarios/index");
        preencheCombos();
    }

    @Path("/incluir")
    public void incluir() throws DaoException {
        preencheCombos();
    }

    public void salvar(final Funcionario funcionario) throws DaoException {

        validator.checking(new Validations() {

            {
                that(funcionario != null && funcionario.getCpf() != null
                        && !funcionario.getCpf().isEmpty(), "CPF", "funcionario.cpf.nao.informado");
                that(funcionario != null && funcionario.getDataNascimento() != null, "Data Nascimento",
                        "funcionario.data.nascimento.nao.informado");
                that(funcionario != null && funcionario.getCargo() != null && funcionario.getCargo().getId() >= 0, "Cargo",
                        "funcionario.cargo.nao.informado");
                that(funcionario != null && funcionario.getDepartamento() != null && funcionario.getDepartamento().getId() >= 0, "Departamento",
                        "funcionario.departamento.nao.informado");

                that(funcionario != null && funcionario.getSalario() != null, "Salário",
                        "funcionario.salario.nao.informado");

                that(funcionario != null && funcionario.getSalario() != null && funcionario.getSalario().compareTo(BigDecimal.ZERO) > 0, "Salário",
                        "funcionario.salario.menor.zero");
            }
        });

        if (validator.hasErrors()) {
            LOG.debug("Erros de validação encontrados.");
        }

        validator.onErrorRedirectTo(this).incluir();

        funcionario.setDataContratacao(new Date(System.currentTimeMillis()));
        funcionario.setCpf(funcionario.getCpf().replaceAll("[.-]", ""));
        
        /* Regra para alocação de funcionário */
        
        if (funcionario.getDepartamento().getId() == DEP_PRODUCAO) {
            funcionario.setStatus(StatusFuncionario.DESALOCADO);
        } else {
            funcionario.setStatus(StatusFuncionario.ALOCADO);
        }
        funcionarios.salvar(funcionario);
        result.redirectTo(this).index();
    }

    @Path("/editar/{funcionario.matricula}")
    public void editar(Funcionario funcionario) throws DaoException {
        Funcionario func = funcionarios.buscarPorId(funcionario.getMatricula());
        if (func == null) {
            LOG.info("funcionario não encontrado");
            result.include("msg", "Funcionário não encontrado.");
            result.notFound();
           
        }   
        
        result.include("funcionario", func);
        preencheCombos();
    }


    public void atualizar(Funcionario funcionario) throws DaoException {
        try {

            funcionario.setCpf(funcionario.getCpf().replaceAll("[.-]", ""));
            funcionarios.atualizar(funcionario);
            result.include("msg", "Funcionário atualizado com sucesso.");
            result.redirectTo(this).filtrar(funcionario);
        } catch (DaoException ex) {
            LOG.error("Erro atualizando o funcionário. " + ex.getMessage());
            result.include("errors", "Erro atualizando o funcionário. " + ex.getMessage());
            result.redirectTo(this).editar(funcionario);
        }
    }

    public void filtrar(Funcionario funcionario) throws DaoException {
        LOG.debug("/funcionarios/filtrar");
        List<Funcionario> listaFuncionarios = funcionarios.buscarPorExemplo(funcionario);
        Long qtdFuncionarios = funcionarios.qtdRegistros(funcionario);
        Long qtdPaginas = qtdFuncionarios / REG_POR_PAGINA;
        qtdPaginas += (qtdFuncionarios % REG_POR_PAGINA > 0) ? 1 : 0;
        result.include("funcionarios", listaFuncionarios);
        result.include("qtde", qtdFuncionarios);
        result.include("qtdPaginas", qtdPaginas);

        result.include("opcoes", opcoes);
        result.include("paginaAtual", 1);
        preencheCombos();

        result.redirectTo(this).index();

    }

    private void preencheCombos() throws DaoException {
        result.include("cargos", cargos.buscarTodos());
        result.include("departamentos", departamentos.buscarTodos());
        result.include("listastatus", StatusFuncionario.values());
        result.include("listaEspecialidades", tipoEspecialidades.buscarTodos());
    }

    @Path("/excluir/{funcionario.matricula}")
    public void excluir(Funcionario funcionario) throws DaoException {
        Funcionario funcionarioEncontrado = null;
        if (funcionario != null) {
            funcionarioEncontrado = funcionarios.buscarPorId(funcionario.getMatricula());
            funcionarioEncontrado.setStatus(StatusFuncionario.DESLIGADO);

        }
        if (funcionarioEncontrado != null) {
            result.redirectTo(this).filtrar(funcionarioEncontrado);
        } else {
            result.redirectTo(this).filtrar(new Funcionario());
        }

    }
}
