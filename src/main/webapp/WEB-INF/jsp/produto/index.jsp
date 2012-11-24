<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/produtos/"/>">Produtos</a> > 
            </span>
            <span class="children">Pesquisa de Produtos</span>
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
            <a href="<c:url value="/cadastros/produtos/incluir"/>">Incluir</a>
            <form action="<c:url value="/cadastros/produtos/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento" class="validate">
                <fieldset class="formato1">
                    <legend>Pesquisa de Produtos</legend>
                    <ul>
                        <li>
                            <label style="width: 210px;">Nome<br/>
                                <input type="text" name="produto.nome" value="${produto.nome}" style="width: 200px;"/> 
                                <span>Informe o nome do produto</span>
                            </label>
                        </li>

                        <li>
                            <button type="submit" class="button">Buscar</button>
                        </li>
                        
                    </ul>
                </fieldset>
            </form>


            <c:if test="${produtos.size() >0}">
                <table id="mytable">
                    <caption>Produtos cadastradas</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>                                        
                            <th>Quantidade</th>                                        
                            <th>Status</th>                                        
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${produtos}" var="produto" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/cadastros/produtos/editar/${produto.id}"/>">${produto.id}</a></td>
                                <td style="text-align: left; width: 300px;">${produto.nome}</td>                               
                                <td style="text-align: left; width: 50px;">${produto.quantidade}</td>                               
                                <td style="text-align: left; width: 50px;">${produto.status.toString()}</td>                               
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/cadastros/produtos/editar/${produto.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/cadastros/produtos/excluir/${produto.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">Paginação: <a href="<c:url value="/cadastros/produtos/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/cadastros/produtos/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/cadastros/produtos/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/cadastros/produtos/pagina/${qtdPaginas}" />">>></a>    


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