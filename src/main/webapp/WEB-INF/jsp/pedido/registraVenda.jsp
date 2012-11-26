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
                    <li>Data: ${venda.dataVenda}</li>
                    <li>Valor ${venda.precoTotal}</li>
                    <li>Itens</li>
                    <li>
                        <table>
                            <tr>
                                <td colspan="3">Data da Venda: ${venda.dataVenda}</td>
                            </tr>
                            <tr>
                                <td colspan="3">Valor: ${venda.precoTotal}</td>
                            </tr>
                            <tr>
                                <td colspan="3">Itens</td>
                            </tr>
                            <tr>
                                <td>Núm.</td>
                                <td>Produto</td>
                                <td>Qtde</td>
                                
                            </tr>
                            <c:forEach items="${venda.itens}" var="item" varStatus="contador">
                            <tr>
                                <td>${contador.index}</td>
                                <td>${item.produto.nome}</td>
                                <td>${item.quantidade}</td>
                                
                            </tr>
                            </c:forEach>
                        </table>
                    </li>
                </ul>
            </fieldset>
        </section>
    </section>
</section>