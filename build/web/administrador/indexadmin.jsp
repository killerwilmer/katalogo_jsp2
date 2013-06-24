<%-- 
    Document   : index
    Created on : 22-feb-2012, 8:56:11
    Author     : zzz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <title>Menu Admin</title>
        <link rel="stylesheet" href="../css/cssAdministrador/index.css" type="text/css"/>
        <!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
        <script src="../jquery/jquery.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function() {
                $(".producto").click(function(event) {
                    $("#contenido").load('producto/producto.jsp');
                    $.getScript('js/b.js');
                });
                $(".categoria").click(function(event) {
                    $("#contenido").load('categoria/menuCategoria.jsp');
                    $.getScript('js/b.js');
                });
                $(".fabricante").click(function(event) {
                    $("#contenido").load('fabricante/menuFabricante.jsp');
                    $.getScript('js/b.js');
                });
                $(".imagen").click(function(event) {
                    $("#contenido").load('imagen/menuImagen.jsp');
                    $.getScript('js/b.js');
                });
                $(".sync").click(function(event) {
                    $("#contenido").load('sync/menuSync.jsp');
                    $.getScript('js/b.js');
                });
                $(".relacion").click(function(event) {
                    $("#contenido").load('relacion/menuRelacion.jsp');
                    $.getScript('js/b.js');
                });
            });
        </script>
    </head>
    <body>
        <div id="contenedor">
            <div class="headers">
                <nav id="menu">
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#" class="producto"><span>Producto</span></a></li>
                        <li><a href="#" class="categoria">Categoria</a></li>
                        <li><a href="#" class="fabricante">Fabricante</a></li>
                        <li><a href="#">Videos</a></li>
                        <li><a href="#" class="imagen">Imagen</a></li>
                        <li><a href="#" class="relacion">Relación</a></li>
                        <li><a href="#" class="sync">Sync</a></li>
                    </ul>
                </nav>      
            </div>
            <div class="wrappers" id="contenido"></div>
        </div>
    </body>
</html>