<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/relatorios/"/>">Relatórios</a> > 
            </span>
            <span class="children">Produtos Mais Vendidos</span>
        </section>
        <section class="text-box">
            <form class="validate" action="<c:url value="/relatorios/produtos/maisvendidos/gerar" />" method="post">
                <fieldset class="formato1">
                    <legend>Produtos Mais Vendidos</legend>
                    <ul>
                        <li>
                            <label style="width: 100px;">Data inicial<br/>
                                <input type="text" name="dataInicial" class="required data" style="width: 100"/>
                                <span>dd/mm/aaaa</span>
                            </label>
                            <label style="width: 85px;">Data Final<br/>
                                <input type="text" name="dataFinal" class="required data" 
                                       value='<fmt:formatDate value="${data}" type="both" pattern="dd/MM/yyyy" />'/>
                                <span>dd/mm/aaaa</span>
                            </label>
                        </li>
                        <li>
                            <button type="submit" class="button">Gerar</button>
                        </li>

                    </ul>
                </fieldset>
            </form>
        </section>
    </section>
</section>