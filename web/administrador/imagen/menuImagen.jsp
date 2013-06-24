<%-- 
    Document   : menuImagen
    Created on : 26-feb-2012, 14:57:54
    Author     : zzz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Imagen</title>
        <link rel="stylesheet" href="../css/admin/indexProducto.css" type="text/css" media="screen" />
        <script src="http://code.jquery.com/jquery-latest.js"></script>
            
        <script type="text/javascript">
            $(document).ready(function() {
                $(".rImagen").click(function(event) {
                    $("#contenido2").load('imagen/addImagen.jsp');
                    $.getScript('js/b.js');
                });
            });
        </script>
    </head>
    <body>
        <div class="submenu">
            <div id="menup">
                <ul>
                    <li><a href="#" class="rImagen">Add Imagen</a></li>
                </ul>
            </div>
        </div>
        <div class="contenido" id="contenido2"></div>
    </body>
</html>