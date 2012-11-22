<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/pessoajuridica/"/>">Pessoa Jurídica</a> > 
            </span>
            <span class="children">Inclusão de Pessoa Jurídica</span>
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
                <form action="<c:url value="/cadastros/pessoajuridica/salvar"/>" method="post" id="form" name="form" class="validate">


                    <fieldset class="formato1">
                        <legend>Inclusão de Pessoa Jurídica</legend>
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
                                    <input type="text" name="pessoa.cnpj" value="${pessoa.cnpj}" style="width: 200px;"
                                           class="required cnpj"/> 
                                    <span>Informe o CPF</span>
                                </label>
                            </li>
                            <li>
                                <label>Nome Oficial<br/>
                                    <input type="text" name="pessoa.nomeOficial" value="${pessoa.nomeOficial}" style="width: 150px; "
                                           class="required"/> 
                                    <span>Informe o Nome Oficial</span>
                                </label>

                                <label>Responsável<br/>
                                    <input type="text" name="pessoa.responsavel" value="${pessoa.responsavel}" style="width: 200px;"
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