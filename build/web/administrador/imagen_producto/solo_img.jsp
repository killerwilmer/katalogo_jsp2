<%-- 
    Document   : solo_img
    Created on : 10-jul-2012, 9:33:31
    Author     : killer
--%>

<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Image"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.scecolombia.logicanegocio.Imagen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

        <script type="text/javascript">

            // Load this script once the document is ready
            $(document).ready(function () {
		
                // Get all the thumbnail
                $('div.thumbnail-item').mouseenter(function(e) {

                    // Calculate the position of the image tooltip
                    x = e.pageX - $(this).offset().left;
                    y = e.pageY - $(this).offset().top;

                    // Set the z-index of the current item, 
                    // make sure it's greater than the rest of thumbnail items
                    // Set the position and display the image tooltip
                    $(this).css('z-index','100')
                    .children("div.tooltip")
                    .css({'top': y + 10,'left': x + 20,'display':'block'});
			
                }).mousemove(function(e) {
			
                    // Calculate the position of the image tooltip			
                    x = e.pageX - $(this).offset().left;
                    y = e.pageY - $(this).offset().top;
			
                    // This line causes the tooltip will follow the mouse pointer
                    $(this).children("div.tooltip").css({'top': y - 180,'left': x + 100});                        
			
                }).mouseleave(function() {
			
                    // Reset the z-index and hide the image tooltip 
                    $(this).css('z-index','200')
                    .children("div.tooltip")
                    .animate({"opacity": "hide"}, "fast");
                });

            });
        </script>

    </head>
    <body>
        <table border="0">
            <thead>
                <%
                    Imagen claseImagen = new Imagen();
                    int categoria = Integer.parseInt(request.getParameter("elegido"));
                    ArrayList<Object> misImagenes = claseImagen.consultarPorCategoria(categoria);

                    for (int i = 0; i < misImagenes.size(); i++) {

                        // Begin ... Buscar la imagen en disco
                        Imagen miImagen = (Imagen) misImagenes.get(i); // Sacamos la imagen de la db

                        String nombreArchivo = "imagen" + miImagen.getId() + ".jpg"; // jpg mucho mas liviano 80%
                        char sep = File.separatorChar;
                        String miRuta = config.getServletContext().getRealPath("/") + "img" + sep + nombreArchivo;
                        int idImagen = miImagen.getId();

                        File archivo = new File(miRuta);
                        // End ... Bucar la imagen en disco


                        // Si la imagen ya existe nos saltamos la creacion en memoria y en disco
                        if (archivo.exists() == true) {
                            System.out.print("No lo crea");
                        } else {
                            System.out.print("lo crea");
                            try {
                                byte[] imgData = miImagen.getImagen();
                                ImageIcon imagen = null;
                                Image img2 = null;
                                ImageIcon imagenEsc = null;
                                if (imgData != null) {
                                    imagen = new ImageIcon(imgData);
                                    img2 = imagen.getImage();
                                    imagenEsc = new ImageIcon(img2.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                                } else {
                                    //ponemos una imagen por defecto
                                }

                                ImageIcon icon = imagenEsc;
                                Image img = icon.getImage();
                                BufferedImage buf = com.scecolombia.utilidades.BufferImage.getBufferedImage(img);

                                ImageIO.write(buf, "jpg", archivo); // Creamos la imagen en disco
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                %>          
                <tr style="background-color: #efefef">
                    <th><b><%=i + 1%></b></th>
                    <th>
                        <div class="thumbnail-item">
                            <a href="#"><img src="../../img/<%=nombreArchivo%>" width="100" height="100" class="thumbnail"/></a>
                            <div class="tooltip">
                                <img src="../../img/<%=nombreArchivo%>" alt="" width="300" height="300"/>
                                <span class="overlay"></span>
                            </div> 
                        </div>
                    </th>
                    <th><input type="checkbox" name="imagenes" value="<%=idImagen%>" /></th>
                </tr>

                <%
                    }
                %>
            </thead>
        </table>
    </body>
</html>