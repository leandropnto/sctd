<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/botoes/"/>">Botões</a> > 
            </span>
            <span class="children">Pesquisa de Botões</span>
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
            <a href="<c:url value="/cadastros/botoes/incluir"/>">Incluir</a>
            <form action="<c:url value="/cadastros/botoes/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento" class="validate">
                <fieldset class="formato1">
                    <legend>Pesquisa de Botões</legend>
                    <ul>
                        <li>
                            <label style="width: 110px; padding-bottom: 30px">Botão<br/>
                                <input type="text" name="botao.nome" value="${botao.nome}" style="width: 100px"/>                      
                            </label>                

                        </li>


                        <li>
                            <button type="submit" class="button">Pesquisar</button>
                        </li>
                        <div class="spacer"></div>
                    </ul>
                </fieldset>
            </form>


            <c:if test="${botoes.size() >0}">
                <table id="mytable">
                    <caption>Botoões cadastradas</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>           
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${botoes}" var="botao" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/cadastros/botoes/editar/${botao.id}"/>">${botao.id}</a></td>
                                <td style="text-align: left; width: 300px;">${botao.nome}</td>
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/cadastros/botoes/editar/${botao.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/cadastros/botoes/excluir/${botao.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2">Paginação: <a href="<c:url value="/cadastros/botoes/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/cadastros/botoes/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/cadastros/botoes/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/cadastros/botoes/pagina/${qtdPaginas}" />">>></a>    


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