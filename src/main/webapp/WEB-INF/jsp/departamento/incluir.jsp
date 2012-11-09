<style type="text/css">
    .myform{
        margin:0 auto;
        width: 680px;
        padding:14px;
        height: 100%;

    }

    fieldset#fdFuncionarios {
        display: block;
        margin: 0;
        margin-bottom: 10px;
        border: 1px groove #B7DDF2;
        border-collapse: collapse;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 14px;
        background-color: #EBF4FB;
        padding-bottom: 10px;

    }
    fieldset#fdFuncionarios ul, fieldset#fdFuncionarios li{
        border:0; margin:0; padding:0; list-style:none;
        margin-bottom: 2px;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        padding-left: 5px;

    }

    fieldset#fdFuncionarios li{
        clear:both;
        list-style:none;
        padding-bottom:10px;   
    }
    fieldset#fdFuncionarios label{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 100px;
    }

    fieldset#fdFuncionarios label.grd{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 470px;
    }   
    fieldset#fdFuncionarios label.med{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 330px;
    }     

    fieldset#fdFuncionarios label.peq{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 80px;
    }    

    .info, .success, .warning, .error, .validation {

        border: 1px solid;

        margin: 10px 0px;

        padding:15px 10px 15px 50px;

        background-repeat: no-repeat;

        background-position: 10px center;

    }

    .info {

        color: #00529B;

        background-color: #BDE5F8;

        background-image: url('<c:url value="/images/info.png"/>');

    }

    .success {

        color: #4F8A10;

        background-color: #DFF2BF;

        background-image:url('<c:url value="/images/success.png" />');

    }

    .warning {

        color: #9F6000;

        background-color: #FEEFB3;

        background-image: url('<c:url value="/images/warning.png"/>');

    }

    .error {

        color: #D8000C;

        background-color: #FFBABA;

        background-image: url('<c:url value="/images/error.png"/>');

    }    
</style>
<c:forEach var="error" items="${errors}">
    ${error.category} - ${error.message}<br />
</c:forEach>

<div id="stylized" class="myform">
    <form action="<c:url value="/departamentos/salvar"/>" method="post" id="form" name="form">
        <h1>SCTD - Departamentos</h1>
        <p>Formulário para cadastro de departamentos</p>

        <fieldset id="fdFuncionarios">
            <ul>
                <li>
                    <label>Descrição<br/>
                        <input type="text" name="departamento.descricao" value="${departamento.descricacao}" style="width: 200px; margin-bottom: 30px"/>                      
                    </label>

                </li>                
                         
                <li>
                    <button type="submit" style="color:#0029FF; width: 100px; font-family: arial; font-weight: bold">Cadastrar</button>
                </li>
                <div class="spacer"></div>
            </ul>
        </fieldset>
    </form>
</div>           