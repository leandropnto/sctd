<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/pedidos/"/>">Pedidos</a> > 
                <a href="<c:url value="/pedidos/pedido/cancelar"/>">Cancelar</a> > 
            </span>
            <span class="children">Cancelamento de Pedido</span>
        </section>
        <section class="text-box">
            <fieldset class="formato1">
                <legend>Informações do Pedido</legend>
                <ul>
                    <li>Data: <fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.dataPedido}"/></li>
                    <li>Valor: <fmt:formatNumber currencySymbol="R$" value="${pedido.precoTotal}" type="currency" minFractionDigits="2"/></li>
                    <li>Funcionário: ${pedido.funcionario.nome}</li>

                </ul>
            </fieldset>
            <table style="width: 100%">

                <tr>
                    <th colspan="4">Itens</th>
                </tr>
                <tr>
                    <th style="width: 5%">Núm.</th>
                    <th style="width: 75%">Produto</th>
                    <th style="width: 15%">Preço</th>
                    <th style="width: 5%">Qtde</th>

                </tr>
                <c:forEach items="${pedido.itens}" var="item" varStatus="contador">
                    <tr>
                        <td>${contador.index + 1}</td>
                        <td>${item.produto.nome}</td>
                        <td>${item.produto.valor}</td>
                        <td>${item.quantidade}</td>

                    </tr>
                </c:forEach>
            </table>

            <table style="width: 100%">
                <tr>
                    <th style="width: 5%">Parc.</th>
                    <th style="width: 60%">Valor</th>
                    <th style="width: 15%; text-align: center">Vencimento</th>                
                    <th style="width: 20%; text-align: center">Situação</th>                

                </tr>
                <c:forEach items="${pedido.fatura.parcelas}" var="parcela" varStatus="contador">
                    <tr>
                        <td>${contador.index + 1}</td>
                        <td><fmt:formatNumber currencySymbol="R$" minFractionDigits="2" value="${parcela.valor}" type="currency"/></td>
                        <td style=" text-align: right"><fmt:formatDate pattern="dd/MM/yyyy" value="${parcela.dataVencimento}" /></td> 
                        <td style=" text-align: right">
                            <c:choose >
                                <c:when test="${parcela.dataPagamento != null}">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${parcela.dataVencimento}" />
                                </c:when>
                                <c:otherwise>
                                    Não Pago
                                </c:otherwise>
                            </c:choose>

                        </td> 
                    </tr>
                </c:forEach>
                <tr><th colspan="4">Informações da Entrega</th></tr>
                <tr>
                    <th>ID</th>
                    <th>Valor</th>
                    <th>Data Recolhimento</th>
                    <th>Data entrega</th>
                </tr>
                <tr>
                    <td>${pedido.entrega.id}</td>
                    <td><fmt:formatNumber currencySymbol="R$" minFractionDigits="2" value="${pedido.entrega.preco}" type="currency"/></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.entrega.dataRecolhimento}" /></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.entrega.dataEntrega}" /></td>                        
                </tr>
            </table>   

                    <form action="<c:url value="/pedidos/pedido/cancelar/registarcancelamento"/>" class="validade" method="post" 
                          style="margin-top: 30px;">
                <fieldset class="formato1">
                    <ul>
                        <li>&nbsp; <input type="hidden" name="pedido.id" value="${pedido.id}"/>
                        </li>
                        <li>
                            <button type="submit" class="button">Cancelar</button>
                        </li>
                    </ul>
                </fieldset>
            </form>        
        </section>
    </section>
</section>