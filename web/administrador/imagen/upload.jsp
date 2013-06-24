<%@page import="com.scecolombia.datos.Conexion"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page import="java.io.*,java.sql.*,java.util.zip.*" %>
<%
Conexion miConexion = new Conexion();
%>
<%
    String saveFile = "";
    String contentType = request.getContentType();
    if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
        DataInputStream in = new DataInputStream(request.getInputStream());
        int formDataLength = request.getContentLength();
        byte dataBytes[] = new byte[formDataLength];
        int byteRead = 0;
        int totalBytesRead = 0;
        while (totalBytesRead < formDataLength) {
            byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
            totalBytesRead += byteRead;
        }
        String file = new String(dataBytes);
        saveFile = file.substring(file.indexOf("filename=\"") + 10);
        saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
        saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
        int lastIndex = contentType.lastIndexOf("=");
        String boundary = contentType.substring(lastIndex + 1, contentType.length());
        int pos;
        pos = file.indexOf("filename=\"");
        pos = file.indexOf("\n", pos) + 1;
        pos = file.indexOf("\n", pos) + 1;
        pos = file.indexOf("\n", pos) + 1;
        int boundaryLocation = file.indexOf(boundary, pos) - 4;
        int startPos = ((file.substring(0, pos)).getBytes()).length;
        int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
        
        Date fechaHora = new Date();
        Calendar c = new GregorianCalendar();

        String dia, mes, annio;

        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        annio = Integer.toString(c.get(Calendar.YEAR));

        String fecha = fechaHora.getHours() + "_" + fechaHora.getMinutes() + "_" + fechaHora.getSeconds() + "_" + dia + "_" + mes + "_" + annio;
        
        File image = new File("C:/UploadedFiles/" + fecha + saveFile);
        
        
        
        FileOutputStream fileOut = new FileOutputStream(image);
        fileOut.write(dataBytes, startPos, (endPos - startPos));
        fileOut.flush();
        fileOut.close();
        /*
        Connection connection = null;
        String connectionURL = "jdbc:mysql://localhost/katalogo";
        PreparedStatement psmnt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "mysql");
            psmnt = connection.prepareStatement("insert into imagen(imagen) values(?)");
            psmnt.setString(1, imagen.getPath());
            int s = psmnt.executeUpdate();
            if (s > 0) {
                out.println("Uploaded successfully !");
            } else {
                out.println("Error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        try {
                PreparedStatement psmt = miConexion.conection.prepareStatement("insert into imagen(imagen)" + "values(?)");;
                FileInputStream fis = new FileInputStream(image);
                psmt.setBinaryStream(1, (InputStream) fis, (int) (image.length()));
                int s = psmt.executeUpdate();

                if (s > 0) {
                    out.print("<script languaje = javascript> alert('Imagen Guardada');</script>");
                } else {
                    out.print("<script languaje = javascript> alert('No se pudo guardar la Imagen');</script>");
                }

                miConexion.conection.close();
                psmt.close();
            } catch (Exception ex) {
                out.println("Error in connection : " + ex);
            }        
    }
%>