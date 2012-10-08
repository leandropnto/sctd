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
<form action="<c:url value="/funcionarios/atualizar"/>" method="post">
    <fieldset>
        <legend>Cadastro de Funcionários</legend>
        <ul>
            <li>
                <label>
                    Nome<br/>
                    <input type="text" name="funcionario.nome" value="${funcionario.nome}"/>
                </label>
            </li>
            <li>
                <label>
                    Data Nascimento<br/>
                    <input type="text" name="funcionario.dataNascimento" value="<fmt:formatDate value="${funcionario.dataNascimento}" type="both" pattern="dd/MM/yyyy" />"/>
                </label>
            </li>
            <li>
                <label>
                    Cargo<br/>
                    <input type="text" name="funcionario.cargo" value="${funcionario.cargo}"/>
                </label>
            </li>
            <li>
                <label>
                    Departamento<br/>
                    <input type="text" name="funcionario.departamento" value="${funcionario.departamento}"/>
                </label>
            </li>
            <li>
                <label>
                    <input type="hidden" name="funcionario.dataContratacao" value="<fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" />"/>
                    <input type="hidden" name="funcionario.matricula" value="${funcionario.matricula}"/>
                    <input type="submit" value="Alterar" />
                </label>
            </li>
        </ul>
    </fieldset>
</form>