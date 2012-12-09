<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/modelos/"/>">Modelos</a> > 
            </span>
            <span class="children">Inclusão de Modelos</span>
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
                <form action="<c:url value="/cadastros/modelos/salvar"/>" method="post" id="form" name="form" class="validate">
                    

                    <fieldset class="formato1">
                        <legend>Inclusão de Modelos</legend>
                        <ul>
                        <li>
                            <label style="width: 220px;">Nome<br/>
                                <input type="text" name="modelo.nome" value="${modelo.nome}" style="width: 200px" class="required"/>   
                                <span>Informe o nome do modelo</span>
                            </label>                
                            <label>Descrição<br/>
                                <input type="text" name="modelo.descricao" value="${modelo.descricao}" style="width: 180px" class="required"/> 
                                <span>Informe a descrição</span>
                            </label>  
                        </li>
                        <li>
                            <label>Tamanho<br/>
                                <input type="text" name="modelo.tamanho" value="${modelo.tamanho}" class="required numeric"/> 
                                <span>Informe o tamanho</span>
                            </label>    
                            <label>Molde<br/>
                                <input type="text" name="modelo.molde" value="${modelo.molde}" class="required"/>  
                                <span>Informe o molde</span>
                            </label>   
                            <label>Sexo<br/>
                                <select name="modelo.sexo" class="required">
                                    <option value>Selecione o Sexo</option>
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>
                                <span>Informe o sexo</span>
                            </label>                

                        </li>                     
                        <li>
                            <label>Tipo<br/>
                                <select name="modelo.tipo.id" class="required">
                                    <option value>Selecione o Tipo</option>
                                    <c:forEach items="${tipos}" var="tipo">
                                        <option value="${tipo.id}">${tipo.nome}</option>
                                    </c:forEach>
                                </select>
                                <span>Informe o tipo</span>
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