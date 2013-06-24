package com.scecolombia.logicanegocio;

import java.util.List;

import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.utilidades.ConexionC;
import java.util.ArrayList;
import java.util.Vector;

public class TipoUsuario implements ICrud {

    int id;
    String nombre;
    int activo;
    String fechaactualizacion;

    public String getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(String fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
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
        return 0;
    }

    @Override
    public boolean modificar(Object obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean eliminar(Object obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object consultarUno(int id) {
        // TODO Auto-generated method stub
        TipoUsuario usu = null;
        ConexionC con = new ConexionC();
        try {
            ArrayList<Vector> res = new ArrayList<Vector>();

            String sql = "select id,nombre,activo,fechaactualizacion from tipousuario where id=" + id + "";
            res = con.ejecutarConsulta(sql);

            usu = new TipoUsuario();

            for (Vector v : res) {

                usu.setId(Integer.valueOf(v.get(0).toString()));
                usu.setNombre(v.get(1).toString());
                usu.setActivo(Integer.valueOf(v.get(2).toString()));
                usu.setFechaactualizacion(v.get(3).toString());

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return usu;
        
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Object> consultarLike(String parte) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
