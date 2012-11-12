
<div class="<c:if test="${errors.size() > 0}">error</c:if>">
    <c:forEach var="error" items="${errors}">
        ${error.category} - ${error.message}<br />
    </c:forEach>
</div>
<div id="stylized" class="myform">
    <form action="<c:url value="/funcionarios/salvar"/>" method="post" id="form" name="form">
        <h1>SCTD - Funcionários</h1>
        <p>Formulário para cadastro de funcionários</p>

        <fieldset id="fdFuncionarios">
            <ul>
                <li>
                    <label>CPF<br/>
                        <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" style="width: 100px"/>                      
                    </label>

                </li>                
                <li>
                    <label>Nome<br/>
                        <input type="text" name="funcionario.nome" value="${funcionario.nome}" style="width: 270px"/>                      
                    </label>

                </li>

                <li>
                    <label>Nascimento<br/>
                        <input type="text" name="funcionario.dataNascimento" value="${funcionario.dataNascimento}" id="dataNascimento"
                               style="width: 75px;"/>
                    </label>   
                    <label>Salário<br/>
                        <input type="text" name="funcionario.salario" value="${funcionario.salario}" id="salario"
                               style="width: 75px;"/>
                    </label>      
                </li>
                <li>
                    <label style="width: 200px; padding-bottom: 30px">Cargo<br/>                        
                        <select name="funcionario.cargo.id">
                            <option value="-1">Selecione o Cargo</option>
                            <c:forEach items="${cargos}" var="cargo">
                                <option value="${cargo.id}">${cargo.descricao}</option>
                            </c:forEach>
                        </select>                          
                    </label>
                    <label style="width: 200px; padding-bottom: 30px">Departamento<br/>                      
                        <select name="funcionario.departamento.id">
                            <option value="-1">Selecione o Departamento</option>
                            <c:forEach items="${departamentos}" var="departamento">
                                <option value="${departamento.id}">${departamento.descricao}</option>
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