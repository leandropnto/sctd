<style>
    .ui-autocomplete-loading {
        background: white url('<c:url value="/images/ui-anim_basic_16x16.gif"/>') right center no-repeat;
    }

    p.clicavel { color:red; margin:5px; cursor:pointer; }


</style>
<script>
    $(function() {
        var num = 0;
        var precoTotal = 0;
        
        //Evento de clique que dispara o dialog
        $('#cadNovoEndereco').click(function(event){            
            event.preventDefault();            
            $( "#divEndereco" ).dialog({
                height: 400,
                width: 800,
                modal: true
            });
        });
      
        //Busca produto
        $( "#nomeproduto" ).autocomplete({
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
                                qtde: item.quantidade,
                                id: "item" +item.id
                                
                            }
                        }));
                    }
                });''
            },
            minLength: 2,
            select: function( event, ui ) {
                
                if (ui.item.qtde <=0){
                    alert("Produto sem itens no estoque. Selecione outro produto!");
                    $('#nomeproduto').val("");
                    $('#nomeproduto').focus();
                    preventDefault();
                } else {
                    $('#nomeproduto').val(ui.item.label);
                    $('#prodid').val(ui.item.chave);
                    $('#preco').val(ui.item.preco);                
                    $('#qtde').val("1");
                    $('#qtde').focus();
                }
                
                
            },
            open: function() {
                $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );                
            }
        });
        
        //Busca cliente
        $( "#clienteBusca" ).autocomplete({
            source: function( request, response ) {
                $.ajax({
                    url: "<c:url value="/pedidos/venda/buscacliente"/>",
                    dataType: "json",
                    data: {
                        term: request.term
                    },
                    success: function( data ) {
                        console.log(data);                        
                        response( $.map( data, function( item ) {
                            return {
                                label: item.nome,
                                value: item.cpf,
                                chave: item.id, 
                                idEndereco: (item.endereco && item.endereco.id ? item.endereco.id : ""),
                                endereco: (item.endereco && item.endereco.logradouro ? item.endereco.logradouro : "") + 
                                    " " + (item.endereco && item.endereco.numero ? item.endereco.numero : "") + 
                                    " " + (item.endereco && item.endereco.complemento ? item.endereco.complemento : "") +
                                    " " + (item.endereco && item.endereco.bairro ? item.endereco.bairro : "") +
                                    " " + (item.endereco && item.endereco.cidade ? item.endereco.cidade : "") +
                                    " " + (item.endereco && item.endereco.estado ? item.endereco.estado : ""),
                                cpf: ((item.endereco && item.cpf) ? item.cpf : item.cnpj),
                                id: "cliente" +item.id,
                                status: item.status                                
                                
                            }
                        }));
                    }
                });
            },
            minLength: 2,
            select: function( event, ui ) {
                
                console.log(ui.item.cpf);
                $('#clienteNome').val(ui.item.label);
                $('#clienteID').attr('value', ui.item.chave)
                $('#span_endereco').text(ui.item.endereco)
                $('#clienteStatus').html("<br/>" + ui.item.status)
                
                
                
                
                
            },
            open: function() {
                $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
        });
        
        
        
        
        $("#qtde").keypress(function(event) {
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
        
        $("#numparcelas").keypress(function(event) {
            var tecla = event.which;
            if ((tecla > 47 && tecla < 58)) // numeros de 0 a 9  
                return true;  
            else {  
                if (tecla != 8) // backspace  
                //event.keyCode = 0;  
                    return false;  
                else  
                    return true;  
            }  
            
        });
        
        $( "#nomeproduto" ).keypress(function(event){
            if (event.which == 13){
                event.preventDefault();                
            }
        });
        
        
        
        $('#bntConfirmarProduto').click(function(){
            confirmaVenda();
        });
        
        
        
        //Acao de cadastro de endereco
        $('#btnCadEndereco').click(function(){
            var endereco = 
                $('#edLogradouro').val() + " " + 
                $('#edNumero').val() + " " + 
                $('#edComplemento').val() + " " + 
                $('#edBairro').val() + " " + 
                $('#edCidade').val() + " " + 
                $('#edEstado').val() + " " + 
                " CEP: " + $('#edCep').val()  + " " +
                $('#edReferencia').val() + " " ;
            $('#span_endereco').text(endereco);
            $('#divEndereco').dialog('close');
            
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.logradouro',
                value: $('#edLogradouro').val(),
                id: 'altEnderecoLogradouro'
            }).appendTo('#form');
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.numero',
                value: $('#edNumero').val(),
                id: 'altEnderecoNumero'
            }).appendTo('#form');
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.complemento',
                value: $('#edComplemento').val(),
                id: 'altEnderecoComplemento'
            }).appendTo('#form');
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.bairro',
                value: $('#edBairro').val(),
                id: 'altEnderecoBairro'
            }).appendTo('#form');
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.cidade',
                value: $('#edCidade').val(),
                id: 'altEnderecoCidade'
            }).appendTo('#form');
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.estado',
                value: $('#edEstado').val(),
                id: 'altEnderecoEstado'
            }).appendTo('#form');
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.referencia',
                value: $('#edReferencia').val(),
                id: 'altEnderecoReferencia'
            }).appendTo('#form');
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.cliente.endereco.cep',
                value: $('#edCep').val(),
                id: 'altEnderecoCep'
            }).appendTo('#form');
        });
        
        
        //Método para a forma de pagamento
        $('#selFormaPagamento').change(function(){
            var opcao = $("option:selected", this).val();
            if (opcao == 0 || opcao == 3){                
                $('#numparcelas').val("1");
                $('#numparcelas').attr("readonly", true);
            } else {
                $('#numparcelas').removeAttr("readonly");
            }
        });
            
        function confirmaVenda(){
            if ($('#prodid').val() == ""){
                alert("Produto não encontrado!");
                $('#nomeproduto').val("");
                $('#preco').val();
                $('#nomeproduto').focus();
                return;
            }
            
            if($('#qtde').val() ==""){
                alert("Informe a quantidade de itens!");
                $('#qtde').val("1");
                return;
            }
            var numid = num;
            num++;
                
            var preco = $('#qtde').val()* $('#preco').val();
            precoTotal += preco;
                
            //monta e insere a linha na tabela
            $("#bodydados").last().append("<tr><td>" + num + "</td><td>" 
                + $('#nomeproduto').val() + "</td><td>" + 
                $('#qtde').val() + "</td><td style='text-align: right'>" + 
                preco.toFixed(2) +  "</td></tr>");
                
            //preenche os campos hidden
            $('<input>').attr({
                type: 'hidden',                    
                name: 'venda.itens['+numid+'].produto.id',
                value: $('#prodid').val()
            }).appendTo('#form');
                
            $('<input>').attr({
                type: 'hidden',   
                id: "item_" + num,
                name: 'venda.itens['+ numid + '].quantidade',
                value: $('#qtde').val()
            }).appendTo('#form');
                
            //limpa e remota o focu
            $('#prodid').val("");
            $('#nomeproduto').val("");
            $('#qtde').val("1");
            $('#nomeproduto').focus();
            var valor = precoTotal.toFixed(2);
            $('#tdtotal').text("Total: " + valor);
                
             
        } 
    });
    
</script>

<section class="featured">
    <section class="breadcrumb">
        <span class="parents">
            <a href="<c:url value="/"/>">Página Inicial</a> > 
            <a href="<c:url value="/pedidos/"/>">Pedidos</a> > 
            <a href="<c:url value="/pedidos/pedido"/>">Registro de Venda</a> >                 
        </span>
        <span class="children">Registro de Venda</span>
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
            <form action="<c:url value="/pedidos/venda/registrar"/>" method="post" id="form" name="form" class="validate">                   
                <fieldset class="formato1">
                    <legend>Registro de Venda</legend>
                    <ul>
                        <li>
                            <label style="width: 150px;">Funcionario<br/>
                                <select name="venda.funcionario.matricula" class="required">
                                    <option value>Informe o funcionário</option>
                                    <c:forEach items="${funcionarios}" var="funcionario">
                                        <option value="${funcionario.matricula}">${funcionario.nome}</option>
                                    </c:forEach>
                                </select>
                                <span>Selecione o funcionário</span>
                            </label>

                            <label style="width: 150px;">Data Venda<br/>
                                <input type="text" name="venda.dataVenda" value='<fmt:formatDate value="${data}" type="both" pattern="dd/MM/yyyy" />' readonly/>
                                <span>Informe a data</span>
                            </label>        
                            <label style="width: 150px;">Total da Venda<br/>
                                <input type="text" name="venda.precoTotal" value="0" readonly style="text-align: right"/>                                   
                            </label> 
                        </li>
                        <li>
                            <label>Forma de Pagamento<br/>
                                <select name="venda.formaPagamento" class="required" id="selFormaPagamento">
                                    <option value>Selecione a forma de Pagamento</option>
                                    <c:forEach items="${formasPagamento}" var="forma">
                                        <option value="${forma.ordinal()}">${forma.toString()}</option>
                                    </c:forEach>
                                </select>
                                <span>Selecione a forma de Pagamento</span>
                            </label>
                            <label>Parcelas<br/>
                                <input type="text" name="numparcelas" value="1" id="numparcelas" class="numeric"/>
                                <span>Quantidade de Parcelas</span>
                            </label>
                        </li>                               
                        <li><hr/></li>

                        <li>
                            <fieldset>
                                <legend>Opções do Cliente</legend>
                                <ul>
                                    <li>
                                        <label style="width: 120px;">CPF/CNPJ<br/>
                                            <input type="text" name="cliente" value="" style="width: 100px" id="clienteBusca" class="cpf"/>                                                
                                        </label> 
                                        <label style="width: 250px;">Nome<br/>
                                            <input type="text" name="clienteNome" value="" style="width: 240px" id="clienteNome"/>                                                   

                                        </label>
                                        <label id="clienteStatus" style="padding-top: 15px; padding-left: 10px; color:red;"> 

                                        </label>
                                    </li>
                                    <li><span id="span_endereco"></span></li>
                                    <li>
                                        <p id="cadNovoEndereco" class="clicavel">Cadastrar novo Endereço</p>                                            
                                    </li>

                                    <li>
                                        <input type="hidden" name="venda.cliente.id" value="" id="clienteID"/> 
                                    </li>

                                </ul>
                            </fieldset>
                        </li>
                        <li>
                            <fieldset>
                                <legend>Busca de produtos</legend>
                                <ul>
                                    <li>
                                        <label style="width: 100px; display: none;">ID<br/>
                                            <input type="text" name="produto.id" value="${produto.id}" style="width: 45px" id="prodid"/>                                                
                                        </label> 
                                        <label style="width: 200px;">Nome<br/>
                                            <input type="text" name="produto.nome" value="" id="nomeproduto" style="width: 190px;"/>                                                
                                        </label> 
                                        <label style="width: 100px;">Preço<br/>
                                            <input type="text" name="preco" value="" id="preco" readonly/>                                                
                                        </label> 
                                        <label style="width: 100px;">Quantidade<br/>
                                            <input type="text" name="qtde" value="" id="qtde" class="numeric"/>                                                
                                        </label> 
                                        <label><br/>
                                            <img id="bntConfirmarProduto" src="<c:url value="/images/confirm.png"/>" style="padding-top: 10px;" title="Confirmar Produto"/>
                                        </label>
                                    </li>
                                </ul>
                            </fieldset>
                        </li>
                        <li><hr/></li>
                        <li>
                            <table id="tabitens" style="width: 95%">
                                <thead>
                                    <tr>
                                        <th style="width: 10%;">Num.</th>
                                        <th style="width: 65%;">Produto</th>
                                        <th style="width: 10%;">Qtde</th>
                                        <th style="width: 15%;">Sub. Total</th>
                                    </tr>
                                </thead>
                                <tbody id="bodydados">

                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="4" style="text-align: right" id="tdtotal">Total</td>
                                    </tr>
                                </tfoot>
                            </table>
                        </li>

                        <li>
                            <button type="submit" class="button">Finalizar</button>
                        </li>

                    </ul>
                </fieldset>
            </form>
        </div>

    </section>
</section>                 

<!-- div "escondida" para o formulario de cadastro de endereco -->
<div style="display:none" id="divEndereco" title="Cadastro de Endereço" class="validate">

    <fieldset class="formato1">
        <legend>Inclusão de endereço</legend>
        <ul>
            <li>
                <label style="width: 270px">Logradouro<br/>                                                                                        
                    <input type="text" name="venda.cliente.endereco.logradouro" value="${venda.cliente.endereco.logradouro}" style="width: 250px;"
                           class="required " id="edLogradouro"/> 
                    <span>Informe o Logradouro</span>
                </label>
                <label>Número<br/>
                    <input type="text" name="venda.cliente.endereco.numero" value="${venda.cliente.endereco.numero}" style="width: 50px;"
                           class="required numeric" id="edNumero"/> 
                    <span>Número</span>
                </label>
            </li>
            <li>
                <label style="width: 270px">Complemento<br/>
                    <input type="text" name="venda.cliente.endereco.complemento" value="${venda.cliente.endereco.complemento}" style="width: 250px;"
                           id="edComplemento"/> 
                    <span>Informe o Complemento</span>
                </label> 
                <label>CEP<br/>
                    <input type="text" name="venda.cliente.endereco.cep" value="${venda.cliente.endereco.cep}" style="width: 50px;"
                           class="required numeric"  id="edCep"/> 
                    <span>Informe o CEP</span>
                </label> 
                <label>Bairro<br/>
                    <input type="text" name="venda.cliente.endereco.bairro" value="${venda.cliente.endereco.bairro}" style="width: 150px;"
                           class="required"  id="edBairro"/> 
                    <span>Informe o Bairro</span>
                </label> 
                <label>Cidade<br/>
                    <input type="text" name="venda.cliente.endereco.cidade" value="${venda.cliente.endereco.cidade}" style="width: 150px;"
                           class="required"  id="edCidade"/> 
                    <span>Informe a Cidade</span>
                </label> 

            </li>
            <li>
                <label>Estado<br/>
                    <select name="venda.cliente.endereco.estado" class="required" id="edEstado"> 
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
                    <input type="text" name="venda.cliente.endereco.referencia" value="${venda.cliente.endereco.referencia}" style="width: 300px;"
                           class="required"  id="edReferencia"/> 
                    <span>Informe a referência</span>
                </label> 
            </li>
            <li>


                <button type="button" class="button" id="btnCadEndereco">Incluir endereço</button>
            </li>
        </ul>
    </fieldset>

</div>  
<!-- div "escondida" para o formulario de cadastro de endereco -->                                            