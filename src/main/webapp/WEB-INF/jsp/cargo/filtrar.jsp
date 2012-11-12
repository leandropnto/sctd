<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cargos/"/>">Cargos</a> > 
            </span>
            <span class="children">Pesquisa de Cargos</span>
        </section>
        <section class="text-box">


            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>
                <br/>
            <a href="<c:url value="/cargos/incluir"/>">Incluir</a>
            <form action="<c:url value="/cargos/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento">
                <fieldset id="fdDepartamentos" style="margin-top: 12px;">
                    <ul>
                        <li>
                            <label style="width: 110px; padding-bottom: 30px">Descrição<br/>
                                <input type="text" name="cargo.descricao" value="${cargo.descricao}" style="width: 200px"/>                      
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
                <caption>Cargos cadastrados</caption>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Descrição</th>           
                        <th>Opções</th>           
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cargos}" var="cargo" varStatus="contador">
                        <tr>
                            <td style="text-align: right; width: 15px;"><a href="<c:url value="/cargos/editar/${cargo.id}"/>">${cargo.id}</a></td>
                            <td style="text-align: left; width: 300px;">${cargo.descricao}</td>
                            <td style="text-align: right; width: 40px;">
                                <a href="<c:url value="/cargos/editar/${cargo.id}"/>">
                                    <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                </a>
                                | <a href="<c:url value="/cargos/excluir/${cargo.id}"/>">
                                    <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">Paginação: <a href="<c:url value="/cargos/pagina/1" />"><<</a> 
                            <c:choose>
                                <c:when test="${paginaAtual>1}">
                                    <a href="<c:url value="/cargos/pagina/${paginaAtual-1}" />"><</a>                         
                                </c:when>
                                <c:otherwise>
                                    <
                                </c:otherwise>
                            </c:choose>
                            ${paginaAtual}
                            <c:choose>
                                <c:when test="${paginaAtual < qtdPaginas}">
                                    <a href="<c:url value="/cargos/pagina/${paginaAtual+1}" />">></a> 
                                </c:when>
                                <c:otherwise>
                                    >
                                </c:otherwise>
                            </c:choose>
                            <a href="<c:url value="/cargos/pagina/${qtdPaginas}" />">>></a>    


                        </td>
                    </tr>
                </tfoot>
            </table>
            <br/>

            <span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} página(s)</span>


        </section>
    </section>
</section>




