<%-- 
    Document   : index
    Created on : 19-feb-2012, 19:31:44
    Author     : zzz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Sesión</title>
        <link rel="stylesheet" href="css/index.css" type="text/css" media="screen" />
    </head>
    <body>

        <div class="header-container" >   

        </div>

        <div class="wrapper">
            <div id="foto">
                <img src="imagenes/wap.png" class="imagen1">
            </div>
            <form id="loginForm" name="logeo" action="login.jsp" method="post">
                <fieldset id="body">
                    <fieldset>
                        <label for="usuario">Usuario</label>
                        <input type="text" name="usuario" class="email" id="email"/>
                    </fieldset>
                    <fieldset>
                        <label for="password">Contraseña</label>
                        <input type="password" name="password" class="pas" id="password"/>
                    </fieldset>
                    <label for="checkbox"><input type="checkbox" id="checkbox" />Recordarme</label>
                    <input type="submit" id="login" value="Iniciar" />
                </fieldset>
                <span><a href="#">¿Olvidaste tu contraseña?</a></span>
                    <div id="errorSesion">
                       <%
                        if(request.getParameter("error")!=null)
                        {
                            out.println(request.getParameter("error"));               
                        }
                       %>
                   </div>
            </form>     
        </div>
        


        <div id="footer-container">
            <div class="wrapper2">
                <h3>Pie de la pagina</h3>
            </div>
        </div> 
    </body>
</html>
