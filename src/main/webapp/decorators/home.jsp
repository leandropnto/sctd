<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if IE 9]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>    
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
            <title><decorator:title default="SCTD -  Sistema de Confecção Tavares Dutra"/></title>
            <meta name="description" content="Festas e Eventos">
            <link href="<c:url value="/css/all.css" />" media="screen, projection" rel="stylesheet" type="text/css" />
            <link href="<c:url value="/css/formularios.css" />" media="screen, projection" rel="stylesheet" type="text/css" />
            <script src="<c:url value="/js/modernizr-2.6.1.min.js"/>"></script>
            <decorator:head/>
        </head>
        <body>

            <header id="header" class="row-fluid bg-blue">
                <div class="row main-header">
                    <h1 class="logo">
                        <a href="<c:url value="/"/>">
                            <img src="<c:url value="/img/logo.gif" />" alt="SCTD -  Sistema de Confecção Tavares Dutra">
                        </a>
                    </h1>
                    <nav class="ui-list">
                        <ul class="list-h top-menu">
                            <li class="ui-item first"><a href="">Usuário</a></li>
                            <li class="ui-item bracket">|</li>
                            <li class="ui-item"><a href="">Configurações</a></li>
                            <li class="ui-item bracket">|</li>
                            <li class="ui-item last"><a href="">Perguntas Frequentes</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="row-fluid bg-silver">
                    <div class="row">
                        <nav>
                            <ul class="list-h ui-list main-menu">
                                <li class="ui-item first is-active"><a href="<c:url value="/"/>">Página Inicial</a></li>
                                <li class="ui-item"><a href="<c:url value="/cadastros/" />">Cadastros</a></li>
                                <li class="ui-item"><a href="<c:url value="/pedidos/"/>">Pedidos</a></li>
                                <li class="ui-item"><a href="<c:url value="/financeiro/"/>">Financeiro</a></li>
                                <li class="ui-item last"><a href="<c:url value="/contato/"/>">Contato</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </header>
            
            <section id="content" class="row">
                <decorator:body/>
            </section>
            <footer id="footer" class="row-fluid">
                <div class="row">
                    <aside class="pg-inicial">
                        <ul>
                            <li><a href="<c:url value="/"/>">Página Inicial</a></li>
                        </ul>
                    </aside>
                    <aside class="footer-links">
                        <nav>
                            <h4><a href="" class="tit2">Cadastros</a></h4>
                            <ul>
                                <li class="ui-item"><a href="">Funcionários</a></li>
                                <li class="ui-item"><a href="">Departamentos</a></li>
                                <li class="ui-item"><a href="">Cargos</li>
                            </ul>
                        </nav>
                        <nav>
                            <h4><a href="" class="tit2">Pedidos</a></h4>
                            <ul>
                                <li class="ui-item"><a href="">Realizados</a></li>
                                <li class="ui-item"><a href="">Pendentes</a></li>
                                <li class="ui-item"><a href="">Entregues</a></li>
                                <li class="ui-item"><a href="">Cancelados</a></li>
                            </ul>
                        </nav>
                        <nav>
                            <h4><a href="" class="tit2">Financeiro</a></h4>
                            <ul>
                                <li class="ui-item"><a href="">Faturas</a></li>
                                <li class="ui-item"><a href="">Cobranças</a></li>
                                
                            </ul>
                        </nav>
                        <nav class="last">
                            <ul>
                                <li><h4><a href="" class="tit2">Usuários</a></h4></li>
                            </ul>
                        </nav>
                    </aside>
                        <address class="footer-address">
                            <p>CREMERJ - Praia de Botafogo (228), loja 119b  - Botafogo</p>
                            <p>Rio de Janeiro/RJ - CEP: 22250-145 | Tel: 3184-7050</p>
                        </address>  
                </div>
            </footer> 

            <script src="<c:url value="/js/jquery-1.8.1.min.js"/>"></script>
            <script src="<c:url value="/js/plugins.js"/>"></script>
            <script src="<c:url value="/js/main.js"/>"></script>
        </body>
    </html>
