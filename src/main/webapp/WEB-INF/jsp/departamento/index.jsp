<style type="text/css">


    span.destaque{
        padding-left: 20px;
    }

    #mytable {
        width: 100%;
        padding: 0;
        margin: 0;
    }

    caption {
        padding: 10px 0 5px 0;
        width: 100%;	 
        font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        text-align: right;
    }

    th {
        font: bold 9px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #66A3D3; 
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        border-top: 1px solid #C1DAD7;
        letter-spacing: 2px;
        text-transform: uppercase;
        text-align: left;
        padding: 6px 6px 6px 12px;
        background: #F4F9FE;
    }

    th.nobg {
        border-top: 0;
        border-left: 0;
        border-right: 1px solid #C1DAD7;
        background: none;
    }

    td {
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        background: #fff;
        padding: 6px 6px 6px 12px;
        color: #4f6b72;
        font: 9px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
    }


    td.alt {
        background: #F5FAFA;
        color: #797268;
    }

    th.spec {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #fff url(images/bullet1.gif) no-repeat;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
    }

    th.specalt {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #f5fafa url(images/bullet2.gif) no-repeat;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #797268;
    }    




    .myform{
        margin:0 auto;
        width: 680px;
        padding:14px;
        height: 100%;

    }

    fieldset#fdDepartamentos {
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
    fieldset#fdDepartamentos ul, fieldset#fdDepartamentos li{
        border:0; margin:0; padding:0; list-style:none;
        margin-bottom: 2px;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        padding-left: 5px;

    }

    fieldset#fdDepartamentos li{
        clear:both;
        list-style:none;
        padding-bottom:10px;   
    }
    fieldset#fdDepartamentos label{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 100px;
    }

    fieldset#fdDepartamentos label.grd{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 470px;
    }   
    fieldset#fdDepartamentos label.med{
        float:left;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 10px;
        cursor: pointer;
        padding-left: 5px;
        width: 330px;
    }     

    fieldset#fdDepartamentos label.peq{
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

<form action="<c:url value="/departamentos/filtrar" />" name="frmBuscaDepartamento" id="frmBuscaDepartamento">
    <fieldset id="fdDepartamentos" style="margin-top: 12px;">
        <ul>
            <li>
                <label style="width: 110px; padding-bottom: 30px">Descrição<br/>
                    <input type="text" name="departamento.descricao" value="${departmaento.descricao}" style="width: 100px"/>                      
                </label>                

            </li>

           
            <li>
                <button type="submit" style="color:#0029FF; width: 100px; font-family: arial; font-weight: bold">Buscar</button>
            </li>
            <div class="spacer"></div>
        </ul>
    </fieldset>
</form>


<br/>

<span class="destaque">Total de ${qtde} registros divididos em ${qtdPaginas} página(s)</span>
