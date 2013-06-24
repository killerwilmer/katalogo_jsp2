package com.scecolombia.utilidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ConexionC {
    
    //public String cadenaConexion = "jdbc:mysql://criferlo.zapto.org:3306/katalogo"; // mysql
    //public String cadenaConexion = "jdbc:sqlite:/Applications/sqlite/data/katalogo.db";
    public String cadenaConexion = Constantes.RUTA_DB_KATALOGO; // servidor
    //public String cadenaConexion = "jdbc:sqlite:C://Users//zzz//Dropbox//Public//katalogo.db"; // killer win
    //public String cadenaConexion = "jdbc:sqlite:/home/killer/Dropbox/Public/katalogo.db"; // killer linux
    
    public String getCadenaConexion() {
        return cadenaConexion;
    }

    public void setCadenaConexion(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }
    //public String driver = "org.gjt.mm.mysql.Driver";
    public String driver = "org.sqlite.JDBC";
    
    //public String usuario = "root";
    public String usuario = "";
    //public String clave = "mysql";
    public String clave = "";
    
    
    Connection conexion = null;

    public Connection getConexion() throws SQLException {
        conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
        return conexion;
    }

    public ConexionC() {
        try {
            Class.forName(driver).newInstance();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    public boolean ejecutarOperacion(String orden) throws SQLException {

        boolean resultado = false;

        try {
            conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
            if (!conexion.isClosed()) {
                Statement sentencia = conexion.createStatement();
                sentencia.execute(orden);
                sentencia.close();
                resultado = true;
            }
        } catch (Exception ex) {

            System.out.print(ex.getMessage());
        } finally {
            conexion.close();
        }
        return resultado;
    }

    public boolean ejecutarOperaciones(ArrayList<String> ordenes) throws SQLException {
        boolean resultado = false;

        try {
            conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
            if (!conexion.isClosed()) {
                conexion.setAutoCommit(false);
                for (String orden : ordenes) {
                    Statement sentencia = conexion.createStatement();
                    sentencia.executeUpdate(orden);
                }
                conexion.commit();
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.print("se hizo rollback");
            System.out.print(ex.getMessage());
        } finally {
            conexion.close();
        }
        return resultado;
    }

    public ArrayList<Vector> ejecutarConsulta(String orden) throws SQLException {
        ResultSet resultado = null;
        ArrayList<Vector> arrayFilas = null;
        try {
            conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
            if (!conexion.isClosed()) {

                Statement sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(orden);

                arrayFilas = new ArrayList<Vector>();

                while (resultado.next()) {
                    Vector fila = new Vector();

                    for (int i = 1; i <= resultado.getMetaData().getColumnCount(); i++) {
                        fila.add(resultado.getObject(i));
                    }
                    arrayFilas.add(fila);
                }

            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        } finally {
            resultado.close();
            conexion.close();
        }
        return arrayFilas;
    }
    
    public int ejecutarConsultaUno(String orden) throws SQLException{
        
        ResultSet resultado = null;
        
        ArrayList<Vector> arrayFilas = null;
        
        int obj =0;
        
        try{
            conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
            if (!conexion.isClosed()) {

                Statement sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(orden);

                arrayFilas = new ArrayList<Vector>();

                while (resultado.next()) {
                    obj = resultado.getInt(1);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        } finally {
            resultado.close();
            conexion.close();
        }
        
        return obj;
        
    }

    public ArrayList<Vector> toArrayList(ResultSet resultado) throws SQLException {
        ArrayList<Vector> arrayFilas = new ArrayList<Vector>();
        while (resultado.next()) {
            Vector fila = new Vector();

            for (int i = 1; i <= resultado.getMetaData().getColumnCount(); i++) {
                fila.add(resultado.getObject(i));
            }
            arrayFilas.add(fila);
        }
        
        return arrayFilas;
    }
}
