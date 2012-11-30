<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/pedidos/"/>">Pedidos</a> > 
                <a href="<c:url value="/pedidos/ordemservico/"/>">Ordem de Serviço</a> > 
            </span>
            <span class="children">Pesquisa de Ordens de Serviço</span>
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
            <a href="<c:url value="/cadastros/produtos/incluir"/>"></a>
            <form action="<c:url value="/cadastros/produtos/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento" class="validate">
                <fieldset class="formato1">
                    <legend>Pesquisa de Ordens de Serviço</legend>
                    <ul>
                        <li>
                            <label style="width: 210px;">ID<br/>
                                <input type="text" name="ordem.id" value="${ordem.id}" style="width: 100px;"/> 
                                <span>Número da Ordem</span>
                            </label>
                        </li>

                        <li>
                            <button type="submit" class="button">Buscar</button>
                        </li>
                        
                    </ul>
                </fieldset>
            </form>


            <c:if test="${ordens.size() >0}">
                <table id="mytable">
                    <caption>Ordens cadastradas</caption>
                    <thead>
                        <tr>
                            <th>Início</th>
                            <th>Entrega</th>                                        
                            <th>Quantidade</th>                                        
                            <th>Status</th>                                        
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ordens}" var="produto" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/pedidos/ordens/editar/${ordem.id}"/>">${ordem.id}</a></td>
                                <td style="text-align: left; width: 300px;">${ordem.dataInicio}</td>                               
                                <td style="text-align: left; width: 50px;">${ordem.quantidade}</td>                               
                                <td style="text-align: left; width: 50px;">${ordem.status.toString()}</td>                               
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/pedidos/ordem/editar/${ordem.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Alterar"/>
                                    </a>
                                    | <a href="<c:url value="/pedidos/ordem/excluir/${produto.id}"/>">
                                        <img src="<c:url value="/images/excluir_peq.png"/>" alt="Excluir" title="Excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                   
                </table>
                <br/>                
                <span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} página(s)</span>
            </c:if>


        </section>
    </section>
</section>