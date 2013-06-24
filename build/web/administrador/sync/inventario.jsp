<%-- 
    Document   : clientes
    Created on : 10/08/2012, 08:16:07 AM
    Author     : skynet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sync Inventario</title>
    </head>
    <body>
        <h1>Inventario</h1>        
        <form name="form1" method="post" enctype="multipart/form-data" action="inventario_archivo.jsp">
            <h1>Escoja el archivo! (Inventario)</h1>
            <input type="file" name="file" /><br /><br />
            <input type="submit" />
        </form>
    </body>
</html>