<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Cache-Control" content="no-cache" />
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT"/>
        <title><decorator:title default="SCTD - Sitema de Confecção Tavares Dutra" /></title>

        <script src="<c:url value="/javascript/jquery-1.6.2.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/javascript/jquery-ui-1.8.16.custom.min.js" />" type="text/javascript"></script>
<!--        <script src="<c:url value="/javascript/jquery.tpl_layout1.1.6.min.js" />" type="text/javascript"></script>-->
        <script type="text/javascript" src="<c:url value="/javascript/tiny_mce/tiny_mce.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/javascript/jquery-ui-1.8.16.custom.css"/>" media="all" />
        <!--        <script>
                    $(document).ready(function(){
                        $.setTemplateLayout(); // função do plug-in que faz a mágica.
                    })
                </script>-->

        <style type="text/css">
            * {
                margin:0;
                padding:0;
            }
            body {
                text-align:center;                     
                font:75%/140% Arial, Helvetica, sans-serif;
                background: #fff url(bg.gif) center repeat-y; 
            }
            #tudo{
                width:1024px;
                margin:0 auto;
                text-align:left;
                border:1px solid #133783;
                /*                voice-family: "\"}\""; */
                voice-family:inherit;
/*                width:1024px;*/
            }
            body>#tudo {
                width:1024px;
            }
            #topo {
                height:90px;
                background:#3B5998;
            }
            
            #topo h1{
                color: #FFF;
                padding-top: 10px;
                padding-left:20px;
                font-family: 'Arial';
                font-size: 24pt;
            }
            #principal {
                float:left;
                width: 712px;
                background:#fff;
                margin-left:156px;
                display:inline;	
                text-align:justify;
            }
            #nav-sec {
                float:left;
                width: 150px;
                background:#FFF;                    
                margin-left:6px;
            }
            #navegacao {
                float:left;
                width: 150px;
                background:#FFF;
                margin-left:-1024px;
                margin-right:6px;
                display:inline;                                              
            }
            #rodape {
                height:50px;
                background:#ddd;
                clear:both;
                background: #3B5998;
                color: #FFF;
                padding: 10px;
            } 
            h1{
                font-size: 130%;
                color:red;
                margin-left:5px;
            }
            h2{
                font-size: 120%;
                color:red;
                margin-left:5px;
            }
            h3{
                font-size: 100%;
                color:black;
                margin-left:5px;
            }
            p { 
                color:#333; 
                margin:10px 0;
            }
            ul,dd,dt {margin-left:5px;}
            li { list-style:none;}
        </style>
    </head>
    <body> 
        <div id="tudo">
            <div id="topo"> 
                <h1>SCTD - Sistema de Confecção Tavares e Dutra </h1> 
            </div> 
            <div id="principal"> 
                <decorator:body />
            </div> 
            <div id="nav-sec">                  
                <fieldset style="display: block;border: 1px solid #CCC;margin-top: 5px;  background-color: #FFF; height: 500px">
                    <legend style="font-weight: bold; color: black;font-family: Arial, Helvetica, sans-serif;">Opções adicionais</legend>

                    <ul style="list-style: none; clear: both; padding-left: 5px; padding-bottom: 20px">
                        <c:forEach items="${opcoes}" var="op" varStatus="cont">
                            <li style="padding-bottom: 10px; <c:if test="${contador.index %2==0}">background-color: #FFF</c:if>" >
                                <a style="text-decoration:none;" href="<c:url value="${op.link}"/>">${op.descricao}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </fieldset>
            </div> 
            <div id="navegacao">                 
                <fieldset style="display: block;border: 1px solid #CCC;margin-top: 5px; background-color: #FFF; height: 500px">
                    <legend style="font-weight: bold; color: black;font-family: Arial, Helvetica, sans-serif;">Opções</legend>
                    <ul style="list-style: none; clear: both; padding-left: 5px; padding-bottom: 20px">
                        <li>
                            <a style="text-decoration:none;" href="<c:url value="/"/>">Home</a>
                        </li>
                        <li>
                            <a style="text-decoration:none;" href="<c:url value="/funcionarios/"/>">Funcionários</a>
                        </li>
                        <li>
                            <a style="text-decoration:none;" href="<c:url value="/pedidos/"/>">Pedidos</a>
                        </li>
                        <li>
                            <a style="text-decoration:none;" href="<c:url value="/vendas/"/>">Vendas</a>
                        </li>
                        <li>
                            <a style="text-decoration:none;" href="<c:url value="/clientes/"/>">Clientes</a>
                        </li>
                        <li>
                            <a style="text-decoration:none;" href="<c:url value="/transportadoras/"/>">Transportadoras</a>
                        </li>
                        <li>
                            <a style="text-decoration:none;" href="<c:url value="/administracao/"/>">Administração</a>
                        </li>
                    </ul>
                </fieldset>
            </div> 
            <div id="rodape">
                <span style='font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;'>
                    Sistema de Confecção Tavares e Dutra. <br/>
                    Desenvolvido por Leandro Pinto, Vagner Praia da Silva e Camilla Dutra
                </span>
                  
            </div> 
            <!-- fim #tudo --> 
        </div> 

    </body>
</html>
