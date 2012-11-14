<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/funcionarios/"/>">Funcionários</a> >                
            </span>
            <span class="children">Funcionários Cadastrados</span>
        </section>
        <section class="text-box">


            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>

            <a href="<c:url value="/cadastros/funcionarios/form"/>">Incluir</a>
            <form action="<c:url value="/cadastros/funcionarios/filtrar" />" name="frmBuscaFuncionario" id="frmBuscarFuncionario">
                <fieldset id="fdFuncionarios" style="margin-top: 12px;">
                    <ul>
                        <li>
                            <label style="width: 110px; padding-bottom: 30px">CPF<br/>
                                <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" style="width: 100px"/>                      
                            </label>
                            <label style="width: 100px; padding-bottom: 30px">Matrícula<br/>
                                <input type="text" name="funcionario.matricula" value="${funcionario.matricula}" style="width: 60px"/>                      
                            </label>
                            <label style="width: 200px; padding-bottom: 30px">Nome<br/>
                                <input type="text" name="funcionario.nome" value="${funcionario.nome}" style="width: 270px"/>                      
                            </label>

                        </li>

                        <li>
                            <label style="width: 140px; padding-bottom: 30px">Cargo<br/>                        
                                <select name="funcionario.cargo.id">
                                    <option value="-1">Selecione o Cargo</option>
                                    <c:forEach items="${cargos}" var="cargo">
                                        <option value="${cargo.id}">${cargo.descricao}</option>
                                    </c:forEach>
                                </select>                          
                            </label>
                            <label style="width: 200px; padding-bottom: 20px">Departamento<br/>                      
                                <select name="funcionario.departamento.id">
                                    <option value="-1">Selecione o Departamento</option>
                                    <c:forEach items="${departamentos}" var="departamento">
                                        <option value="${departamento.id}">${departamento.descricao}</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label>Status<br/>                      
                                <select name="funcionario.status.id">
                                    <option value="-1">Selecione o Status</option>
                                    <c:forEach items="${listastatus}" var="st">
                                        <option value="${st.id}">${st.descricao}</option>
                                    </c:forEach>
                                </select>
                            </label>                                  
                        </li>
                        <li  style="width: 200px; padding-bottom: 80px">
                            <label>Especialidade<br/>                      
                                <select name="funcionario.especialidade.id">
                                    <option value="-1">Selecione a Especialidade</option>
                                    <c:forEach items="${listaEspecialidades}" var="especialidade">
                                        <option value="${especialidade.id}">${especialidade.descricao}</option>
                                    </c:forEach>
                                </select>
                            </label>  
                        </li>

                        <li>
                            <button type="submit" style="color:#0029FF; width: 100px; font-family: arial; font-weight: bold">Buscar</button>
                        </li>
                        <div class="spacer"></div>
                    </ul>
                </fieldset>
            </form>


            <br/>

            <span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} página(s)</span>
        </section>
    </section>
</section>