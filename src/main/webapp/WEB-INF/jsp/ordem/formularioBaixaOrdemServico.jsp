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
            <span class="children">Baixa de Ordem de Serviço</span>
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
                <form action="<c:url value="/pedidos/ordem/filtrarparabaixa"/>" method="post" id="form" name="form" class="validate">


                    <fieldset class="formato1">
                        <legend>Buscar ordem Serviço</legend>
                        <ul>
                            <li>
                                <label>Número da Ordem de Serviço<br/>
                                    <input type="text" name="ordem.id" value="${ordem.id}" style="width: 100px;  "
                                           class="required numeric" id="qtdeItens" /> 
                                    <span>Número da Ordem</span>
                                </label>  
                            </li>
                            <li>
                                <button type="submit" class="button">Buscar</button>
                            </li>


                        </ul>
                    </fieldset>
                </form>
                <c:if test="${ordem.id != null}">
                    <form action="<c:url value="/pedidos/ordem/registrarbaixa"/>" method="post" id="form" name="form" class="validate">
                        <fieldset class="formato1">
                            <legend>Informações da Ordem de Serviço</legend>
                            <ul>
                                <input type="hidden" value="${ordem.id}" name="ordem.id"/>
                                <li style="padding-top: 10px;">Nº da Ordem: ${ordem.id}</li>
                                <li>Data: <fmt:formatDate pattern="dd/MM/yyyy" value="${ordem.dataInicio}"/></li>
                                <li>Quantidade: ${ordem.quantidade}</li>                                
                                <li>Produto: ${ordem.produto.nome}</li> 
                                <c:choose>
                                    <c:when test="${ordem.item != null}">
                                        <li>Registrada para o pedido: ${ordem.item.pedido.id}</li>
                                    </c:when>                                    
                                </c:choose>
                                <li>Status: ${ordem.status.toString()}</li>                                
                                <c:if test="${ordem.status.ordinal() == 0}">
                                    <button type="submit" class="button">Baixar</button>
                                </c:if>
                            </ul>
                        </fieldset>
                        
                    </form>
                </c:if>
            </div>

        </section>
    </section>
</section>           