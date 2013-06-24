<%-- 
    Document   : menuCategoria
    Created on : 22-feb-2012, 20:22:43
    Author     : usuarui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Categoria</title>
        <link rel="stylesheet" href="../css/cssAdministrador/indexProducto.css" type="text/css" media="screen" />
        <script src="http://code.jquery.com/jquery-latest.js"></script>
            
        <script type="text/javascript">
            $(document).ready(function() {
                $(".rCategoria").click(function(event) {
                    $("#contenido2").load('categoria/categoria.jsp');
                    $.getScript('js/b.js');
                });
            });
        </script>
    </head>
    <body>
        <div class="submenu">
            <div id="span"><span>Categoria</span></div>
            <div id="menup">
                <ul>
                    <li><a href="#" class="rCategoria">Registro</a></li>
                </ul>
            </div>
        </div>
        <div class="contenido" id="contenido2"></div>
    </body>
</html>
