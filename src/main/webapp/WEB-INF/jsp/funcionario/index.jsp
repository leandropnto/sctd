<style type="text/css">
    table {
        height: 90%; padding-top: 10px; padding-left: 10px; width: 100% ;
    }
    thead th {
        background-color: #FFF;
    }

    tfoot tr{
        background-color: #FFF;
    }

    td{
        font-family: Verdana, Arial, Helvetica, sans-serif;
        color: #930;
    }

    td.data{
        width: 15%;
        text-align: center;
        height: 20px;
    }

    td.descricao{
        width: 85%;
        text-align: left;
    }

    td a{
        text-decoration: none;
        cursor: pointer;
        color: #930;


    }

    span.destaque{
        padding-left: 20px;
    }
</style>
<c:forEach var="error" items="${errors}">
    ${error.category} - ${error.message}<br />
</c:forEach>
<table>
    <thead>
        <tr>
            <th>Matrícula</th>
            <th>Nome</th>
            <th>Dt. Nascimento</th>
            <th>Dt. Contratação</th>
            <th>Cargo</th>
            <th>Departamento</th>

        </tr>
    </thead>
    <tbody>
        <c:forEach items="${funcionarios}" var="funcionario" varStatus="contador">
            <tr>
                <td class="data"><a href="<c:url value="/funcionarios/editar/${funcionario.matricula}"/>">${funcionario.matricula}</a></td>
                <td class="data">${funcionario.nome}</td>               
                <td class="data"><fmt:formatDate value="${funcionario.dataNascimento}" type="both" pattern="dd/MM/yyyy" /></td>
                <td class="data"><fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" /></td>
                <td class="data">${funcionario.cargo}</td>
                <td class="data">${funcionario.departamento}</td>
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

<span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} páginas</span>
