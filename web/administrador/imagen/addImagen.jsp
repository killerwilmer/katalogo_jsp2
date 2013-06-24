<%-- 
    Document   : addImagen
    Created on : 26-feb-2012, 14:58:10
    Author     : zzz
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.File"%>
<%@page import="com.scecolombia.utilidades.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Conexion miConexion = new Conexion();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Imagen</title>
        <link rel="stylesheet" href="../css/admin/nproducto.css" type="text/css" media="screen" />
        <script src="http://code.jquery.com/jquery-latest.js"></script>
    </head>
    <body>
        <div id="stylized" class="myform">
            <form id="form" action="imagen/upload.jsp" enctype="MULTIPART/FORM-DATA" method="post" >
                <h1>Agregar Imagen</h1>
                <label>Ruta pc()
                    <span class="small">Add la Imagen</span>
                </label>
                <input type="file" id="file" name="file" />
                                
                <input name="enviar" type="submit" value="Enviar" />
                <div class="spacer"></div> 
                
            </form>
            <%
                Date fechaHora = new Date();
                Calendar c = new GregorianCalendar();
                    
                String dia, mes, annio;
                    
                dia = Integer.toString(c.get(Calendar.DATE));
                mes = Integer.toString(c.get(Calendar.MONTH) + 1);
                annio = Integer.toString(c.get(Calendar.YEAR));
                    
                String fecha = fechaHora.getHours() + "_" + fechaHora.getMinutes() + "_" + fechaHora.getSeconds() + "_" + dia + "_" + mes + "_" + annio;
    %>
        </div>
    </body>
    
        <%
        if ( request.getParameter("enviar") != null )
        {
            String ruta = request.getParameter("ruta").trim();

            if ( ruta.length() == 0 )
            {
                out.print("<script languaje = javascript> alert('Todos los Campos son requeridos'); history.back(); </script>");
            }
            else
            {
                try {
                    
                    File image = new File(ruta);
                    PreparedStatement psmt = miConexion.conection.prepareStatement("insert into imagen(imagen)"+"values(?)");;
                    FileInputStream fis = new FileInputStream(image);
                    psmt.setBinaryStream(1, (InputStream)fis, (int)(image.length()));
                    int s = psmt.executeUpdate();
                    
                    if (s > 0) {
                        out.print("<script languaje = javascript> alert('Imagen Guardada');</script>");
                    }
                    else {
                        out.print("<script languaje = javascript> alert('No se pudo guardar la Imagen');</script>");
                    }
                        
                    miConexion.conection.close();
                    psmt.close();
                } catch (Exception ex) {
                    out.println("Error in connection : " + ex);
                }
            }
         }
    %>
    
</html>