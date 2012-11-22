<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/transportadoras/"/>">Transportadoras</a> > 
            </span>
            <span class="children">Pesquisa de Transportadoras</span>
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
            <a href="<c:url value="/cadastros/transportadoras/incluir"/>">Incluir</a>
            <form action="<c:url value="/cadastros/transportadoras/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento" class="validate">
                <fieldset class="formato1">
                    <legend>Pesquisa de Transportadoras</legend>
                    <ul>
                        <li>
                            <label style="width: 210px;">Nome<br/>
                                <input type="text" name="transportadora.nome" value="${transportadora.nome}" style="width: 200px;"/> 
                                <span>Informe o nome da transportadora</span>
                            </label>
                            <label>CNPJ<br/>
                                <input type="text" name="transportadora.cnpj" value="${transportadora.cnpj}" style="width: 200px;  "/> 
                                <span>Informe CNPJ</span>
                            </label>

                        </li>                
                        <li>
                            <label style="width: 100px;">Telefone<br/>
                                <input type="text" name="transportadora.telefone" value="${transportadora.telefone}" style="width: 80px;"/> 
                                <span>Informe o nome telefone</span>
                            </label>
                            <label>E-mail<br/>
                                <input type="text" name="transportadora.email" value="${transportadora.email}" style="width: 150px; "/> 
                                <span>Informe o E-mail</span>
                            </label>

                        </li>                
                        <li>
                            <label>Responsável<br/>
                                <input type="text" name="transportadora.responsavel" value="${transportadora.responsavel}" style="width: 200px; margin-bottom: 30px"/> 
                                <span>Informe o responsável</span>
                            </label>
                        </li>


                        <li>
                            <button type="submit" class="button">Buscar</button>
                        </li>
                        <div class="spacer"></div>
                    </ul>
                </fieldset>
            </form>


            <c:if test="${transportadoras.size() >0}">
                <table id="mytable">
                    <caption>Transportadoras cadastradas</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CNPJ</th>
                            <th>Telefone</th>
                            <th>E-Mail</th>
                            <th>Responsável</th>           
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${transportadoras}" var="transportadora" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/cadastros/transportadoras/editar/${transportadora.id}"/>">${transportadora.id}</a></td>
                                <td style="text-align: left; width: 200px;">${transportadora.nome}</td>
                                <td style="text-align: left; width: 80px;">${transportadora.cnpj}</td>
                                <td style="text-align: left; width: 80px;">${transportadora.telefone}</td>
                                <td style="text-align: left; width: 150px;">${transportadora.email}</td>
                                <td style="text-align: left; width: 150px;">${transportadora.responsavel}</td>
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/cadastros/transportadoras/editar/${transportadora.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/cadastros/transportadoras/excluir/${transportadora.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">Paginação: <a href="<c:url value="/cadastros/transportadoras/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/cadastros/transportadoras/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/cadastros/transportadoras/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/cadastros/transportadoras/pagina/${qtdPaginas}" />">>></a>    


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