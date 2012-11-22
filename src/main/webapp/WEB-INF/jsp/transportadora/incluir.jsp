<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/transportadoras/"/>">Transportadoras</a> > 
            </span>
            <span class="children">Inclusão de Transportadoras</span>
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
                <form action="<c:url value="/cadastros/transportadoras/salvar"/>" method="post" id="form" name="form" class="validate">


                    <fieldset class="formato1">
                        <legend>Inclusão de Transportadoras</legend>
                        <ul>
                            <li>
                                <label style="width: 210px;">Descrição<br/>
                                    <input type="text" name="transportadora.nome" value="${transportadora.nome}" style="width: 200px;"
                                           class="required"/> 
                                    <span>Informe o nome da transportadora</span>
                                </label>
                                <label>CNPJ<br/>
                                    <input type="text" name="transportadora.cnpj" value="${transportadora.cnpj}" style="width: 200px;  "
                                           class="required cnpj"/> 
                                    <span>Informe CNPJ</span>
                                </label>

                            </li>                
                            <li>
                                <label style="width: 100px;">Telefone<br/>
                                    <input type="text" name="transportadora.telefone" value="${transportadora.telefone}" style="width: 80px;"
                                           class="required fone"/> 
                                    <span>Informe o nome telefone</span>
                                </label>
                                <label>E-mail<br/>
                                    <input type="text" name="transportadora.email" value="${transportadora.email}" style="width: 150px; "
                                           class="required email"/> 
                                    <span>Informe o E-mail</span>
                                </label>

                            </li>                
                            <li>
                                <label>Responsável<br/>
                                    <input type="text" name="transportadora.responsavel" value="${transportadora.responsavel}" style="width: 200px; margin-bottom: 30px"
                                           class="required"/> 
                                    <span>Informe o responsável</span>
                                </label>
                            </li>
                            <li>
                                <button type="submit" class="button">Cadastrar</button>
                            </li>

                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>           