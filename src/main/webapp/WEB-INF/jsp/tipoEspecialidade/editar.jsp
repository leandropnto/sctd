<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/tipoespecialidade/"/>">Tipo de Especialidade</a> > 
            </span>
            <span class="children">Inclusão de Tipos de Especialidade</span>
        </section>
        <section class="text-box">

            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>

            <div id="stylized" class="myform">
                <form action="<c:url value="/cadastros/tipoespecialidade/atualizar"/>" method="post" id="form" name="form">
                    

                    <fieldset id="fdFuncionarios">
                        <ul>
                            <li>
                                <label>Nome<br/>
                                    <input type="text" name="especialidade.nome" value="${especialidade.nome}" style="width: 200px; margin-bottom: 30px"/>                      
                                </label>

                            </li>                
                            <li>
                                <label>Descrição<br/>
                                    <input type="text" name="especialidade.descricao" value="${especialidade.descricao}" style="width: 200px; margin-bottom: 30px"/>                      
                                </label>

                            </li>                

                            <li>
                                <input type="hidden" name="especialidade.id" value="${especialidade.id}" />
                                <button type="submit" style="color:#0029FF; width: 100px; font-family: arial; font-weight: bold">Atualizar</button>
                            </li>
                            <div class="spacer"></div>
                        </ul>
                    </fieldset>
                </form>
            </div>

        </section>
    </section>
</section>           