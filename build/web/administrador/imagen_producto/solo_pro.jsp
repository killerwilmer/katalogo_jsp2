<%-- 
    Document   : solo_pro
    Created on : 10-jul-2012, 9:35:51
    Author     : killer
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.scecolombia.logicanegocio.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table border="0">
            <thead>
                <%
                    Producto claseProducto = new Producto();
                    String marca = request.getParameter("elegido");
                    ArrayList<Producto> misProductos = claseProducto.consultarPorMarca(marca);
                    //out.print(misProductos);
                    if (misProductos == null) {
                %>
                <tr style="background-color: #efefef">
                    <th>En esta Categoria no hay productos</th>
                </tr>
                <%                    } else {
                    for (int i = 0; i < misProductos.size(); i++) {
                        Producto miProducto = (Producto) misProductos.get(i);
                        String nombreProducto = miProducto.getNombre();
                        String codigoProducto = miProducto.getCodigo();
                %>          
                <tr style="background-color: #efefef">
                    <th><b><%=i + 1%></b></th>
                    <th><%=nombreProducto%></th>
                    <th><input type="checkbox" name="productos" value="<%=codigoProducto%>" /></th>
                </tr>
                <%
                        }
                    }
                %>
            </thead>
        </table>
    </body>
</html>
