<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/modelos/"/>">Modelos</a> > 
            </span>
            <span class="children">Pesquisa de Modelos</span>
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
            <a href="<c:url value="/cadastros/modelos/incluir"/>">Incluir</a>
            <form action="<c:url value="/cadastros/modelos/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento" class="validate">
                <fieldset class="formato1">
                    <legend>Pesquisa de Modelos</legend>
                    <ul>
                        <li>
                            <label style="width: 220px;">Nome<br/>
                                <input type="text" name="modelo.nome" value="${modelo.nome}" style="width: 200px"/>                      
                            </label>                
                            <label>Descrição<br/>
                                <input type="text" name="modelo.descicao" value="${modelo.descricao}" style="width: 180px"/>                      
                            </label>  
                        </li>
                        <li>
                            <label>Tamanho<br/>
                                 <select name="modelo.tamanho" class="">
                                    <option value>Selecione o Tamanho</option>
                                    <option value="P">Pequeno</option>
                                    <option value="M">Médio</option>
                                    <option value="G">Grande</option>
                                </select>
                            </label>    
                            <label>Molde<br/>
                                <input type="text" name="modelo.molde" value="${modelo.molde}" />                      
                            </label>   
                            <label>Sexo<br/>
                                <select name="modelo.sexo">
                                    <option value>Selecione o Sexo</option>
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>
                            </label>                

                        </li>                     
                        <li>
                            <label>Tipo<br/>
                                <select name="modelo.tipo.id">
                                    <c:forEach items="${tipos}" var="tipo">
                                        <option value="${tipo.id}">${tipo.nome}</option>
                                    </c:forEach>
                                </select>
                            </label>                

                        </li>


                        <li>
                            <button type="submit" class="button">Pesquisar</button>
                        </li>
                        
                    </ul>
                </fieldset>
            </form>


            <c:if test="${modelos.size() >0}">
                <table id="mytable">
                    <caption>Modelos cadastrados</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>           
                            <th>Descrição</th>           
                            <th>Tamanho</th>           
                            <th>Molde</th>           
                            <th>Tipo</th>           
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${modelos}" var="modelo" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/cadastros/modelos/editar/${modelo.id}"/>">${modelo.id}</a></td>
                                <td style="text-align: left; width: 150px;">${modelo.nome}</td>
                                <td style="text-align: left; width: 100px;">${modelo.descricao}</td>
                                <td style="text-align: right; width: 30px;">${modelo.tamanho}</td>
                                <td style="text-align: left; width: 50px;">${modelo.molde}</td>
                                <td style="text-align: left; width: 60px;">${modelo.tipo.nome}</td>
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/cadastros/modelos/editar/${modelo.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/cadastros/modelos/excluir/${modelo.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">Paginação: <a href="<c:url value="/cadastros/modelos/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/cadastros/modelos/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/cadastros/modelos/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/cadastros/modelos/pagina/${qtdPaginas}" />">>></a>    


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