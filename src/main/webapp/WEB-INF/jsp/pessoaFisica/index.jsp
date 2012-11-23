<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/pessoafisica/"/>">Pessoa Física</a> > 
            </span>
            <span class="children">Pesquisa de Pessoa Física</span>
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
            <a href="<c:url value="/cadastros/pessoafisica/incluir"/>">Incluir</a>
            <form action="<c:url value="/cadastros/pessoafisica/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento" class="validate">
                <fieldset class="formato1">
                    <legend>Pesquisa de Pessoa Física</legend>
                    <ul>
                        <li>
                            <label style="width: 210px;">Nome<br/>
                                <input type="text" name="pessoa.nome" value="${pessoa.nome}" style="width: 200px;"/> 
                                <span>Informe o nome da pessoa</span>
                            </label>
                            <label>Telefone<br/>
                                <input type="text" name="pessoa.telefone" value="${pessoa.telefone}" style="width: 200px;  "/> 
                                <span>Informe o Telefone</span>
                            </label>

                        </li>                
                        <li>
                            <label style="width: 100px;">E-mail<br/>
                                <input type="text" name="pessoa.email" value="${pessoa.email}" style="width: 80px;"/> 
                                <span>Informe o e-mail</span>
                            </label>
                            <label>CPF<br/>
                                <input type="text" name="pessoa.cpf" value="${pessoa.cpf}" style="width: 150px; "/> 
                                <span>Informe o CPF</span>
                            </label>

                        </li>                
 


                        <li>
                            <button type="submit" class="button">Buscar</button>
                        </li>
                       
                    </ul>
                </fieldset>
            </form>


            <c:if test="${pessoas.size() >0}">
                <table id="mytable">
                    <caption>Pessoas cadastradas</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>                            
                            <th>Telefone</th>
                            <th>E-Mail</th>
                            <th>CPF</th>           
                            <th>Status</th>           
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pessoas}" var="pessoa" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/cadastros/pessoafisica/editar/${pessoa.id}"/>">${pessoa.id}</a></td>
                                <td style="text-align: left; width: 200px;">${pessoa.nome}</td>
                                <td style="text-align: left; width: 80px;">${pessoa.telefone}</td>
                                <td style="text-align: left; width: 150px;">${pessoa.email}</td>
                                <td style="text-align: left; width: 150px;">${pessoa.cpf}</td>
                                <td style="text-align: left; width: 150px;">${pessoa.status.toString()}</td>
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/cadastros/pessoafisica/editar/${pessoa.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/cadastros/pessoafisica/excluir/${pessoa.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">Paginação: <a href="<c:url value="/cadastros/pessoafisica/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/cadastros/pessoafisica/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/cadastros/pessoafisica/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/cadastros/pessoafisica/pagina/${qtdPaginas}" />">>></a>    


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