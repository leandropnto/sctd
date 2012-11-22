<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/pessoafisica/"/>">Pessoa Física</a> > 
            </span>
            <span class="children">Edição de Pessoa Física</span>
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
                <form action="<c:url value="/cadastros/pessoafisica/atualizar"/>" method="post" id="form" name="form" class="validate">
                    

                    <fieldset class="formato1">
                        <legend>Edição de Pessoa Física</legend>
                        <ul>
                             <li>
                                <label style="width: 210px;">Nome<br/>
                                    <input type="text" name="pessoa.nome" value="${pessoa.nome}" style="width: 200px;"
                                           class="required"/> 
                                    <span>Informe o nome</span>
                                </label>
                                <label>Telefone<br/>
                                    <input type="text" name="pessoa.telefone" value="${pessoa.telefone}" style="width: 100px;  "
                                           class="required fone"/> 
                                    <span>Informe o telefone</span>
                                </label>

                            </li>                
                            <li>
                                <label>E-mail<br/>
                                    <input type="text" name="pessoa.email" value="${pessoa.email}" style="width: 150px; "
                                           class="required email"/> 
                                    <span>Informe o E-mail</span>
                                </label>

                                <label>CPF<br/>
                                    <input type="text" name="pessoa.cpf" value="${pessoa.cpf}" style="width: 200px; margin-bottom: 30px"
                                           class="required cpf"/> 
                                    <span>Informe o CPF</span>
                                </label>
                            </li>             

                            <li>
                                <input type="hidden" name="pessoa.id" value="${pessoa.id}" />
                                <input type="hidden" name="pessoa.status" value="${pessoa.status}" />
                                <button type="submit" class="button">Atualizar</button>
                            </li>
                            
                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>           