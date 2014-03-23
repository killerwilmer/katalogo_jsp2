<%-- 
    Document   : clientes_archivo
    Created on : 08-ago-2012, 8:21:43
    Author     : killer
--%>

<%@page import="com.scecolombia.logicanegocio.ProductoAux"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.scecolombia.utilidades.ConexionC"%>
<%@page import="java.util.Date"%>
<%@page import="com.scecolombia.logicanegocio.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="java.io.DataInputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Archivo Producto</title>
    </head>
    <body>        
        <h1>Archivo Producto</h1>
        <%
            String contentType = request.getContentType();
            String ruta = "/var/www/katalogo_php/default/app/test/sqlite/imports/import_producto_katalogo.sh";
            String ruta2 = "/var/www/katalogo_php/default/app/test/sqlite/imports/import_producto_aux.sh";

            if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
                String miLineaLimpia = "";

                DataInputStream in = new DataInputStream(request.getInputStream());

                BufferedReader miBuffer = new BufferedReader(new InputStreamReader(in));
                String linea;
                List miLista = new ArrayList();
                while ((linea = miBuffer.readLine()) != null) {
                    if (linea.trim().length() != 0) {
                        miLista.add(linea);
                    }
                }
                int idCliente = 1;
                for (int i = 4; i < miLista.size() - 1; i++) {
                    String miLinea = miLista.get(i).toString();
                    String[] arrayCampos = miLinea.split(",");
                    ProductoAux miProductoAux = new ProductoAux();

                    // En este momento tenemos un array en el que cada elemento es dato
                    for (int j = 0; j < arrayCampos.length; j++) {

                        String miCampo = arrayCampos[j].toString();
                        miCampo = miCampo.substring(1, miCampo.length() - 1);
                        miCampo = miCampo;

                        miCampo = miCampo.replaceFirst("^0*", "");
                        if (miCampo.equals("")) {
                            miCampo = "0";
                        }

                        miCampo = miCampo.replaceAll(" +", " ");
                        if (miCampo.equals("") || miCampo.equals(" ")) {
                            miCampo = "OTROS";
                        }
                        miCampo = miCampo.trim();

                        switch (j) {
                            case 1:
                                //id producto
                                miProductoAux.setProducto(miCampo);
                                break;
                            case 2:
                                //descripcion
                                miProductoAux.setDescripcion(miCampo);
                                break;
                            case 3:
                                //precio 001
                                miProductoAux.setPrecio1(miCampo);
                                break;
                            case 4:
                                //precio 002
                                miProductoAux.setPrecio2(miCampo);
                                break;
                            case 5:
                                //precio 003
                                miProductoAux.setPrecio3(miCampo);
                                break;
                            case 6:
                                //precio 004
                                miProductoAux.setPrecio4(miCampo);
                                break;
                            case 15:
                                //porcentaje iva
                                miProductoAux.setPorcentajeIva(miCampo);
                                break;
                            case 16:
                                //unidad de medida
                                miProductoAux.setUnidadMedida(miCampo);
                                break;
                            case 17:
                                //marca
                                miProductoAux.setMarca(miCampo);
                                break;
                            case 33:
                                //producto activo
                                miProductoAux.setActivo(miCampo);
                                break;
                            default:
                                break;
                        }
                    }
                    if (miProductoAux.getActivo().equals("S")) {
                        miLineaLimpia += idCliente + "," + miProductoAux.getProducto() + "," + miProductoAux.getDescripcion() + "," + miProductoAux.getPrecio1() + ","
                                + miProductoAux.getPrecio2() + "," + miProductoAux.getPrecio3() + "," + miProductoAux.getPrecio4() + "," + miProductoAux.getPorcentajeIva() + "," + miProductoAux.getUnidadMedida() + ","
                                + miProductoAux.getMarca() + "\n";
                        idCliente++;
                    }
                }

                String fileName = "/var/www/katalogo_php/default/app/test/sqlite/csvs/productos.csv";
                try {
                    FileWriter writer = new FileWriter(fileName);
                    writer.append(miLineaLimpia);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                Runtime.getRuntime().exec("/bin/sh " + ruta);
                Runtime.getRuntime().exec("/bin/sh " + ruta2);
            }
        %>
    </body>
</html>