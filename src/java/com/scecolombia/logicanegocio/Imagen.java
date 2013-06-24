package com.scecolombia.logicanegocio;

import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.logicanegocio.interfaces.IImagen;
import com.scecolombia.utilidades.ConexionC;
import com.scecolombia.utilidades.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Vector;

public class Imagen implements ICrud, IImagen {

    int id;
    byte[] imagen;
    File imagenArchivo;
    int idCategoria;
    String fechaActualizacion;
    String tipoActulaizacion;

    public String getTipoActulaizacion() {
        return tipoActulaizacion;
    }

    public void setTipoActulaizacion(String tipoActulaizacion) {
        this.tipoActulaizacion = tipoActulaizacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaactualizacion) {
        this.fechaActualizacion = fechaactualizacion;
    }

    public File getImagenArchivo() {
        return imagenArchivo;
    }

    public void setImagenArchivo(File imagenArchivo) {
        this.imagenArchivo = imagenArchivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public int crear(Object obj) {
        ConexionC con = new ConexionC();
        Imagen img = (Imagen) obj;
        int ultimoId = -1;

        try {
            String sql = "insert into imagen (categoria_id,fechaactualizacion)"
                    + " values( " + img.getIdCategoria() + ",'" + img.getFechaActualizacion() + "')";
            if (con.ejecutarOperacion(sql)) {
                String sql2 = "select max(id) from imagen";
                ultimoId = con.ejecutarConsultaUno(sql2);

                //actualizar con la imagen que trae
                String orden = "update imagen set imagen=? where id=" + ultimoId + "";
                PreparedStatement s = (PreparedStatement) con.getConexion().prepareStatement(orden);


                FileInputStream fis = new FileInputStream(img.getImagenArchivo());
                //s.setBinaryStream(1, fis, (int) img.getImagenArchivo().length());

                s.setBytes(1, img.getImagen());

                int executeUpdate = s.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ultimoId;
    }

    //afecta la tabla imagen de aux.db 
    //que sera descargada del android para actualizar las imagenes
    public int crearParaActualizarEnAndroid(ArrayList<Imagen> lista) {

        ConexionC con = new ConexionC();
        //se cambia la ruta para aux.db
        con.setCadenaConexion(Constantes.RUTA_DB_IMAGENES);

        int ultimoId = -1;

        try {

            con.ejecutarOperacion("delete from imagen");

            for (int i = 0; i < lista.size(); i++) {

                Imagen img = lista.get(i);
                
                String sql = "insert into imagen (id,categoria_id,fechaactualizacion,tipoactualizacion)"
                        + " values( "+img.getId()+"," + img.getIdCategoria() + ",'" + img.getFechaActualizacion() + "','" + img.getTipoActulaizacion() + "')";

                if (con.ejecutarOperacion(sql)) {
                    //String sql2 = "select max(id) from imagen";
                    
                    //ultimoId = con.ejecutarConsultaUno(sql2);

                    //actualizar con la imagen que trae
                    String orden = "update imagen set imagen=? where id=" + img.getId() + "";
                    PreparedStatement s = (PreparedStatement) con.getConexion().prepareStatement(orden);


                    //FileInputStream fis = new FileInputStream(img.getImagenArchivo());
                    //s.setBinaryStream(1, fis, (int) img.getImagenArchivo().length());

                    s.setBytes(1, img.getImagen());

                    int executeUpdate = s.executeUpdate();
                }


            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ultimoId;

    }

    @Override
    public boolean modificar(Object obj) {
        ConexionC con = new ConexionC();
        Imagen cat = (Imagen) obj;

        boolean respuesta = false;

        try {
            String sql = "update imagen set"
                    + " categoria_id=" + cat.getIdCategoria() + ","
                    + " fechaactualizacion='" + cat.getFechaActualizacion() + "'"
                    + " where id=" + cat.getId() + "";
            if (con.ejecutarOperacion(sql)) {

                //actualizar con la imagen que trae
                String orden = "update imagen set imagen=? where id=" + cat.getId() + "";



                PreparedStatement s = (PreparedStatement) con.getConexion().prepareStatement(orden);
                FileInputStream fis = new FileInputStream(cat.getImagenArchivo());
                s.setBinaryStream(1, fis, (int) cat.getImagenArchivo().length());
                int executeUpdate = s.executeUpdate();

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
        Imagen cat = (Imagen) obj;

        boolean respuesta = false;

        try {
            String sql = "delete from imagen where id=" + cat.getId() + "";

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
        Imagen cat = null;
        ConexionC con = new ConexionC();

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,imagen,categoria_id,fechaactualizacion from imagen where id=" + id + "";

            res = con.ejecutarConsulta(sql);

            cat = new Imagen();

            for (Vector v : res) {
                cat.setId(Integer.valueOf(v.get(0).toString()));
                cat.setImagen((byte[]) v.get(1));
                cat.setIdCategoria(Integer.valueOf(v.get(2).toString()));
                cat.setFechaActualizacion(v.get(3).toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cat;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        Imagen cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,imagen,categoria_id,fechaactualizacion from imagen";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();



                for (Vector v : res) {
                    cat = new Imagen();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setImagen((byte[]) v.get(1));
                    cat.setIdCategoria(Integer.valueOf(v.get(2).toString()));
                    cat.setFechaActualizacion(v.get(3).toString());
                    lista.add(cat);
                }


            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    /**
     * *
     * no lo veo necesario
     *
     * @param parte
     * @return
     */
    @Override
    public ArrayList<Object> consultarLike(String parte) {
        return null;
    }

    /**
     * *
     *
     * @param idImagen
     * @return Devuelve una lista de productos
     */
    @Override
    public ArrayList<Object> consultarProductosAsociados(int idImagen) {
        Producto prod = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();

            String sql = "select "
                    + " p.id,p.nombre,p.precio1,p.precio2,p.precio3,p.impuesto1,"
                    + " p.impuesto2,p.codigo,p.categoria_id,p.linkvideo,p.fechaactualizacion,"
                    + " p.nuevo,p.inventario,p.ordencategoria,p.ordenfabricante,p.observaciones"
                    + " p.edadlucro"
                    + " from imagen i,imagenproducto ip,producto p "
                    + " where i.id=ip.imagen_id and p.id=ip.producto_id"
                    + " and ip.imagen_id=" + idImagen + "";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();

                for (Vector v : res) {

                    prod = new Producto();
                    prod.setId(Integer.valueOf(v.get(0).toString()));
                    prod.setNombre(v.get(1).toString());
                    prod.setPrecio1(Double.valueOf(v.get(2).toString()));
                    prod.setPrecio2(Double.valueOf(v.get(3).toString()));
                    prod.setPrecio3(Double.valueOf(v.get(4).toString()));
                    prod.setImpuesto1(Double.valueOf(v.get(5).toString()));
                    prod.setImpuesto2(Double.valueOf(v.get(6).toString()));
                    prod.setCodigo(v.get(7).toString());
                    prod.setCategoria_id(Integer.valueOf(v.get(8).toString()));
                    prod.setLinkvideo(v.get(9).toString());
                    prod.setFechaactualizacion(v.get(10).toString());
                    prod.setNuevo(Boolean.valueOf(v.get(11).toString()));
                    prod.setInventario(Integer.valueOf(v.get(12).toString()));
                    prod.setOrdencategoria(Integer.valueOf(v.get(13).toString()));
                    prod.setOrdenfabricante(Integer.valueOf(v.get(14).toString()));
                    prod.setObservaciones(String.valueOf(v.get(15).toString()));
                    prod.setEdadlucro(v.get(16).toString());

                    lista.add(prod);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Object> consultarPorFabricante(int idFabricante) {
        Imagen cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,imagen,categoria_id from imagen i,categoria c "
                    + " where c.fabricante_id=" + idFabricante + ""
                    + " and i.categoria_id=c.id";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();

                for (Vector v : res) {
                    cat = new Imagen();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setImagen((byte[]) v.get(1));
                    cat.setIdCategoria(Integer.valueOf(v.get(2).toString()));
                    lista.add(cat);
                }


            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Object> consultarPorCategoria(int idCategoria) {
        Imagen cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,imagen,categoria_id,fechaactualizacion from imagen where categoria_id=" + idCategoria + "";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();


                for (Vector v : res) {
                    cat = new Imagen();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setImagen((byte[]) v.get(1));
                    cat.setIdCategoria(Integer.valueOf(v.get(2).toString()));
                    cat.setFechaActualizacion(v.get(3).toString());
                    lista.add(cat);
                }


            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    /**
     * *
     *
     * @param idMayor se supone que los nuevos van a ser los que son mayores o
     * iguales a este id en la tabla.
     * @return
     */
    public ArrayList<Imagen> consultarNuevos(int idMayor) {
        Imagen img = null;
        ConexionC con = new ConexionC();

        ArrayList<Imagen> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,imagen,categoria_id,fechaactualizacion from imagen where id>=" + idMayor + "";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Imagen>();

                for (Vector v : res) {
                    img = new Imagen();
                    img.setId(Integer.valueOf(v.get(0).toString()));
                    img.setImagen((byte[]) v.get(1));
                    img.setIdCategoria(Integer.valueOf(v.get(2).toString()));
                    img.setFechaActualizacion(v.get(3).toString());
                    img.setTipoActulaizacion("i");
                    lista.add(img);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
