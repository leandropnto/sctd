<style>
    .ui-autocomplete-loading {
        background: white url('images/ui-anim_basic_16x16.gif') right center no-repeat;
    }
</style>
<script>
    $(function() {
        function log( message ) {
            $( "<div>" ).text( message ).prependTo( "#log" );
            $( "#log" ).scrollTop( 0 );
        }
 
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
                                value: item.nome
                            }
                        }));
                    }
                });
            },
            minLength: 2,
            select: function( event, ui ) {
                log( ui.item ?
                    "Selected: " + ui.item.label :
                    "Nothing selected, input was " + this.value);
                $('<input>').attr({
                    type: 'hidden',
                    id: 'foo',
                    name: 'venda.itens[].produto.id',
                    value: ui.item.value
                }).appendTo('#form');
                
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
                <a href="<c:url value="/cadastros/"/>">Cadastros</a> > 
                <a href="<c:url value="/cadastros/produtos/"/>">Produtos</a> > 
                <a href="<c:url value="/cadastros/produtos/venda"/>">Venda</a> > 
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
                                    <select name="venda.funcionario.id" class="required">
                                        <option value>Informe o funcionário</option>
                                        <c:forEach items="${funcionarios}" var="funcionario">
                                            <option value="${funcionario.matricula}">${funcionario.nome}</option>
                                        </c:forEach>
                                    </select>
                                    <span>Selecione o funcionário</span>
                                </label>

                                <label style="width: 150px;">Data Venda<br/>
                                    <input type="text" name="venda.dataVenda" value="<fmt:formatDate value="${data}" type="both" pattern="dd/MM/yyyy" />" readonly/>
                                    <span>Informe a data</span>
                                </label>        
                                <label style="width: 150px;">Total da Venda<br/>
                                    <input type="text" name="venda.precoTotal" value="0" readonly style="text-align: right"/>                                    
                                </label>                                         
                            </li>      
                            <li><hr/></li>
                            <li>
                                <fieldset>
                                    <legend>Busca de produtos</legend>
                                    <ul>
                                        <li>
                                            <label style="width: 100px;">ID<br/>
                                                <input type="text" name="produto.id" value="${produto.id}" style="width: 45px"/>                                                
                                            </label> 
                                            <label style="width: 200px;">Nome<br/>
                                                <input type="text" name="produto.nome" value="" id="nomeproduto" />                                                
                                            </label> 
                                            <label style="width: 100px;">QTDE<br/>
                                                <input type="text" name="qtde" value="" />                                                
                                            </label> 
                                        </li>
                                    </ul>
                                </fieldset>
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