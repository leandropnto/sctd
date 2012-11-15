<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/departamentos/"/>">Departamentos</a> > 
            </span>
            <span class="children">Edição de Departamentos</span>
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
                <form action="<c:url value="/cadastros/departamentos/atualizar"/>" method="post" class="validate">
                    
                    <input type="hidden" name="departamento.id" value="${departamento.id}" />
                    <fieldset class="formato1">
                        <ul>
                            <li>                    
                                <label>Descrição<br/>
                                    <input type="text" name="departamento.descricao" value="${departamento.descricao}" style="width: 100px" 
                                           class="required"/>                      
                                    <span>Informe a descrição</span>
                                </label>                             

                            </li>  

                            <li>
                                <button type="submit" class="button">Atualizar</button>
                            </li>
                            
                        </ul>
                    </fieldset>
                </form>

            </div>

        </section>
    </section>
</section>


