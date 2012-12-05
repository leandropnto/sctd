<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/financeiro/"/>">Financeiro</a> > 
            </span>
            <span class="children">Opções do Financeiro</span>
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
            <fieldset>
                <ul>
                    <li><a href="<c:url value="/financeiro/pagamento"/>">Registrar Pagamento</a></li>

                </ul>
                <hr/>

            </fieldset>
        </section>
    </section>
</section>