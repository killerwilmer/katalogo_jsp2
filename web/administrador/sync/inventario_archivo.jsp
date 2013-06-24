<%-- 
    Document   : clientes_archivo
    Created on : 08-ago-2012, 8:21:43
    Author     : killer
--%>

<%@page import="com.sun.xml.rpc.processor.modeler.j2ee.xml.string"%>
<%@page import="com.scecolombia.logicanegocio.InventarioAux"%>
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
        <title>Archivo Inventario</title>
    </head>
    <body>
        <h1>Archivo Inventario</h1>
        <%
            String contentType = request.getContentType();
            String ruta = "/var/www/katalogokumbia/default/app/test/sqlite/imports/import_inventario_katalogo.sh";
            String ruta2 = "/var/www/katalogokumbia/default/app/test/sqlite/imports/import_inventario_aux.sh";

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
                    InventarioAux miInventarioAux = new InventarioAux();

                    // En este momento tenemos un array en el que cada elemento es dato
                    for (int j = 0; j < arrayCampos.length; j++) {

                        String miCampo = arrayCampos[j].toString();
                        if (miCampo.substring(0, 1).equals("\"")) {
                            miCampo = miCampo.substring(1, miCampo.length() - 1);
                        }

                        miCampo = miCampo.replaceFirst("^0*", "");
                        if (miCampo.equals("")) {
                            miCampo = "0";
                        }

                        miCampo = miCampo.replaceAll(" +", " ");
                        if (miCampo.equals("") || miCampo.equals(" ")) {
                            miCampo = "NULL";
                        }
                        miCampo = miCampo.trim();

                        switch (j) {
                            case 0:
                                miInventarioAux.setProducto(miCampo);//3202734161--3202322735
                                break;
                            case 1:
                                miInventarioAux.setReferencia(miCampo);
                                break;
                            case 5:
                                //double number = Double.parseDouble(miCampo);
                                miInventarioAux.setBodega(miCampo);
                                break;
                            case 7:
                                //double number = Double.parseDouble(miCampo);
                                miInventarioAux.setFechaCompra(miCampo);
                                break;
                            default:
                                break;
                        }
                    }
                    miLineaLimpia += idCliente + "," + miInventarioAux.getProducto() + "," + miInventarioAux.getReferencia() + "," + miInventarioAux.getBodega() + "," + miInventarioAux.getFechaCompra() + "\n";
                    idCliente++;
                }

                String fileName = "/var/www/katalogokumbia/default/app/test/sqlite/csvs/inventario.csv";
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