<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/departamentos/"/>">Departamentos</a> > 
            </span>
            <span class="children">Pesquisa de Departamentos</span>
        </section>
        <section class="text-box">

            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>
            <div id="stylized" class="myform">    
                <form action="<c:url value="/departamentos/atualizar"/>" method="post">
                    
                    <input type="hidden" name="departamento.id" value="${departamento.id}" />
                    <fieldset id="fdFuncionarios">
                        <ul>
                            <li>                    
                                <label>Descrição<br/>
                                    <input type="text" name="departamento.descricao" value="${departamento.descricao}" style="width: 100px" 
                                           />                      
                                </label>                             

                            </li>  

                            <li>
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


