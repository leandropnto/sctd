<script>
    $(function() {
        
        //Método para a forma de pagamento
        $('#tipo').change(function(){
            var opcao = $("option:selected", this).val();
            console.log(opcao);
            if (opcao == 1){                                
                $('#liproduto').css("display","");
                $('#lipedido').css("display", "none");
                               
            } else if (opcao == 2){
                $('#liproduto').css("display", "none");
                $('#lipedido').css("display", "");
            } else {
                $('#liproduto').css("display", "none");
                $('#lipedido').css("display", "none");
            }
        });
        
        //pedidoid
        //Busca produto
        $( "#idpedido" ).autocomplete({
            source: function( request, response ) {
                $.ajax({
                    url: "<c:url value="/pedidos/ordem/buscarpedido"/>",
                    dataType: "json",
                    data: {
                        term: request.term
                    },
                    success: function( data ) {
                        console.log(data);                        
                        response( $.map( data, function( item ) {
                            return {
                                label: item.id + "-" + item.produto.nome,
                                value: item.id,
                                produto: item.produto.id,
                                preco: item.produto.valor,
                                qtde: item.quantidade,
                                id: "item" +item.id
                                
                            }
                        }));
                    }
                });
            },
            minLength: 1,
            select: function( event, ui ) {
                
                
                $('#nomeproduto').text("Item Selecionado: " + ui.item.label + ". Preço: R$ " + ui.item.preco);
                $('#produtoid').val(ui.item.produto);
                $('#qtdeItens').val(ui.item.qtde);
                
                
                
                
            },
            open: function() {
                $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
        });
    });
    
</script>
<section id="content" class="row">
    <section class="featured">
        <section class="breadcrumb">
            <span class="parents">
                <a href="<c:url value="/"/>">Página Inicial</a> > 
                <a href="<c:url value="/pedidos/"/>">Pedidos</a> > 
                <a href="<c:url value="/pedidos/ordem/"/>">Ordem Serviço</a> > 
            </span>
            <span class="children">Registro de Ordem de Serviço - VENDA</span>
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
                <form action="<c:url value="/pedidos/ordem/salvarordemvenda"/>" method="post" id="form" name="form" class="validate">


                    <fieldset class="formato1">
                        <legend>Ordem de Serviço - VENDA</legend>
                        <ul>
                            <li>
                                <label style="width: 150px;">Data de Inicio<br/>
                                    <input type="text" name="ordem.dataInicio" 
                                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${ordem.dataInicio}"/>" style="width: 85px;"
                                           class="required data"/> 
                                    <span>dd/mm/aaaa</span>
                                </label>
                                <label style="width: 150px;">Data de Entrega<br/>
                                    <input type="text" name="ordem.dataEntrega" 
                                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${ordem.dataEntrega}"/>" style="width: 85px;"
                                           class="required data"/> 
                                    <span>dd/mm/aaaa</span>
                                </label>
                                <label>Quantidade<br/>
                                    <input type="text" name="ordem.quantidade" value="${ordem.quantidade}" style="width: 100px;  "
                                           class="required numeric" id="qtdeItens" readonly/> 
                                    <span>Informe a quantidade</span>
                                </label>
                            </li>                             

                            <li>
                                <label>
                                    <input type="hidden" name="ordem.produto.id" value="${ordem.produto.id}" id="produtoid"/>
                                </label>
                                <span>Selecione um produto.</span>
                            </li>
                            <li id="liproduto">                                
                                <label>
                                    Item do pedido (Informe o número do PEDIDO e selecione um item)<br/>                                    
                                    <input type="text" name="ordem.item.id" value="" id="idpedido" class="required numeric"/>
                                    <span>Informe o número do PEDIDO e selecione um item</span>
                                </label>
                            </li>   
                            <li>
                                <span id="nomeproduto"></span>                                
                            </li>

                            <li><label> Funcionários<br/>
                                    <select name="ordem.funcionarios[].matricula" class="required" multiple size="5">
                                        <c:forEach items="${funcionarios}" var="funcionario">
                                            <option value="${funcionario.matricula}">${funcionario.nome}</option>
                                        </c:forEach>
                                    </select>
                                    <span>Selecione um funcionário</span>
                                </label>
                            </li>

                            <li>
                                <label>Status<br/>
                                    <input type="text" name="ordem.status" value="${ordem.status}" readonly/>
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