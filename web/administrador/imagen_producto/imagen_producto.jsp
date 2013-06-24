<%-- 
    Document   : imagen_producto
    Created on : 29-mar-2012, 23:08:43
    Author     : zzz
--%>
<%@page import="com.scecolombia.interfaz.combos.Combos"%>
<%@page import="com.scecolombia.logicanegocio.Producto"%>
<%@page import="java.io.File"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Image"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="java.util.Iterator"%>
<%@page import="sun.awt.image.OffScreenImage"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="com.scecolombia.logicanegocio.Imagen"%>
<%@page import="sun.awt.image.OffScreenImage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Imagen con producto</title>
        <link rel="stylesheet" type="text/css" href="../../css/cssAdministrador/doscolumnas.css" media="screen" />
        <script type="text/javascript" src="../../jquery/jquery.min.js"></script>

        <script language="javascript">
            $(document).ready(function(){
                // Parametros para el combo principal
                $("#comboproductos").change(function () {//combo principal donde tenemos los primero datos
                    $("#comboproductos option:selected").each(function () {
                        //alert($(this).val());
                        elegido=$(this).val();//recogemos el valor seleccionado del combo
                        $.post("solo_pro.jsp", { elegido: elegido }, function(data){//pagina de donde traemos la info
                            $("#colpro").html(data);//Este es el div donde se cargara la nueva info
                        });			
                    });
                })
            });
        </script>

        <script language="javascript">
            $(document).ready(function(){
                // Parametros para el combo principal
                $("#comboimagenes").change(function () {//combo principal donde tenemos los primero datos
                    $("#comboimagenes option:selected").each(function () {
                        //alert($(this).val());
                        elegido=$(this).val();//recogemos el valor seleccionado del combo
                        $.post("solo_img.jsp", { elegido: elegido }, function(data){//pagina de donde traemos la info
                            $("#colimg").html(data);//Este es el div donde se cargara la nueva info
                        });			
                    });
                })
            });
        </script>

        <style>

            .thumbnail-item { 
                /* position relative so that we can use position absolute for the tooltip */
                position: relative; 
                float: left;  
                margin: 0px 5px; 
            }

            .thumbnail-item a { 
                display: block; 
            }

            .thumbnail-item img.thumbnail {
                border:3px solid #ccc;	
            }

            .tooltip { 
                /* by default, hide it */
                display: none; 
                /* allow us to move the tooltip */
                position: absolute; 
                /* align the image properly */
                padding: 0px 0 0 8px; 
            }

            .tooltip span.overlay { 
                /* the png image, need ie6 hack though */
                background: url(overlay.png) no-repeat; 
                /* put this overlay on the top of the tooltip image */
                position: absolute; 
                top: 2px; 
                left: 0px; 
                display: block; 
                width: 320px; 
                height: 315px;
            }
        </style>


    </head>
    <body>
        <form method="POST" ACTION="imagen.jsp">
            <div id="header">
                <p>Relacion de imagen producto</p>
            </div>

            <div class="colmask leftmenu">
                <div class="colleft">
                    <div class="col1"> <!-- COLUMNA DE LOS PRODUCTOS -->

                        <div id="divproductos" class="divprodcutos">
                            Escoja la Marca <select name="comboproductos" id="comboproductos">
                                <option value="0">Elija la Marca</option>
                                <%
                                    Combos miCombo = new Combos();
                                    out.print(miCombo.darComboMarcas());
                                %>
                            </select>
                        </div>
                        <!-- Column 1 start -->
                        <div id="colpro">

                        </div>
                        <!-- Column 1 end -->
                    </div>
                    <div class="col2"> <!-- COLUMNA DE LAS IMAGENES -->
                        <div id="divimagenes" class="divimagenes">
                            Escoja la Categoria: <select name="comboimagenes" id="comboimagenes">
                                <option value="0">Elija la Categoria</option>
                                <%
                                    out.print(miCombo.darCombosCategoria());
                                %>
                            </select>
                        </div>
                        <!-- Column 2 start -->
                        <br> <INPUT TYPE=submit name=submit Value="Enviar"> <br>
                        <div id="colimg">

                        </div>
                        <!-- Column 2 end -->                          
                    </div>
                </div>
            </div>        
            <div id="footer">
                <p>Contactar.</p>
            </div>
        </form>
    </body>
</html>