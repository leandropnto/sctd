<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/tiposmodelo/"/>">Tipos</a> > 
            </span>
            <span class="children">Edição de Tipos</span>
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
                <form action="<c:url value="/cadastros/tiposmodelo/atualizar"/>" method="post" id="form" name="form" class="validate">
                    

                    <fieldset class="formato1">
                        <legend>Edição de Tipos</legend>
                        <ul>
                            <li>
                                <label>Tipo<br/>
                                    <input type="text" name="tipo.nome" value="${tipo.nome}" style="width: 200px;"
                                           class="required"/>  
                                    <span>Nome do tipo</span>
                                </label>

                            </li>                
                            <li>
                                <label>Peso<br/>
                                    <input type="text" name="tipo.peso" value="${tipo.peso}" style="width: 100px; margin-bottom: 30px"
                                           class="textoMedio"/>  
                                    <span>Informe o peso</span>
                                </label>

                            </li>                

                            <li>
                                <input type="hidden" name="tipo.id" value="${tipo.id}" />
                                <button type="submit" class="button">Atualizar</button>
                            </li>
                            
                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>           