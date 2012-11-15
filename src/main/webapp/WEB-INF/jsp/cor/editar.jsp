<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/cores/"/>">Cores</a> > 
            </span>
            <span class="children">Edição de Cores</span>
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
                <form action="<c:url value="/cadastros/cores/atualizar"/>" method="post" id="form" name="form" class="validate">
                    

                    <fieldset class="formato1">
                        <legend>Edição de Cores</legend>
                        <ul>
                            <li>
                                <label>Descrição<br/>
                                    <input type="text" name="cor.nome" value="${cor.nome}" style="width: 200px; margin-bottom: 30px"
                                           class="required"/>                      
                                    <span>Informe a descrição</span>
                                </label>

                            </li>                

                            <li>
                                <input type="hidden" name="cor.id" value="${cor.id}" />
                                <button type="submit" class="button">Atualizar</button>
                            </li>
                            <div class="spacer"></div>
                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>           