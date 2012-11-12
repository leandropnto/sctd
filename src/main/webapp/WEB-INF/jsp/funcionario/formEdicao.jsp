
<c:forEach var="error" items="${errors}">
    ${error.category} - ${error.message}<br />
</c:forEach>
<div id="stylized" class="myform">    
    <form action="<c:url value="/funcionarios/atualizar"/>" method="post">
        <h1>SCTD - Funcionários</h1>
        <p>Formulário de edição de funcionários</p>
        <input type="hidden" name="funcionario.matricula" value="${funcionario.matricula}" />
        <input type="hidden" name="funcionario.dataContratacao" 
               value="<fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" />"/>
        <fieldset id="fdFuncionarios">
            <ul>
                <li>
                    <input type="hidden" value="${funcionario.matricula}" name="funcionario.matricula"/>
                    <label>CPF<br/>
                        <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" style="width: 100px" readonly="true"
                               style="background-color: #666"/>                      
                    </label>                             
                    <label>Data Contratação<br/>
                        <input type="text" name="funcionario.dataContratacao" value="<fmt:formatDate value="${funcionario.dataContratacao}" type="both" pattern="dd/MM/yyyy" />" id="dataContratacao"
                               style="width: 75px;"/>
                    </label>   
                </li>  
                <li>
                    <label>Nome<br/>
                        <input type="text" name="funcionario.nome" value="${funcionario.nome}" style="width: 270px"/>                      
                    </label>

                </li>

                <li>
                    <label>Nascimento<br/>
                        <input type="text" name="funcionario.dataNascimento" value="<fmt:formatDate value="${funcionario.dataNascimento}" type="both" pattern="dd/MM/yyyy" />" id="dataNascimento"
                               style="width: 75px;"/>
                    </label>                
                </li>
                <li>
                    <label style="width: 155px; padding-bottom: 30px">Cargo<br/>                        
                        <select name="funcionario.cargo.id">
                            <option value="-1">Selecione o Cargo</option>
                            <c:forEach items="${cargos}" var="cargo">
                                <option value="${cargo.id}" <c:if test="${cargo.id == funcionario.cargo.id}">selected</c:if> >${cargo.descricao}</option>
                            </c:forEach>
                        </select>                          
                    </label>
                    <label style="width: 200px; padding-bottom: 30px">Departamento<br/>                      
                        <select name="funcionario.departamento.id">
                            <option value="-1">Selecione o Departamento</option>
                            <c:forEach items="${departamentos}" var="departamento">
                                <option value="${departamento.id}" <c:if test="${departamento.id == funcionario.departamento.id}">selected</c:if> >
                                    ${departamento.descricao}
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                    <label style="width: 200px; padding-bottom: 30px">Status<br/>                      
                        <select name="funcionario.status.id">
                            <option value="-1">Selecione o Departamento</option>
                            <c:forEach items="${listastatus}" var="st">
                                <option value="${st.id}" <c:if test="${st.id == funcionario.status.id}">selected</c:if> >
                                    ${st.descricao}
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                </li>
                <li>
                    <label style="width: 200px; padding-bottom: 30px">Especialidade<br/>                      
                        <select name="funcionario.especialidade.id">
                            <option value="-1">Selecione a Especialidade</option>
                            <c:forEach items="${listaEspecialidades}" var="especialidade">
                                <option value="${especialidade.id}" <c:if test="${especialidade.id == funcionario.especialidade.id}">selected</c:if> >
                                    ${especialidade.descricao}
                                </option>
                            </c:forEach>
                        </select>
                    </label>                    
                </li>
                <li>
                    <button type="submit" style="color:#0029FF; width: 100px; font-family: arial; font-weight: bold">Cadastrar</button>
                </li>
                <div class="spacer"></div>
            </ul>
        </fieldset>
    </form>

</div>

<script type="text/javascript">   
    $(function() {
        $( "#dataNascimento" ).datepicker();
    });    
</script>   
