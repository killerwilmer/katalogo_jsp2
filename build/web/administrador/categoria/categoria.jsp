<%-- 
    Document   : categoria
    Created on : 22-feb-2012, 20:11:50
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
        <title>Registro Categoria</title>
        <link rel="stylesheet" href="../css/cssAdministrador/nproducto.css" type="text/css"/>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
    </head>
    <body>
        <div id="stylizedcategoria" class="myformcategoria">
            <form id="form" action="categoria/categoria.jsp" method="post">
                <h1>Registro Categoria</h1>
                <label>Nombre
                    <span class="small">Add la Categoria</span>
                </label>
                <input type="text" name="nombre" id="nombre" />
                    
                <fieldset>
                    <legend align= "center">Activar o Desactivar</legend>
                    <p>        
                        <input type="radio" name="estado" id="sizeSmall" value="1" checked="checked" style="width: 15px; float: left; margin-left: 13%"/>
                        <label for="sizeSmall" style="width: 40px; float:left; ">Activo</label>
                        <input type="radio" name ="estado" id="sizeMed" value ="0" style="width: 15px; float: left; margin-left: 130px"/>
                        <label for="sizeMed" style="width: 50px; float: left">Inactivo</label>
                    </p>       
                </fieldset>  
                  
                <input id="boton" name="enviar" type="submit" value="Guardar" />
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
                String SQLIden = "Select nombre from categoria where nombre ='" + nombre + "'";
                if (miConexion.existeRegistro(SQLIden))
                {
                    out.print("<script languaje = javascript> alert('Categoria ya existe'); history.back(); </script>");
                }
                else
                {
                    String SQL = "insert into categoria (nombre,activo) values('"+nombre+"','"+estado+"');";
                    if (miConexion.ejecutarOperacion(SQL))
                    {
                        out.print("<script languaje = javascript> alert('Categoria Registrada');</script>");
                    }
                    else
                    {
                        out.print("<script languaje = javascript> alert('No se pudo registrar la Categoria');</script>");
                    }
                }
            }
         }
    %>
    
</html>
