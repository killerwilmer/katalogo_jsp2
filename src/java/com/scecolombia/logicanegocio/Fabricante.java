package com.scecolombia.logicanegocio;

import java.util.List;

import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.utilidades.ConexionC;
import java.util.ArrayList;
import java.util.Vector;

public class Fabricante implements ICrud {

    int id;
    String nombre;
    int activo;
    String fechaActualizacion;

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaactualizacion(String fechaactualizacion) {
        this.fechaActualizacion = fechaactualizacion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    @Override
    public int crear(Object obj) {
        // TODO Auto-generated method stub
        ConexionC con = new ConexionC();
        Fabricante cat = (Fabricante) obj;
        int ultimoId = -1;

        try {
            String sql = "insert into fabricante (nombre,activo,fechaactualizacion)"
                    + " values('" + cat.getNombre() + "'," + cat.getActivo() + ")";
            if (con.ejecutarOperacion(sql)) {
                String sql2 = "select max(id) from fabricante;";
                ultimoId = con.ejecutarConsultaUno(sql2);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ultimoId;
       
    }

    @Override
    public boolean modificar(Object obj) {
        // TODO Auto-generated method stub
         ConexionC con = new ConexionC();
        Fabricante cat = (Fabricante) obj;

        boolean respuesta = false;

        try {
            String sql = "update fabricante set"
                    + " nombre='" + cat.getNombre() + "',"
                    + " activo=" + cat.getActivo() + ","
                    + " fechaactualizacion='"+cat.getFechaActualizacion()+"'"
                    + " where id=" + cat.getId() + "";
            if (con.ejecutarOperacion(sql)) {
                respuesta = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return respuesta;
        
    }

    @Override
    public boolean eliminar(Object obj) {
        // TODO Auto-generated method stub
        
        ConexionC con = new ConexionC();
        Fabricante cat = (Fabricante) obj;

        boolean respuesta = false;

        try {
            String sql = "delete from fabricante where id=" + cat.getId() + "";

            if (con.ejecutarOperacion(sql)) {
                respuesta = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return respuesta;
        
    }

    @Override
    public Object consultarUno(int id) {
        // TODO Auto-generated method stub
        Fabricante cat = null;
        ConexionC con = new ConexionC();

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,activo,fechaactualizacion from fabricante where id=" + id + "";

            res = con.ejecutarConsulta(sql);

            cat = new Fabricante();

            for (Vector v : res) {
                cat.setId(Integer.valueOf(v.get(0).toString()));
                cat.setNombre(v.get(1).toString());
                cat.setActivo(Integer.valueOf(v.get(2).toString()));
                cat.setFechaactualizacion(v.get(3).toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cat;
        
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        Fabricante cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,activo,fechaactualizacion from fabricante";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();
                

                for (Vector v : res) {
                    cat = new Fabricante();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setActivo(Integer.valueOf(v.get(2).toString()));
                    cat.setFechaactualizacion(v.get(3).toString());
                    lista.add(cat);
                }
                
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Object> consultarLike(String parte) {
        Fabricante cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,activo,fechaactualizacion from fabricante where nombre like '%"+parte+"%'";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();

                for (Vector v : res) {
                    cat = new Fabricante();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setActivo(Integer.valueOf(v.get(2).toString()));
                    cat.setFechaactualizacion(v.get(3).toString());
                    lista.add(cat);
                }
                
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
