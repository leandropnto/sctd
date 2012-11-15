<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/departamentos/"/>">Departamentos</a> > 
            </span>
            <span class="children">Pesquisa de Departamentos</span>
        </section>
        <section class="text-box">
            <c:if test="${errors.size()>0}">
                <div class="error">
                    <c:forEach var="error" items="${errors}">
                        ${error.category} - ${error.message}<br />
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${msg != null}">
                <div class="success">
                    ${msg}
                </div>
            </c:if>

            <br/>

            <a href="<c:url value="/cadastros/departamentos/incluir"/>">Incluir</a> 
            <form action="<c:url value="/cadastros/departamentos/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento" class="validate">
                <fieldset class="formato1">
                    <legend>Pesquisa de Departamentos</legend>
                    <ul>
                        <li>
                            <label style="width: 110px; padding-bottom: 30px">Descrição<br/>
                                <input type="text" name="departamento.descricao" value="${departmaento.descricao}" style="width: 100px"/>                      

                            </label>                

                        </li>


                        <li>
                            <button type="submit" class="button">Buscar</button>
                        </li>

                    </ul>
                </fieldset>
            </form>

            <br/>
            <c:if test="${departamentos.size() >0}">
                <table id="mytable">
                    <caption>Departamentos cadastrados</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>           
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${departamentos}" var="departamento" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/cadastros/departamentos/editar/${departamento.id}"/>">${departamento.id}</a></td>
                                <td style="text-align: left; width: 300px;">${departamento.descricao}</td>
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/cadastros/departamentos/editar/${departamento.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/cadastros/departamentos/excluir/${departamento.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">Paginação: <a href="<c:url value="/cadastros/departamentos/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/cadastros/departamentos/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/cadastros/departamentos/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/cadastros/departamentos/pagina/${qtdPaginas}" />">>></a>    


                            </td>
                        </tr>
                    </tfoot>
                </table>
                <br/>

                <span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} página(s)</span>
            </c:if>

        </section>
    </section>
</section>