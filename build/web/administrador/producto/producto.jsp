<%-- 
    Document   : producto
    Created on : 22-feb-2012, 14:02:24
    Author     : usuarui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Producto</title>
        <link rel="stylesheet" href="../css/cssAdministrador/indexProducto.css" type="text/css"/>
        
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $(".nuevoProducto").click(function(event) {
                    $("#contenido2").load('producto/nuevoProducto.jsp');
                    $.getScript('js/b.js');
                });
            });
        </script>
    </head>
    <body>
        <div id="contenedor2">
            <div class="submenu">
                <div id="span"><span>Producto</span></div>
                <div id="menup">
                    <ul>
                        <li><a href="#" class="nuevoProducto" >Nuevo</a></li>
                        <li><a href="#" >Archivos</a></li>
                        <li><a href="#" >Enlaces</a></li>
                        <li><a href="#" >Acerca de</a></li>
                    </ul>
                </div>
            </div>
            <div class="contenido" id="contenido2"></div>
        </div>
    </body>
</html>
