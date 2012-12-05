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
                <a href="<c:url value="/pedidos/entrega/entrega"/>">Registrar Entrega</a> > 
                 
            </span>
            <span class="children">Registro de Entrega</span>
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
                <form action="<c:url value="/pedidos/entrega/registrarentrega"/>" method="post" id="form" name="form" class="validate">
                    <fieldset class="formato1">
                        <legend>Registro de Entrega</legend>
                        <ul>
                            <li>
                                <label style="width: 150px;">Número da venda<br/>
                                    <input type="text" name="venda.id" />
                                    <span>Informe o número</span>
                                </label>
                                <label style="width: 150px;">Número do pedido<br/>
                                    <input type="text" name="pedido.id" />
                                    <span>Informe o número </span>
                                </label>
                                 
                            </li>
                            <li>
                                <label style="width: 150px;">Data da Entrega<br/>
                                    <input type="text" name="data" class="required data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${data}"/>" />
                                    <span>dd/mm/aaaa</span>
                                </label> 
                            </li>

                            <li>
                                <button type="submit" class="button">Registrar</button>
                            </li>

                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>