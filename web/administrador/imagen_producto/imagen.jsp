<%-- 
    Document   : imagen
    Created on : 06-abr-2012, 9:51:14
    Author     : zzz
--%>

<%@page import="com.scecolombia.logicanegocio.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%! String[] imagenes;
            String[] productos;
        %>
        <center>Seleccionaste : 
            <%
                imagenes = request.getParameterValues("imagenes");
                productos = request.getParameterValues("productos");

                if (imagenes != null && productos != null) {
                    for (int i = 0; i < imagenes.length; i++) {
                        out.println("<b> La imagen # " + imagenes[i] + "<b>");
                        for (int j = 0; j < productos.length; j++) {
                            out.println("<b> productos " + productos[j] + "<b>");
                            Producto prod = new Producto();
                            prod.asociarImagen(imagenes[i],productos[j]);
                        }
                    }
                } else {
                    out.println("<b>nada<b>");
                }

                /*
                 * if (productos != null) { for (int i = 0; i <
                 * productos.length; i++) { out.println("<b>" + productos[i] +
                 * "<b>"); } } else { out.println("<b>nada<b>");
                }
                 */
            %>
        </center>
    </body>
</html>
