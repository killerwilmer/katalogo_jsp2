package com.scecolombia.logicanegocio;

import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.logicanegocio.interfaces.IProducto;
import com.scecolombia.utilidades.ConexionC;
import com.scecolombia.utilidades.Fecha;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Producto implements ICrud, IProducto {

    int id;
    String nombre;
    double precio1;
    double precio2;
    double precio3;
    double impuesto1;
    double impuesto2;
    String codigo;
    int categoria_id;
    String linkvideo;
    String fechaactualizacion;
    boolean nuevo;
    int inventario;
    int ordencategoria;
    int ordenfabricante;
    String observaciones;
    String edadlucro;
    String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEdadlucro() {
        return edadlucro;
    }

    public void setEdadlucro(String edadlucro) {
        this.edadlucro = edadlucro;
    }

    public String getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(String fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getImpuesto1() {
        return impuesto1;
    }

    public void setImpuesto1(double impuesto1) {
        this.impuesto1 = impuesto1;
    }

    public double getImpuesto2() {
        return impuesto2;
    }

    public void setImpuesto2(double impuesto2) {
        this.impuesto2 = impuesto2;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getOrdencategoria() {
        return ordencategoria;
    }

    public void setOrdencategoria(int ordencategoria) {
        this.ordencategoria = ordencategoria;
    }

    public int getOrdenfabricante() {
        return ordenfabricante;
    }

    public void setOrdenfabricante(int ordenfabricante) {
        this.ordenfabricante = ordenfabricante;
    }

    public double getPrecio1() {
        return precio1;
    }

    public void setPrecio1(double precio1) {
        this.precio1 = precio1;
    }

    public double getPrecio2() {
        return precio2;
    }

    public void setPrecio2(double precio2) {
        this.precio2 = precio2;
    }

    public double getPrecio3() {
        return precio3;
    }

    public void setPrecio3(double precio3) {
        this.precio3 = precio3;
    }

    @Override
    public int crear(Object obj) {
        // TODO Auto-generated method stub
        ConexionC con = new ConexionC();
        Producto prod = (Producto) obj;
        int ultimoId = -1;

        try {
            String sql = "insert into producto (nombre,precio1,precio2,precio3,impuesto1,impuesto2,"
                    + "codigo,categoria_id,linkvideo,fechaactualizacion,nuevo,inventario,ordencategoria,"
                    + "ordenfabricante,observaciones,edadlucro)"
                    + " values('" + prod.getNombre() + "'," + prod.getPrecio1() + "," + prod.getPrecio2() + ""
                    + "" + prod.getPrecio3() + "," + prod.getImpuesto1() + "," + prod.getImpuesto2() + "," + prod.getCodigo() + ""
                    + "" + prod.getCategoria_id() + "," + prod.getLinkvideo() + ",'" + prod.getFechaactualizacion() + "'," + prod.isNuevo() + ""
                    + "" + prod.getInventario() + "," + prod.getOrdencategoria() + "," + prod.getOrdenfabricante() + ",'" + prod.getObservaciones() + "',"
                    + "'" + prod.getEdadlucro() + "'" + ")";
            if (con.ejecutarOperacion(sql)) {
                String sql2 = "select max(id) from producto;";
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
        Producto prod = (Producto) obj;

        boolean respuesta = false;

        try {
            String sql = "update producto set"
                    + " nombre='" + prod.getNombre() + "',"
                    + " precio1=" + prod.getPrecio1() + ","
                    + " precio2=" + prod.getPrecio2() + ","
                    + " precio3=" + prod.getPrecio3() + ","
                    + " impuesto1=" + prod.getImpuesto1() + ","
                    + " impuesto2=" + prod.getImpuesto2() + ","
                    + " codigo='" + prod.getCodigo() + "',"
                    + " categoria=" + prod.getCategoria_id() + ","
                    + " linkvideo='" + prod.getLinkvideo() + "',"
                    + " fechaactualizacion='" + prod.getFechaactualizacion() + "',"
                    + " nuevo=" + prod.isNuevo() + ","
                    + " inventario=" + prod.getInventario() + ","
                    + " ordencategoria=" + prod.getOrdencategoria() + ","
                    + " ordenfabricante=" + prod.getOrdenfabricante() + ","
                    + " observaciones='" + prod.getObservaciones() + "',"
                    + " edadlucro='" + prod.getEdadlucro() + "'"
                    + " where id=" + prod.getId() + "";
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
        ConexionC con = new ConexionC();
        Producto cat = (Producto) obj;

        boolean respuesta = false;

        try {
            String sql = "delete from producto where id=" + cat.getId() + "";

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
        Producto cat = null;
        ConexionC con = new ConexionC();

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,precio1,precio2,precio3,impuesto1,"
                    + " impuesto2,codigo,categoria_id,linkvideo,fechaactualizacion,"
                    + " nuevo,inventario, ordencategoria, ordenfabricante, observaciones,edadlucro"
                    + " from Producto where id=" + id + "";

            res = con.ejecutarConsulta(sql);

            cat = new Producto();

            for (Vector v : res) {
                cat.setId(Integer.valueOf(v.get(0).toString()));
                cat.setNombre(v.get(1).toString());
                cat.setPrecio1(Double.valueOf(v.get(2).toString()));
                cat.setPrecio2(Double.valueOf(v.get(3).toString()));
                cat.setPrecio3(Double.valueOf(v.get(4).toString()));
                cat.setImpuesto1(Double.valueOf(v.get(5).toString()));
                cat.setImpuesto2(Double.valueOf(v.get(6).toString()));
                cat.setCodigo(v.get(7).toString());
                cat.setCategoria_id(Integer.valueOf(v.get(8).toString()));
                cat.setLinkvideo(v.get(9).toString());
                cat.setFechaactualizacion(v.get(10).toString());
                cat.setNuevo(Boolean.valueOf(v.get(11).toString()));
                cat.setInventario(Integer.valueOf(v.get(12).toString()));
                cat.setOrdencategoria(Integer.valueOf(v.get(13).toString()));
                cat.setOrdenfabricante(Integer.valueOf(v.get(14).toString()));
                cat.setObservaciones(v.get(15).toString());
                cat.setEdadlucro(v.get(16).toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cat;

    }

    @Override
    public boolean asociarImagen(String idImagen, String idProducto) {
        // TODO Auto-generated method stub
        ConexionC con = new ConexionC();
        boolean res = false;

        try {
            java.util.Date fecha = new Date();
            String miFecha = String.valueOf(fecha.getTime());

            String sql = "insert into imagenproducto (imagen_id,producto_id,activo,fechaactualizacion) values('" + idImagen + "','" + idProducto + "',1,'" + miFecha +"')";
            res = con.ejecutarOperacion(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return res;
    }

    @Override
    public ArrayList<Imagen> consultarImagenes(int idProducto) {
        // TODO Auto-generated method stub

        ConexionC con = new ConexionC();

        ArrayList<Imagen> lista = null;

        try {
            String sql = "select i.id,i.categoria_id,i.imagen from producto p,imagen i,imagenproducto ip where"
                    + " p.id=ip.producto_id and i.id=ip.imagen_id "
                    + " and ip.producto_id=" + idProducto + "";

            ArrayList<Vector> res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                for (Vector v : res) {
                    Imagen img = new Imagen();
                    img.setId(Integer.valueOf(v.get(0).toString()));
                    img.setIdCategoria(Integer.valueOf(v.get(1).toString()));
                    img.setImagen((byte[]) v.get(id));
                    lista.add(img);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Producto> consultarPorCategoria(int idCategoria) {

        Producto cat = null;
        ConexionC con = new ConexionC();
        ArrayList<Producto> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,precio1,precio2,precio3,impuesto1,"
                    + " impuesto2,codigo,categoria_id,linkvideo,fechaactualizacion,"
                    + " nuevo,inventario, ordencategoria, ordenfabricante, observaciones,edadlucro"
                    + " from producto where categoria_id=" + idCategoria + "";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Producto>();

                for (Vector v : res) {
                    cat = new Producto();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setPrecio1(Double.valueOf(v.get(2).toString()));
                    cat.setPrecio2(Double.valueOf(v.get(3).toString()));
                    cat.setPrecio3(Double.valueOf(v.get(4).toString()));
                    cat.setImpuesto1(Double.valueOf(v.get(5).toString()));
                    cat.setImpuesto2(Double.valueOf(v.get(6).toString()));
                    cat.setCodigo(v.get(7).toString());
                    cat.setCategoria_id(Integer.valueOf(v.get(8).toString()));
                    cat.setLinkvideo(v.get(9).toString());
                    cat.setFechaactualizacion(v.get(10).toString());
                    cat.setNuevo(Boolean.valueOf(v.get(11).toString()));
                    cat.setInventario(Integer.valueOf(v.get(12).toString()));
                    cat.setOrdencategoria(Integer.valueOf(v.get(13).toString()));
                    cat.setOrdenfabricante(Integer.valueOf(v.get(14).toString()));
                    cat.setObservaciones(v.get(15).toString());
                    cat.setEdadlucro(v.get(16).toString());
                    lista.add(cat);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        Producto cat = null;
        ConexionC con = new ConexionC();
        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,precio1,precio2,precio3,impuesto1,"
                    + " impuesto2,codigo,categoria_id,linkvideo,fechaactualizacion,"
                    + " nuevo,inventario, ordencategoria, ordenfabricante, observaciones,edadlucro"
                    + " from Producto";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();



                for (Vector v : res) {
                    cat = new Producto();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setPrecio1(Double.valueOf(v.get(2).toString()));
                    cat.setPrecio2(Double.valueOf(v.get(3).toString()));
                    cat.setPrecio3(Double.valueOf(v.get(4).toString()));
                    cat.setImpuesto1(Double.valueOf(v.get(5).toString()));
                    cat.setImpuesto2(Double.valueOf(v.get(6).toString()));
                    cat.setCodigo(v.get(7).toString());
                    cat.setCategoria_id(Integer.valueOf(v.get(8).toString()));
                    cat.setLinkvideo(v.get(9).toString());
                    cat.setFechaactualizacion(v.get(10).toString());
                    cat.setNuevo(Boolean.valueOf(v.get(11).toString()));
                    cat.setInventario(Integer.valueOf(v.get(12).toString()));
                    cat.setOrdencategoria(Integer.valueOf(v.get(13).toString()));
                    cat.setOrdenfabricante(Integer.valueOf(v.get(14).toString()));
                    cat.setObservaciones(v.get(15).toString());
                    cat.setEdadlucro(v.get(16).toString());
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
        Producto cat = null;
        ConexionC con = new ConexionC();
        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,precio1,precio2,precio3,impuesto1,"
                    + " impuesto2,codigo,categoria_id,linkvideo,fechaactualizacion,"
                    + " nuevo,inventario, ordencategoria, ordenfabricante, observaciones,edadlucro"
                    + " from Producto where nombre like '%" + parte + "%';";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();



                for (Vector v : res) {
                    cat = new Producto();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setPrecio1(Double.valueOf(v.get(2).toString()));
                    cat.setPrecio2(Double.valueOf(v.get(3).toString()));
                    cat.setPrecio3(Double.valueOf(v.get(4).toString()));
                    cat.setImpuesto1(Double.valueOf(v.get(5).toString()));
                    cat.setImpuesto2(Double.valueOf(v.get(6).toString()));
                    cat.setCodigo(v.get(7).toString());
                    cat.setCategoria_id(Integer.valueOf(v.get(8).toString()));
                    cat.setLinkvideo(v.get(9).toString());
                    cat.setFechaactualizacion(v.get(10).toString());
                    cat.setNuevo(Boolean.valueOf(v.get(11).toString()));
                    cat.setInventario(Integer.valueOf(v.get(12).toString()));
                    cat.setOrdencategoria(Integer.valueOf(v.get(13).toString()));
                    cat.setOrdenfabricante(Integer.valueOf(v.get(14).toString()));
                    cat.setObservaciones(v.get(15).toString());
                    cat.setEdadlucro(v.get(16).toString());
                    lista.add(cat);
                }

            }



        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Producto> consultarPorMarca(String nombreMarca) {
        Producto cat = null;
        ConexionC con = new ConexionC();
        ArrayList<Producto> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select idproducto,descripcion from misproductos where marca ='" + nombreMarca + "';";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Producto>();

                for (Vector v : res) {
                    cat = new Producto();
                    cat.setCodigo(v.get(0).toString());
                    cat.setNombre(v.get(1).toString());
                    lista.add(cat);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Producto> consultarMarcas() {
        Producto cat = null;
        ConexionC con = new ConexionC();
        ArrayList<Producto> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select DISTINCT marca from misproductos";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Producto>();

                for (Vector v : res) {
                    cat = new Producto();
                    cat.setMarca(v.get(0).toString());
                    lista.add(cat);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
