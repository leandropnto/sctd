<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/pedidos/"/>">Pedidos</a> > 
            </span>
            <span class="children">Venda Registrada</span>
        </section>
        <section class="text-box">
            <fieldset class="formato1">
                <legend>Venda Registrada</legend>
                <ul>
                    <li>Data: <fmt:formatDate pattern="dd/MM/yyyy" value="${venda.dataVenda}"/></li>
                    <li>Valor: <fmt:formatNumber currencySymbol="R$" value="${venda.precoTotal}" type="currency" minFractionDigits="2"/></li>
                    <li>Funcionário: ${venda.funcionario.nome}</li>
                 
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
                <c:forEach items="${venda.itens}" var="item" varStatus="contador">
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
                    <th style="width: 75%">Valor</th>
                    <th style="width: 15%; text-align: center">Vencimento</th>                

                </tr>
                <c:forEach items="${venda.fatura.parcelas}" var="parcela" varStatus="contador">
                    <tr>
                        <td>${contador.index + 1}</td>
                        <td><fmt:formatNumber currencySymbol="R$" minFractionDigits="2" value="${parcela.valor}" type="currency"/></td>
                        <td style=" text-align: right"><fmt:formatDate pattern="dd/MM/yyyy" value="${parcela.dataVencimento}" /></td> 
                    </tr>
                </c:forEach>
            </table>                    
        </section>
    </section>
</section>