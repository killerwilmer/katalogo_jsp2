<%-- 
    Document   : clientes_archivo
    Created on : 08-ago-2012, 8:21:43
    Author     : killer
--%>

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
        <title>Archivo Clientes</title>
    </head>
    <body>
        <h1>Archivo Clientes</h1>
        <%
            String contentType = request.getContentType();
            //String ruta = "/var/www/sqlite/imports/import_cliente_aux.sh";
            String ruta = "/var/www/katalogo_php/default/app/test/sqlite/imports/import_cliente_aux.sh";
            String ruta2 = "/var/www/katalogo_php/default/app/test/sqlite/imports/import_cliente_katalogo.sh";

            if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
                String miLineaLimpia = "";
                DataInputStream in = new DataInputStream(request.getInputStream());

                ConexionC miConexion = new ConexionC();
                //String sql = "INSERT INTO cliente VALUES(?,?,?,?,?,?,?,?);";
                //PreparedStatement pstmt = miConexion.getConexion().prepareStatement(sql);

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
                    Cliente miCliente = new Cliente();

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
                            miCampo = "NULL";
                        }

                        switch (j) {
                            case 0:
                                miCliente.setNit(miCampo);
                                break;
                            case 1:
                                miCliente.setSucursal(miCampo);
                                break;
                            case 3:
                                miCliente.setNombre(miCampo);
                                break;
                            case 4:
                                miCliente.setNomTienda(miCampo);
                                break;
                            case 5:
                                miCliente.setDireccion(miCampo);
                                break;
                            case 6:
                                miCliente.setTelefono1(miCampo);
                                break;
                            default:
                                break;
                        }
                        Date fecha = new Date();
                        String time = String.valueOf(fecha.getTime());
                        miCliente.setTelefono2("NULL");
                        miCliente.setFechaActulizacion(time);
                        //System.out.println(miCliente.getFechaActulizacion());
                    }
                    miLineaLimpia += idCliente + "," + miCliente.getNombre() + "," + miCliente.getDireccion() + "," + miCliente.getTelefono1() + ","
                            + miCliente.getNit() + "," + miCliente.getTelefono2() + "," + miCliente.getFechaActulizacion() + "," + miCliente.getNomTienda() + "," + miCliente.getSucursal() + "\n";
                    idCliente++;
                    //consultar por nit si existe
                    //Cliente miClienteLocal = new Cliente();
                    //miClienteLocal = (Cliente) miClienteLocal.consultarUnoPorNit(miCliente.getNit());
                    //if (miClienteLocal != null) {
                    //pstmt.setString(2, miCliente.getNombre());
                    //pstmt.setString(3, miCliente.getDireccion());
                    //pstmt.setString(4, miCliente.getTelefono1());
                    //pstmt.setString(5, miCliente.getNit());
                    //pstmt.setString(6, miCliente.getTelefono2());
                    //pstmt.setString(7, miCliente.getFechaActulizacion());
                    //pstmt.setString(8, miCliente.getNomTienda());
                    //pstmt.execute();
                    //miClienteLocal.crear(miCliente);
                    /*} else {
                     miClienteLocal.modificarPorNit(miCliente);
                     }*/
                }
                //System.out.print(miLineaLimpia);


                String fileName = "/var/www/katalogo_php/default/app/test/sqlite/csvs/clientes.csv";
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
                //out.print("<script type='text/javascript'>alert('Clientes Actualizados');</script>");
                //out.print("<script type='text/javascript'>history.back();</script>");
            }
        %>
    </body>
</html>