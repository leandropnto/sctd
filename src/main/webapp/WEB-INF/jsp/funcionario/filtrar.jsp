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
        font: bold 9px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
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




    .myform{
        margin:0 auto;
        width: 680px;
        padding:14px;
        height: 100%;

    }

    fieldset#fdFuncionarios {
        display: block;
        margin: 0;
        margin-bottom: 10px;
        border: 1px groove #B7DDF2;
        border-collapse: collapse;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 14px;
        background-color: #EBF4FB;
        padding-bottom: 10px;

    }
    fieldset#fdFuncionarios ul, fieldset#fdFuncionarios li{
        border:0; margin:0; padding:0; list-style:none;
        margin-bottom: 2px;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        padding-left: 5px;

    }

    fieldset#fdFuncionarios li{
        clear:both;
        list-style:none;
        padding-bottom:10px;   
    }
    fieldset#fdFuncionarios label{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 100px;
    }

    fieldset#fdFuncionarios label.grd{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 470px;
    }   
    fieldset#fdFuncionarios label.med{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 330px;
    }     

    fieldset#fdFuncionarios label.peq{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 80px;
    }    

    .info, .success, .warning, .error, .validation {

        border: 1px solid;

        margin: 10px 0px;

        padding:15px 10px 15px 50px;

        background-repeat: no-repeat;

        background-position: 10px center;

    }

    .info {

        color: #00529B;

        background-color: #BDE5F8;

        background-image: url('<c:url value="/images/info.png"/>');

    }

    .success {

        color: #4F8A10;

        background-color: #DFF2BF;

        background-image:url('<c:url value="/images/success.png" />');

    }

    .warning {

        color: #9F6000;

        background-color: #FEEFB3;

        background-image: url('<c:url value="/images/warning.png"/>');

    }

    .error {

        color: #D8000C;

        background-color: #FFBABA;

        background-image: url('<c:url value="/images/error.png"/>');

    }    


</style>
<c:forEach var="error" items="${errors}">
    ${error.category} - ${error.message}<br />
</c:forEach>

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
