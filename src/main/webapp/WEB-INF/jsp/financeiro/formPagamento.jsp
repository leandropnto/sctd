<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/financeiro/"/>">Financeiro</a> > 
                <a href="<c:url value="/financeiro/pagamento"/>">Registrar Pagamento</a> > 
            </span>
            <span class="children">Registrar Pagamento</span>
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
            <form method="post" action="<c:url value="/financeiro/filtrar"/>" class="validate">
                <fieldset class="formato1">
                    <legend>Registrar pagamento</legend>
                    <ul>
                        <li>
                            <label>
                                Número da fatura<br/>
                                <input type="text" value="${fatura.id}" name="fatura.id" class="required numeric"/>
                                <span>Número</span>
                            </label>
                        </li>
                        <li>
                            <button type="submit" class="button">Buscar</button>
                        </li>

                    </ul>


                </fieldset>
            </form>

            <fieldset class="formato1">
                <legend>
                    Informações da fatura
                </legend>
                <ul>
                    <li></li>
                    <li>Número: ${fatura.id}</li>
                    <li>Data Lançamento: <fmt:formatDate pattern="dd/MM/yyyy" value="${fatura.dataLancamento}" /></li>
                    <li>Valor: <fmt:formatNumber type="currency" value="${fatura.valorTotal}" /></li>
                    <li>Status: ${fatura.status.toString()}</li>
                </ul>
            </fieldset>               

            <table>
                <thead>
                    <tr>
                        <th>N.</th>
                        <th>Vencimento</th>
                        <th>Valor</th>
                        <th>Data Pag.</th>
                        <th>Opções</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${parcelas}" var="parcela" varStatus="contador">
                        <tr>
                            <td>${contador.index+1}</td>
                            <td><fmt:formatDate value="${parcela.dataVencimento}" pattern="dd/MM/yyyy" /></td>
                            <td><fmt:formatNumber type="currency" value="${parcela.valor}" /></td>                           
                            <td><fmt:formatDate value="${parcela.dataPagamento}" pattern="dd/MM/yyyy" /></td>
                            <td><a href="<c:url value="/financeiro/baixar/${parcela.id}"/>">Baixar</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </section>
</section>