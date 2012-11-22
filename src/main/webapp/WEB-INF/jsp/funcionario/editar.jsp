<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/funcionarios/"/>">Funcionários</a> >                
            </span>
            <span class="children">Edição de Funcionários</span>
        </section>
        <section class="text-box">


            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>
            <div id="stylized" class="myform">    
                <form action="<c:url value="/cadastros/funcionarios/atualizar"/>" method="post" class="validate">

                    <input type="hidden" name="funcionario.matricula" value="${funcionario.matricula}" />
                    <input type="hidden" name="funcionario.dataContratacao" 
                           value="<fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" />"/>
                    <fieldset class="formato1">
                        <legend>Edição de Funcionários</legend>
                        <ul>
                            <li>
                                <input type="hidden" value="${funcionario.matricula}" name="funcionario.matricula"/>
                                <label class="med">CPF<br/>
                                    <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" class="required cpf"/>
                                    <span>Informe um CPF válido</span>
                                </label>   
                                <label>Nome<br/>
                                    <input type="text" name="funcionario.nome" value="${funcionario.nome}" style="width: 270px" 
                                           class="required" />                      
                                    <span>Informe o nome</span>
                                </label>
                            </li>  
                            <li>
                                <label>Data Contratação<br/>
                                    <input type="text" name="funcionario.dataContratacao" value="<fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" />" id="dataContratacao"
                                           style="width: 75px;" class="required data"/>
                                    <span>dd/mm/yyyy</span>
                                </label> 
                                <label>Nascimento<br/>
                                    <input type="text" name="funcionario.dataNascimento" value="<fmt:formatDate value="${funcionario.dataNascimento}" type="both" pattern="dd/MM/yyyy" />" id="dataNascimento"
                                           style="width: 75px;" class="required data"/>
                                    <span>dd/mm/yyyy</span>
                                </label> 

                            </li>
                            <li>
                                <label style="width: 155px; padding-bottom: 30px">Cargo<br/>                        
                                    <select name="funcionario.cargo.id" class="required">
                                        <option value>Selecione o Cargo</option>
                                        <c:forEach items="${cargos}" var="cargo">
                                            <option value="${cargo.id}" <c:if test="${cargo.id == funcionario.cargo.id}">selected</c:if> >${cargo.descricao}</option>
                                        </c:forEach>
                                    </select>       
                                    <span>Selecione o cargo</span>
                                </label>
                                <label style="width: 200px; padding-bottom: 30px">Departamento<br/>                      
                                    <select name="funcionario.departamento.id" class="required">
                                        <option value>Selecione o Departamento</option>
                                        <c:forEach items="${departamentos}" var="departamento">
                                            <option value="${departamento.id}" <c:if test="${departamento.id == funcionario.departamento.id}">selected</c:if> >
                                                ${departamento.descricao}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span>Selecione o Departamento</span>
                                </label>
                                <label style="width: 200px; padding-bottom: 30px">Status<br/>                      
                                    <select name="funcionario.status.id">
                                        <option value>Selecione o Status</option>
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
                                <label style="width: 200px; padding-bottom: 30px">Especialidade<br/>                      
                                    <select name="funcionario.tipoEspecialidades[].id" class="required" multiple>                                       
                                        <c:forEach items="${listaEspecialidades}" var="especialidade" varStatus="contador">
                                            <option value="${especialidade.id}" <c:if test="${funcionario.tipoEspecialidades.contains(especialidade)}">selected</c:if> >
                                                ${especialidade.descricao}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span>Selecione a Especialidade</span>
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