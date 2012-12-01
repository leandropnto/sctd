<style>
    .ui-autocomplete-loading {
        background: white url('images/ui-anim_basic_16x16.gif') right center no-repeat;
    }

    p.clicavel { color:red; margin:5px; cursor:pointer; }


</style>

<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/pedidos/"/>">Pedidos</a> > 
                <a href="<c:url value="/pedidos/pedido/listar"/>">Listar</a> > 
            </span>
            <span class="children">Listagem de Pedidos</span>
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

            <div id="stylized" class="myform">
                <form action="<c:url value="/pedidos/pedido/filtrar"/>" method="post" id="form" name="form" class="validate">                   
                    <fieldset class="formato1">
                        <legend>Pesquisa de Pedidos</legend>
                        <ul>
                            <li>
                                <label>ID<b/>
                                    <input type="text" name="pedido.id" class="numeric"/>
                                    <span>Informe o número do pedido</span>
                                </label>
                                <label>Status do Pedido<b/>
                                    <select name="pedido.status">
                                        <option value>Selecione o Status</option>
                                        <c:forEach items="${listaStatus}" var="status">
                                            <option value="${status.ordinal()}">${status.toString()}</option>
                                        </c:forEach>
                                    </select>

                                </label>
                            </li>
                           
                            <li>
                                <button type="submit" class="button">Continuar</button>
                            </li>

                        </ul>
                    </fieldset>
                </form>
            </div>

            <c:if test="${pedidos.size() >0}">
                <table id="mytable" style="width: 100%">
                    <caption>Pedidos cadastrados</caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Preço</th>           
                            <th>Valor Pago</th>           
                            <th>Data</th>           
                            <th>Entrega</th>           
                            <th>Cliente</th>           
                            <th>Funcionário</th>           
                            <th>Opções</th>           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pedidos}" var="pedido" varStatus="contador">
                            <tr>
                                <td style="text-align: right; width: 15px;"><a href="<c:url value="/pedidos/pedido/info/${pedido.id}"/>">${pedido.id}</a></td>
                                <td style="text-align: left; width: 150px;"><fmt:formatNumber type="currency" value="${pedido.precoTotal}"/></td>
                                <td style="text-align: left; width: 100px;"><fmt:formatNumber type="currency" value="${pedido.valorPago}"/></td>
                                <td style="text-align: right; width: 30px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.dataPedido}"/></td>
                                <td style="text-align: left; width: 50px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.dataEntrega}"/></td>
                                <td style="text-align: left; width: 60px;">${pedido.cliente.nome}</td>
                                <td style="text-align: left; width: 60px;">${pedido.funcionario.nome}</td>
                                <td style="text-align: right; width: 40px;">
                                    <a href="<c:url value="/pedidos/pedido/info/${pedido.id}"/>">
                                        <img src="<c:url value="/images/editar_peq.png"/>" alt="Editar" title="Informações"/>
                                    </a>
                                    
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">Paginação: <a href="<c:url value="/pedidos/pedido/pagina/1" />"><<</a> 
                                <c:choose>
                                    <c:when test="${paginaAtual>1}">
                                        <a href="<c:url value="/pedidos/pedido/pagina/${paginaAtual-1}" />"><</a>                         
                                    </c:when>
                                    <c:otherwise>
                                        <
                                    </c:otherwise>
                                </c:choose>
                                ${paginaAtual}
                                <c:choose>
                                    <c:when test="${paginaAtual < qtdPaginas}">
                                        <a href="<c:url value="/pedidos/pedido/pagina/${paginaAtual+1}" />">></a> 
                                    </c:when>
                                    <c:otherwise>
                                        >
                                    </c:otherwise>
                                </c:choose>
                                <a href="<c:url value="/pedidos/pedido/pagina/${qtdPaginas}" />">>></a>    


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

