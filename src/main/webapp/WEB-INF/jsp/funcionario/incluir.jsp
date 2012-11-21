<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/funcionarios/"/>">Funcionários</a> >                
            </span>
            <span class="children">Inclusão de Funcionários</span>
        </section>
        <section class="text-box">

            <div class="<c:if test="${errors.size() > 0}">error</c:if>">
                <c:forEach var="error" items="${errors}">
                    ${error.category} - ${error.message}<br />
                </c:forEach>
            </div>
            <div id="stylized" class="myform">
                <form action="<c:url value="/cadastros/funcionarios/salvar"/>" method="post" id="form" name="formulario" class="validate">       

                    <fieldset class="formato1">
                        <legend>Inclusão de Funcionários</legend>
                        <ul>

                            <li>
                                <label class="med">CPF<br/>
                                    <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" id="cpf" class="required cpf"/>                      
                                           <span>Informe um CPF válido</span>
                                </label>
                                <label class="med">Nome<br/>
                                    <input type="text" name="funcionario.nome" id="nome" class="required textoMedio" value="${funcionario.nome}" />
                                    <span>Informe o nome</span>
                                </label>
                            </li>                


                            <li>
                                <label>Nascimento<br/>
                                    <input type="text" name="funcionario.dataNascimento" value="${funcionario.dataNascimento}" id="dataNascimento"
                                           style="width: 75px;" class="required data"/>
                                    <span>dd/mm/yyyy</span>
                                </label>   
                                <label>Salário<br/>
                                    <input type="text" name="funcionario.salario" value="${funcionario.salario}" id="salario"
                                           style="width: 75px;" class="required numeric"/>
                                    <span>Salário</span>
                                </label>      
                            </li>
                            <li>
                                <label>Cargo<br/>                        
                                    <select name="funcionario.cargo.id" class="required">
                                        <option value>Selecione o Cargo</option>
                                        <c:forEach items="${cargos}" var="cargo">
                                            <option value="${cargo.id}">
                                                <c:if test="${cargo.id == funcionario.cargo.id}">selected</c:if> 
                                                ${cargo.descricao}
                                            </option>
                                        </c:forEach>
                                    </select>     
                                    <span>Selecione o cargo</span>
                                </label>
                                <label>Departamento<br/>                      
                                    <select name="funcionario.departamento.id" class="required">
                                        <option value>Selecione o Departamento</option>
                                        <c:forEach items="${departamentos}" var="departamento">
                                            <option value="${departamento.id}"  
                                                    <c:if test="${departamento.id == funcionario.departamento.id}">selected</c:if> 
                                                    >${departamento.descricao}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span>Selecione o Departamento</span>
                                </label>
                                <label>Status<br/>                      
                                    <select name="funcionario.status.id" class="required">
                                        <option>Selecione o Departamento</option>
                                        <c:forEach items="${listastatus}" var="st">
                                            <option value="${st.id}" <c:if test="${st.id == funcionario.status.id}">selected</c:if> >
                                                ${st.descricao}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span>Selecione o Status</span>
                                </label>
                            </li>
                            <li>
                                <label>Especialidade<br/>                      
                                    <select name="funcionario.tipoEspecialidades[]" class="required" multiple>                                        
                                        <c:forEach items="${listaEspecialidades}" var="especialidade">
                                            <option value="${especialidade.id}" 
                                                    <c:if test="${especialidade.id == funcionario.especialidade.id}">selected</c:if> >
                                                ${especialidade.descricao}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span>Selecione a especialidade</span>
                                </label>                    
                            </li>                
                            <li>
                                <button type="submit" class="button">Cadastrar</button>
                            <li>

                        </ul>
                    </fieldset>
                </form>
            </div>                                         
        </section>
    </section>
</section>  