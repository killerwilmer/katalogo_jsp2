/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.logicanegocio;

import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.utilidades.ConexionC;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author mildred
 */
public class ImagenProducto implements ICrud {

    int id;
    int activo;
    String imagenId;
    String productoId;
    String fechaActualizacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public int crear(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean modificar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object consultarUno(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ImagenProducto img = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "SELECT id,activo,imagen_id,producto_id,fechaactualizacion FROM imagenproducto";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();

                for (Vector v : res) {
                    img = new ImagenProducto();
                    img.setId(Integer.valueOf(v.get(0).toString()));
                    img.setActivo(Integer.valueOf(v.get(1).toString()));
                    img.setImagenId(v.get(2).toString());
                    img.setProductoId(v.get(3).toString());
                    img.setFechaActualizacion(v.get(4).toString());
                    lista.add(img);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Object> consultarLike(String parte) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
