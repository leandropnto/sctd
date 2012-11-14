<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/departamentos/"/>">Departamentos</a> > 
            </span>
            <span class="children">Incluir Departamento</span>
        </section>
        <section class="text-box">
            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>


            <div id="stylized" class="myform">
                <form action="<c:url value="/cadastros/departamentos/salvar"/>" method="post" id="form" name="form">
                    <br/>
                    <fieldset id="fdFuncionarios">
                        <ul>
                            <li>
                                <label>Descrição<br/>
                                    <input type="text" name="departamento.descricao" value="${departamento.descricacao}" style="width: 200px; margin-bottom: 30px"/>                      
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
        </section>
    </section>
</section>           