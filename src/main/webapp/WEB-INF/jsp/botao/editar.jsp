<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/botoes/"/>">Botões</a> > 
            </span>
            <span class="children">Edição de Botões</span>
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
                <form action="<c:url value="/cadastros/botoes/atualizar"/>" method="post" id="form" name="form" class="validate">
                    

                    <fieldset class="formato1">
                        <legend>Edição de Botões</legend>
                        <ul>
                            <li>
                                <label>Descrição<br/>
                                    <input type="text" name="botao.nome" value="${botao.nome}" style="width: 200px; margin-bottom: 30px"
                                           class="required"/>  
                                    <span>Nome do botão</span>
                                </label>

                            </li>                

                            <li>
                                <input type="hidden" name="botao.id" value="${botao.id}" />
                                <button type="submit" class="button">Atualizar</button>
                            </li>
                            
                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>           