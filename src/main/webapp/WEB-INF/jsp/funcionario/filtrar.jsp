
<c:forEach var="error" items="${errors}">
    ${error.category} - ${error.message}<br />
</c:forEach>


<a href="<c:url value="/funcionarios/form"/>">Incluir</a>
<form action="<c:url value="/funcionarios/filtrar" />" name="frmBuscaFuncionario" id="frmBuscarFuncionario">
    <fieldset id="fdFuncionarios" style="margin-top: 12px;">
        <ul>
            <li>
                <label>Nome<br/>
                    <input type="text" name="funcionario.nome" value="${funcionario.nome}" style="width: 270px"/>                      
                </label>

            </li>

            <li>
                <label style="width: 200px; padding-bottom: 30px">Cargo<br/>                        
                    <select name="funcionario.cargo.id">
                        <option value="-1">Selecione o Cargo</option>
                        <c:forEach items="${cargos}" var="cargo">
                            <option value="${cargo.id}">${cargo.descricao}</option>
                        </c:forEach>
                    </select>                          
                </label>
                <label style="width: 200px; padding-bottom: 30px">Departamento<br/>                      
                    <select name="funcionario.departamento.id">
                        <option value="-1">Selecione o Departamento</option>
                        <c:forEach items="${departamentos}" var="departamento">
                            <option value="${departamento.id}">${departamento.descricao}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>Status<br/>                      
                    <select name="funcionario.status.id">
                        <option value="-1">Selecione o Status</option>
                        <c:forEach items="${listastatus}" var="st">
                            <option value="${st.id}">${st.descricao}</option>
                        </c:forEach>
                    </select>
                </label>                
            </li>
            <li  style="width: 200px; padding-bottom: 80px">
                <label>Especialidade<br/>                      
                    <select name="funcionario.especialidade.id">
                        <option value="-1">Selecione a Especialidade</option>
                        <c:forEach items="${listaEspecialidades}" var="especialidade">
                            <option value="${especialidade.id}">${especialidade.descricao}</option>
                        </c:forEach>
                    </select>
                </label>  
            </li>
            <li>
                <button type="submit" style="color:#0029FF; width: 100px; font-family: arial; font-weight: bold">Buscar</button>
            </li>
            <div class="spacer"></div>
        </ul>
    </fieldset>
</form>

<table id="mytable">
    <caption>Funcionários cadastrados</caption>
    <thead>
        <tr>
            <th>Mat.</th>
            <th>Nome</th>
            <!--            <th>Nascimento</th>
                        <th>Contratação</th>-->
            <th>Cargo</th>
            <th>Departamento</th>
            <th>Status</th>
            <th>Opções</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${funcionarios}" var="funcionario" varStatus="contador">
            <tr>
                <td style="text-align: right"><a href="<c:url value="/funcionarios/editar/${funcionario.matricula}"/>">${funcionario.matricula}</a></td>
                <td>${funcionario.nome}</td>               
<!--                <td><fmt:formatDate value="${funcionario.dataNascimento}" type="both" pattern="dd/MM/yyyy" /></td>
                <td><fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" /></td>-->
                <td>${funcionario.cargo}</td>
                <td>${funcionario.departamento}</td>
                <td>${funcionario.status}</td>
                <td>
                    <a href="<c:url value="/funcionarios/editar/${funcionario.matricula}"/>">
                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                    </a>
                    | <a href="<c:url value="/funcionarios/excluir/${funcionario.matricula}"/>">
                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                    </a>
                </td>
            </tr>
        </c:forEach>

    </tbody>
    <tfoot>
        <tr>
            <td colspan="2">Paginação: <a href="<c:url value="/funcionarios/pagina/1" />"><<</a> 
                <c:choose>
                    <c:when test="${paginaAtual>1}">
                        <a href="<c:url value="/funcionarios/pagina/${paginaAtual-1}" />"><</a>                         
                    </c:when>
                    <c:otherwise>
                        <
                    </c:otherwise>
                </c:choose>
                ${paginaAtual}
                <c:choose>
                    <c:when test="${paginaAtual < qtdPaginas}">
                        <a href="<c:url value="/funcionarios/pagina/${paginaAtual+1}" />">></a> 
                    </c:when>
                    <c:otherwise>
                        >
                    </c:otherwise>
                </c:choose>
                <a href="<c:url value="/funcionarios/pagina/${qtdPaginas}" />">>></a>    


            </td>
        </tr>
    </tfoot>
</table>
<br/>

<span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} página(s)</span>
