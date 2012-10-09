<style type="text/css">


    span.destaque{
        padding-left: 20px;
    }

    #mytable {
        width: 100%;
        padding: 0;
        margin: 0;
    }

    caption {
        padding: 10px 0 5px 0;
        width: 100%;	 
        font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        text-align: right;
    }

    th {
        font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #66A3D3; 
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        border-top: 1px solid #C1DAD7;
        letter-spacing: 2px;
        text-transform: uppercase;
        text-align: left;
        padding: 6px 6px 6px 12px;
        background: #F4F9FE;
    }

    th.nobg {
        border-top: 0;
        border-left: 0;
        border-right: 1px solid #C1DAD7;
        background: none;
    }

    td {
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        background: #fff;
        padding: 6px 6px 6px 12px;
        color: #4f6b72;
    }


    td.alt {
        background: #F5FAFA;
        color: #797268;
    }

    th.spec {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #fff url(images/bullet1.gif) no-repeat;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
    }

    th.specalt {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #f5fafa url(images/bullet2.gif) no-repeat;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #797268;
    }    
</style>
<c:forEach var="error" items="${errors}">
    ${error.category} - ${error.message}<br />
</c:forEach>
<table id="mytable">
    <caption>Funcionários cadastrados</caption>
    <thead>
        <tr>
            <th>Matrícula</th>
            <th>Nome</th>
            <th>Data Nascimento</th>
            <th>Data Contratação</th>
            <th>Cargo</th>
            <th>Departamento</th>

        </tr>
    </thead>
    <tbody>
        <c:forEach items="${funcionarios}" var="funcionario" varStatus="contador">
            <tr>
                <td style="text-align: right"><a href="<c:url value="/funcionarios/editar/${funcionario.matricula}"/>">${funcionario.matricula}</a></td>
                <td>${funcionario.nome}</td>               
                <td><fmt:formatDate value="${funcionario.dataNascimento}" type="both" pattern="dd/MM/yyyy" /></td>
                <td><fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" /></td>
                <td>${funcionario.cargo}</td>
                <td>${funcionario.departamento}</td>
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
