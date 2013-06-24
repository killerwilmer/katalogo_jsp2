<%-- 
    Document   : fabricante
    Created on : 22-feb-2012, 20:12:04
    Author     : usuarui
--%>

<%@page import="com.scecolombia.datos.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Conexion miConexion = new Conexion();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Fabricante</title>
        <link rel="stylesheet" href="../css/cssAdministrador/nproducto.css" type="text/css" media="screen" />
        <script src="http://code.jquery.com/jquery-latest.js"></script>
    </head>
    <body>
        <div id="stylizedfabricante" class="myformfabricante">
            <form id="form" action="fabricante/fabricante.jsp" method="post">
                <h1>Registro Fabricante</h1>
                <label>Nombre
                    <span class="small">Add el fabricante</span>
                </label>
                <input type="text" name="nombre" id="nombre" />
                
                <fieldset>
                    <legend align= "center">Activar o Desactivar</legend>
                    <p>        
                        <input type="radio" name="estado" id="sizeSmall" value="1" checked="checked" style="width: 15px; float: left; margin-left: 13%"/>
                        <label for="sizeSmall" style="width: 40px; float:left; ">Activo</label>
                        <input type="radio" name ="estado" id="sizeMed" value ="0" style="width: 15px; float: left; margin-left: 140px"/>
                        <label for="sizeMed" style="width: 50px; float: left">Inactivo</label>
                    </p>       
                </fieldset>  
                
                <input id="boton" name="enviar" type="submit" value="Enviar" />
                <div class="spacer"></div> 
            </form>
        </div>
    </body>
            <%
        if ( request.getParameter("enviar") != null )
        {
            String nombre = request.getParameter("nombre").trim();
            int estado = Integer.parseInt(request.getParameter("estado").trim());

            if ( nombre.length() == 0 )
            {
                out.print("<script languaje = javascript> alert('Todos los Campos son requeridos'); history.back(); </script>");
            }
            else
            {
                String SQLIden = "Select nombre from fabricante where nombre ='" + nombre + "'";
                if (miConexion.existeRegistro(SQLIden))
                {
                    out.print("<script languaje = javascript> alert('Fabricante ya existe'); history.back(); </script>");
                }
                else
                {
                    String SQL = "insert into fabricante (nombre,activo) values('"+nombre+"','"+estado+"');";
                    if (miConexion.ejecutarOperacion(SQL))
                    {
                        out.print("<script languaje = javascript> alert('Fabricante Registrado');</script>");
                    }
                    else
                    {
                        out.print("<script languaje = javascript> alert('No se pudo registrar el Fabricante');</script>");
                    }
                }
            }
         }
    %>
</html>
