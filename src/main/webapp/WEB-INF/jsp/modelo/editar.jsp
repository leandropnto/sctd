<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/Modelos/"/>">Modelos</a> > 
            </span>
            <span class="children">Edição de Modelos</span>
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
                <form action="<c:url value="/cadastros/modelos/atualizar"/>" method="post" id="form" name="form" class="validate">


                    <fieldset class="formato1">
                        <legend>Edição de Modelos</legend>
                        <ul>
                            <li>
                                <input type="hidden" name="modelo.id" value="${modelo.id}"/>
                                <label style="width: 220px;">Nome<br/>
                                    <input type="text" name="modelo.nome" value="${modelo.nome}" style="width: 200px"/>                      
                                </label>                
                                <label>Descrição<br/>
                                    <input type="text" name="modelo.descricao" value="${modelo.descricao}" style="width: 180px"/>                      
                                </label>  
                            </li>
                            <li>
                                <label>Tamanho<br/>
                                    <input type="text" name="modelo.tamanho" value="${modelo.tamanho}" />                      
                                </label>    
                                <label>Molde<br/>
                                    <input type="text" name="modelo.molde" value="${modelo.molde}" />                      
                                </label>   
                                <label>Sexo<br/>
                                    <select name="modelo.sexo">
                                        <option value>Selecione o Sexo</option>
                                        <option value="M" <c:if test="${modelo.sexo == 'M'.charAt(0)}"> selected</c:if>>Masculino</option>
                                        <option value="F" <c:if test="${modelo.sexo == 'F'.charAt(0)}"> selected</c:if>>Feminino</option>
                                        <option value="U" <c:if test="${modelo.sexo == 'U'.charAt(0)}"> selected</c:if>>Unisex</option>
                                    </select>
                                </label>                

                            </li>                     
                            <li>
                                <label>Tipo<br/>
                                    <select name="modelo.tipo.id">
                                        <c:forEach items="${tipos}" var="tipo">
                                            <option value="${tipo.id}" <c:if test="${modelo.tipo.id == tipo.id}"> selected</c:if>>${tipo.nome}</option>
                                        </c:forEach>
                                    </select>
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