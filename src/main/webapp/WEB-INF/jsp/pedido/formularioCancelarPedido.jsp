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
                <a href="<c:url value="/pedidos/pedido/cancelar"/>">Cancelar</a> > 
            </span>
            <span class="children">Cancelamento de Pedido</span>
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
                <form action="<c:url value="/pedidos/pedido/cancelar/forminfocancelamento"/>" method="post" id="form" name="form" class="validate">                   
                    <fieldset class="formato1">
                        <legend>Cancelamento de Pedido</legend>
                        <ul>
                            <li>
                                <label>ID<b/>
                                    <input type="text" name="pedido.id" class="required numeric"/>
                                    <span>Informe o número do pedido</span>
                                </label>
                            </li>

                            <li>
                                <button type="submit" class="button">Continuar</button>
                            </li>

                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>                  

