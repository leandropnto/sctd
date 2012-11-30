
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
        $( "#nomep" ).autocomplete({
            source: function( request, response ) {
                $.ajax({
                    url: "<c:url value="/pedidos/venda/buscaproduto"/>",
                    dataType: "json",
                    data: {
                        term: request.term
                    },
                    success: function( data ) {
                        console.log(data);                        
                        response( $.map( data, function( item ) {
                            return {
                                label: item.nome,
                                value: item.nome,
                                chave: item.id,
                                preco: item.valor,
                                id: "item" +item.id
                                
                            }
                        }));
                    }
                });''
            },
            minLength: 2,
            select: function( event, ui ) {
                
                
                $('#nomeproduto').val(ui.item.label);
                $('#produtoid').val(ui.item.chave);
                $('#preco').val(ui.item.preco);
                $('#qtde').focus();
                
                
                
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
            <span class="children">Registro de Ordem de Serviço</span>
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
                <form action="<c:url value="/pedidos/ordem/salvar"/>" method="post" id="form" name="form" class="validate">


                    <fieldset class="formato1">
                        <legend>Registro de Ordem de Serviço</legend>
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
                                           class="required numeric"/> 
                                    <span>Informe a quantidade</span>
                                </label>
                            </li>
                            <li>
                                <label>Tipo de Serviço<br/>
                                    <select name="tipo" id="tipo">
                                        <option value="0">Selecione</option>
                                        <option value="1">Venda</option>
                                        <option value="2">Pedido</option>
                                    </select>
                                </label>
                            </li>  
                            <li id="liproduto" style="display: none;">
                                <input type="hidden" name="ordem.produto.id" value="${ordem.produto.id}" id="produtoid"/>
                                <label>
                                    Produto<br/>
                                    <input type="text" name="nomeproduto" value="" id="nomep"/>
                                </label>
                            </li>
                            <li id="lipedido" style="display: none">
                                <label>
                                    Pedido<br/>
                                    <input type="text" name="pedido.id" value="${pedido.id}" id="pedidoid"/>
                                </label>
                                <label>Selecione o item<br/>
                                    <select name="ordem.item.id" id="iditem">
                                        <option value>Selecione</option>

                                    </select>
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