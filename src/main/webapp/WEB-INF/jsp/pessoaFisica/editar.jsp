<script>
    $(function() {
        $( "#accordion" ).accordion({
           
        });
    });
</script>
<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/pessoafisica/"/>">Pessoa Física</a> > 
            </span>
            <span class="children">Edição de Pessoa Física</span>
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
                <form action="<c:url value="/cadastros/pessoafisica/atualizar"/>" method="post" id="form" name="form" class="validate">


                    <fieldset class="formato1">
                        <legend>Edição de Pessoa Física</legend>
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
                                    <input type="text" name="pessoa.cpf" value="${pessoa.cpf}" style="width: 200px; margin-bottom: 30px"
                                           class="required cpf"/> 
                                    <span>Informe o CPF</span>
                                </label>
                            </li>             

                            <li>
                                <input type="hidden" name="pessoa.id" value="${pessoa.id}" />
                                <input type="hidden" name="pessoa.status" value="${pessoa.status}" />
                                <button type="submit" class="button">Atualizar</button>
                            </li>

                        </ul>
                    </fieldset>
                </form>
                <div id="accordion" class="myform">
                    <h3>Endereços Registrados</h3>
                    <div>

                        <table id="mytable">
                            <caption>Endereço Cadastrado</caption>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Logradouro</th>                            
                                    <th>Número</th>
                                    <th>Complemento</th>
                                    <th>CEP</th>           
                                    <th>Cidade</th>           
                                    <th>Estado</th>           
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td style="text-align: right; width: 15px;">${pessoa.endereco.id}</a></td>
                                    <td style="text-align: left; width: 200px;">${pessoa.endereco.logradouro}</td>
                                    <td style="text-align: left; width: 80px;">${pessoa.endereco.numero}</td>
                                    <td style="text-align: left; width: 150px;">${pessoa.endereco.complemento}</td>
                                    <td style="text-align: left; width: 150px;">${pessoa.endereco.cep}</td>
                                    <td style="text-align: left; width: 150px;">${pessoa.endereco.cidade}</td>
                                    <td style="text-align: right; width: 40px;">${pessoa.endereco.estado}</td>
                                </tr>


                            </tbody>

                        </table>
                        <br/>                


                    </div>
                    <h3>Cadastrar endereços</h3>
                    <div>
                        <form action="<c:url value="/cadastros/pessoafisica/endereco/${pessoa.id}/salvar"/>" 
                              method="post" id="form2" name="form2" class="validate">
                            <fieldset class="formato1">
                                <legend>Inclusão de endereço</legend>
                                <ul>
                                    <li>
                                        <label style="width: 270px">Logradouro<br/>
                                            <input type="text" name="endereco.logradouro" value="${endereco.logradouro}" style="width: 250px;"
                                                   class="required "/> 
                                            <span>Informe o Logradouro</span>
                                        </label>
                                        <label>Número<br/>
                                            <input type="text" name="endereco.numero" value="${endereco.numero}" style="width: 50px;"
                                                   class="required numeric"/> 
                                            <span>Número</span>
                                        </label>
                                    </li>
                                    <li>
                                        <label style="width: 270px">Complemento<br/>
                                            <input type="text" name="endereco.complemento" value="${endereco.complemento}" style="width: 250px;"
                                                   /> 
                                            <span>Informe o Complemento</span>
                                        </label> 
                                        <label>CEP<br/>
                                            <input type="text" name="endereco.cep" value="${endereco.cep}" style="width: 50px;"
                                                   class="required numeric"  /> 
                                            <span>Informe o CEP</span>
                                        </label> 
                                        <label>Bairro<br/>
                                            <input type="text" name="endereco.bairro" value="${endereco.bairro}" style="width: 150px;"
                                                   class="required"  /> 
                                            <span>Informe o Bairro</span>
                                        </label> 
                                        <label>Cidade<br/>
                                            <input type="text" name="endereco.cidade" value="${endereco.cidade}" style="width: 150px;"
                                                   class="required"  /> 
                                            <span>Informe a Cidade</span>
                                        </label> 

                                    </li>
                                    <li>
                                        <label>Estado<br/>
                                            <select name="endereco.estado" class="required"> 
                                                <option value>Selecione o Estado</option>
                                                <option value="ac">Acre</option>
                                                <option value="al">Alagoas</option>
                                                <option value="ap">Amapá</option>
                                                <option value="am">Amazonas</option>
                                                <option value="ba">Bahia</option>
                                                <option value="ce">Ceará</option>
                                                <option value="df">Distrito Federal</option>
                                                <option value="es">Espirito Santo</option>
                                                <option value="go">Goiás</option>
                                                <option value="ma">Maranhão</option>
                                                <option value="ms">Mato Grosso do Sul</option>
                                                <option value="mt">Mato Grosso</option>
                                                <option value="mg">Minas Gerais</option>
                                                <option value="pa">Pará</option>
                                                <option value="pb">Paraíba</option>
                                                <option value="pr">Paraná</option>
                                                <option value="pe">Pernambuco</option>
                                                <option value="pi">Piauí</option>
                                                <option value="rj">Rio de Janeiro</option>
                                                <option value="rn">Rio Grande do Norte</option>
                                                <option value="rs">Rio Grande do Sul</option>
                                                <option value="ro">Rondônia</option>
                                                <option value="rr">Roraima</option>
                                                <option value="sc">Santa Catarina</option>
                                                <option value="sp">São Paulo</option>
                                                <option value="se">Sergipe</option>
                                                <option value="to">Tocantins</option>
                                            </select>
                                            <span>Selecione o estado</span>
                                        </label> 
                                        <label style="320px;">Referência<br/>
                                            <input type="text" name="endereco.referencia" value="${endereco.referencia}" style="width: 300px;"
                                                   class="required"  /> 
                                            <span>Informe a referência</span>
                                        </label> 
                                    </li>
                                    <li>


                                        <button type="submit" class="button">Incluir endereço</button>
                                    </li>
                                </ul>
                            </fieldset>
                        </form>
                    </div>
                </div>                
            </div>

        </section>
    </section>
</section>           