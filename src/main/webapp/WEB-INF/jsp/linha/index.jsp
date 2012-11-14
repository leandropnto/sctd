<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/linhas/"/>">linhas</a> > 
            </span>
            <span class="children">Pesquisa de linhas</span>
        </section>
        <section class="text-box">
            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>

            <br/>
            <a href="<c:url value="/cadastros/linhas/incluir"/>">Incluir</a>
            <form action="<c:url value="/cadastros/linhas/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento">
                <fieldset id="fdDepartamentos" style="margin-top: 12px;">
                    <ul>
                        <li>
                            <label style="width: 110px; padding-bottom: 30px">linha<br/>
                                <input type="text" name="linha.nome" value="${linha.nome}" style="width: 100px"/>                      
                            </label>                

                        </li>


                        <li>
                            <button type="submit" style="color:#0029FF; width: 100px; font-family: arial; font-weight: bold">Buscar</button>
                        </li>
                        <div class="spacer"></div>
                    </ul>
                </fieldset>
            </form>


            <c:if test="${linhas.size() >0}">
                <table id="mytable">
                    <caption>linhas cadastradas</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>           
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${linhas}" var="linha" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/cadastros/linhas/editar/${cargo.id}"/>">${cargo.id}</a></td>
                                <td style="text-align: left; width: 300px;">${linha.nome}</td>
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/cadastros/linhas/editar/${linha.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/cadastros/linhas/excluir/${linha.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2">Paginação: <a href="<c:url value="/cadastros/linhas/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/cadastros/linhas/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/cadastros/linhas/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/cadastros/linhas/pagina/${qtdPaginas}" />">>></a>    


                            </td>
                        </tr>
                    </tfoot>
                </table>
                <br/>

                
            </c:if>


            <br/>

            <span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} página(s)</span>

        </section>
    </section>
</section>