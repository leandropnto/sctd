<style>
    .ui-autocomplete-loading {
        background: white url('<c:url value="/images/ui-anim_basic_16x16.gif"/>') right center no-repeat;
    }

    p.clicavel { color:red; margin:5px; cursor:pointer; }


</style>

<script>
    $(function() {
        
        
        
        $("#qtdItem").keypress(function(event) {
            var tecla = event.which;
            if ( event.which == 13 ) {
                event.preventDefault();
                confirmaVenda();   
            } else if ((tecla > 47 && tecla < 58)) // numeros de 0 a 9  
                return true;  
            else {  
                if (tecla != 8) // backspace  
                //event.keyCode = 0;  
                    return false;  
                else  
                    return true;  
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
                });
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
                                
                            </li>                             
                            
                            <li>
                                <label>
                                    <input type="hidden" name="ordem.produto.id" value="${ordem.produto.id}" id="produtoid"/>
                                </label>
                                <span>Selecione um produto.</span>
                            </li>
                            <li id="liproduto">                                
                                <label>
                                    Produto<br/>
                                    <input type="text" name="nomeproduto" value="" id="nomep"/>
                                </label>
                                <label>Quantidade<br/>
                                    <input type="text" name="ordem.quantidade" value="${ordem.quantidade}" style="width: 100px;  "
                                           class="required numeric" id="qtdItem"/> 
                                    <span>Informe a quantidade</span>
                                </label>
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