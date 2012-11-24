<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/produtos/"/>">Produtos</a> > 
            </span>
            <span class="children">Edição de Produtos</span>
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
                <form action="<c:url value="/cadastros/produtos/atualizar"/>" method="post" id="form" name="form" class="validate">
                    

                    <fieldset class="formato1">
                        <legend>Edição de Produtos</legend>
                        <ul>
                             <li>
                                <label style="width: 210px;">Nome<br/>
                                    <input type="text" name="produto.nome" value="${produto.nome}" style="width: 200px;"
                                           class="required"/> 
                                    <span>Informe o nome da produto</span>
                                </label>
                                <label>Valor<br/>
                                    <input type="text" name="produto.valor" value="${produto.valor}" style="width: 100px;  "
                                           class="required numeric"/> 
                                    <span>Informe o valor</span>
                                </label>

                            </li>                
                            <li>
                                <label style="width: 150px;">Modelo<br/>
                                    <select name="produto.modelo.id" class="required">
                                        <option value>Informe o modelo</option>
                                        <c:forEach items="${modelos}" var="modelo">
                                            <option value="${modelo.id}" <c:if test="${produto.modelo.id == modelo.id}">selected</c:if>>${modelo.descricao}</option>
                                        </c:forEach>
                                    </select>
                                    <span>Informe o modelo</span>
                                </label>
                                <label style="width: 150px;">Botão<br/>
                                    <select name="produto.botao.id" class="required">
                                        <option value>Informe o Botão</option>
                                        <c:forEach items="${botoes}" var="botao">
                                            <option value="${botao.id}" <c:if test="${produto.botao.id == botao.id}">selected</c:if>>${botao.nome}</option>
                                        </c:forEach>
                                    </select>
                                    <span>Informe o botão</span>
                                </label>
                                <label style="width: 150px;">Cor<br/>
                                    <select name="produto.cor.id" class="required">
                                        <option value>Informe a cor</option>
                                        <c:forEach items="${cores}" var="cor">
                                            <option value="${cor.id}" <c:if test="${produto.cor.id == cor.id}">selected</c:if>>${cor.nome}</option>
                                        </c:forEach>
                                    </select>
                                    <span>Informe a cor</span>
                                </label>
                                <label style="width: 150px;">Linha<br/>
                                    <select name="produto.linha.id" class="required">
                                        <option value>Informe a linha</option>
                                        <c:forEach items="${linhas}" var="linha">
                                            <option value="${linha.id}" <c:if test="${produto.linha.id == linha.id}">selected</c:if>>${linha.nome}</option>
                                        </c:forEach>
                                    </select>
                                    <span>Informe a linha</span>
                                </label>
                            </li>                 

                            <li>
                                <input type="hidden" name="produto.id" value="${produto.id}" />
                                <input type="hidden" name="produto.quantidade" value="${produto.quantidade}" />
                                <input type="hidden" name="produto.status" value="${produto.status}" />
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