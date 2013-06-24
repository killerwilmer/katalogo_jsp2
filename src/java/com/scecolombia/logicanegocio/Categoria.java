package com.scecolombia.logicanegocio;


import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.utilidades.ConexionC;
import java.util.ArrayList;
import java.util.Vector;

public class Categoria implements ICrud {

    int id;
    String nombre;
    int activo;
    int idFabricante;
    String fechaActualizacion;   
    String tipoActualizacion;

    public String getTipoActualizacion() {
        return tipoActualizacion;
    }

    public void setTipoActualizacion(String tipoActualizacion) {
        this.tipoActualizacion = tipoActualizacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
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
        ConexionC con = new ConexionC();
        Categoria cat = (Categoria) obj;
        int ultimoId = -1;

        try {
            String sql = "insert into categoria (nombre,activo,fabricante_id,fechaactualizacion)"
                    + " values('" + cat.getNombre() + "'," + cat.getActivo() + ","+cat.getIdFabricante()+",'"+cat.getFechaActualizacion()+"')";
            if (con.ejecutarOperacion(sql)) {
                String sql2 = "select max(id) from categoria;";
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
        Categoria cat = (Categoria) obj;

        boolean respuesta = false;

        try {
            String sql = "update categoria set"
                    + " nombre='" + cat.getNombre() + "',"
                    + " activo=" + cat.getActivo() + ","
                    + " fabricante_id="+cat.getIdFabricante()+","
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
        Categoria cat = (Categoria) obj;

        boolean respuesta = false;

        try {
            String sql = "delete from categoria where id=" + cat.getId() + "";

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

        Categoria cat = null;
        ConexionC con = new ConexionC();

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,activo,fabricante_id,fechaactualizacion from Categoria where id=" + id + "";

            res = con.ejecutarConsulta(sql);

            cat = new Categoria();

            for (Vector v : res) {
                cat.setId(Integer.valueOf(v.get(0).toString()));
                cat.setNombre(v.get(1).toString());
                cat.setActivo(Integer.valueOf(v.get(2).toString()));
                cat.setIdFabricante(Integer.valueOf(v.get(3).toString()));
                cat.setFechaActualizacion(v.get(4).toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cat;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        Categoria cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,activo,fabricante_id,fechaactualizacion from Categoria";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();

                for (Vector v : res) {
                    cat = new Categoria();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setActivo(Integer.valueOf(v.get(2).toString()));
                    cat.setIdFabricante(Integer.valueOf(v.get(3).toString()));
                    cat.setFechaActualizacion(v.get(4).toString());
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
        Categoria cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,activo,fabricante_id,fechaactualizacion from Categoria where nombre like '%"+parte+"%'";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();
                

                for (Vector v : res) {
                    cat = new Categoria();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setActivo(Integer.valueOf(v.get(2).toString()));
                    cat.setIdFabricante(Integer.valueOf(v.get(3).toString()));
                    cat.setFechaActualizacion(v.get(4).toString());
                    lista.add(cat);
                }
                
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    /***
     * 
     * @param idMayor se supone que los nuevos van a ser los que son mayores 
     * o iguales a este id en la tabla.
     * @return 
     */
     public ArrayList<Categoria> consultarNuevos(int idMayor) {
        Categoria cat = null;
        ConexionC con = new ConexionC();

        ArrayList<Categoria> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();
            String sql = "select id,nombre,activo,fabricante_id,fechaactualizacion from Categoria where id>="+idMayor+"";

            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Categoria>();
                

                for (Vector v : res) {
                    cat = new Categoria();
                    cat.setId(Integer.valueOf(v.get(0).toString()));
                    cat.setNombre(v.get(1).toString());
                    cat.setActivo(Integer.valueOf(v.get(2).toString()));
                    cat.setIdFabricante(Integer.valueOf(v.get(3).toString()));
                    cat.setFechaActualizacion(v.get(4).toString());
                    cat.setTipoActualizacion("i");
                    lista.add(cat);
                }
                
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }        
            
}