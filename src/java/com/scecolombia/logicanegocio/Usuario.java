package com.scecolombia.logicanegocio;


import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.logicanegocio.interfaces.IUsuario;
import com.scecolombia.utilidades.ConexionC;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario implements ICrud, IUsuario {

    int tipoUsuario;
    int idUsuario;
    int activo;
    String nombre;
    String apellido;
    String direccion;
    String telefono;
    String login;
    String clave;

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * *
     *
     * @param obj
     * @return Ultimo id creado -1 si no se creó
     */
    @Override
    public int crear(Object obj) {
        ConexionC con = new ConexionC();
        Usuario usu = (Usuario) obj;
        int ultimoId = -1;

        try {
            String sql = "insert into usuario (nombre,apellido,direccion,telefono,login,clave,activo,tipousuario_id)"
                    + "values('" + usu.getNombre() + "','" + usu.getApellido() + "','" + usu.getDireccion() + "','" + usu.getTelefono() + "',"
                    + "'" + usu.getLogin() + "','" + usu.getClave() + "','" + usu.getActivo() + "'," + usu.getTipoUsuario() + ")";
            if (con.ejecutarOperacion(sql)) {
                String sql2 = "select max(id) from usuario;";
                ultimoId = con.ejecutarConsultaUno(sql2);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ultimoId;
    }

    @Override
    public boolean modificar(Object obj) {
        ConexionC con = new ConexionC();
        Usuario usu = (Usuario) obj;

        boolean respuesta = false;

        try {
            String sql = "update usuario set"
                    + " nombre='" + usu.getNombre() + "',"
                    + " apellido='" + usu.getApellido() + "',"
                    + " direccion='" + usu.getDireccion() + "',"
                    + " telefono='" + usu.getTelefono() + "',"
                    + " clave='" + usu.getClave() + "',"
                    + " activo=" + usu.getActivo() + ","
                    + " tipousuario_id=" + usu.getTipoUsuario() + ""
                    + " where id=" + usu.getIdUsuario() + "";
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
        Usuario usu = (Usuario) obj;

        boolean respuesta = false;

        try {
            String sql = "delete from usuario where id=" + usu.getIdUsuario() + "";

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
        Usuario usu = null;
        ConexionC con = new ConexionC();
        try {
            ArrayList<Vector> res = new ArrayList<Vector>();

            String sql = "select id,nombre,apellido,direccion,telefono,login,clave,activo,tipousuario_id from usuario where id=" + id + "";
            res = con.ejecutarConsulta(sql);

            usu = new Usuario();

            for (Vector r : res) {

                usu.setIdUsuario(Integer.valueOf(r.get(0).toString()));
                usu.setNombre(r.get(1).toString());
                usu.setApellido(r.get(2).toString());
                usu.setDireccion(r.get(3).toString());
                usu.setTelefono(r.get(4).toString());
                usu.setLogin(r.get(5).toString());
                usu.setClave(r.get(6).toString());
                usu.setActivo(Integer.valueOf(r.get(7).toString()));
                usu.setTipoUsuario(Integer.valueOf(r.get(8).toString()));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return usu;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        Usuario usu = null;
        ConexionC con = new ConexionC();
        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();

            String sql = "select id,nombre,apellido,direccion,telefono,login,clave,activo,tipousuario_id from usuario";
            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();
                for (Vector r : res) {


                    usu = new Usuario();
                    usu.setIdUsuario(Integer.valueOf(r.get(0).toString()));
                    usu.setNombre(r.get(1).toString());
                    usu.setApellido(r.get(2).toString());
                    usu.setDireccion(r.get(3).toString());
                    usu.setTelefono(r.get(4).toString());
                    usu.setLogin(r.get(5).toString());
                    usu.setClave(r.get(6).toString());
                    usu.setActivo(Integer.valueOf(r.get(7).toString()));
                    usu.setTipoUsuario(Integer.valueOf(r.get(8).toString()));

                    lista.add(usu);

                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return (ArrayList<Object>) lista;
    }

    @Override
    public ArrayList<Object> consultarLike(String parte) {
        // TODO Auto-generated method stub
        Usuario usu = null;
        ConexionC con = new ConexionC();
        ArrayList<Object> lista = null;

        try {
            ArrayList<Vector> res = new ArrayList<Vector>();

            String sql = "select id,nombre,apellido,direccion,telefono,login,clave,activo,tipousuario_id from usuario where apellido like '%" + parte + "%'";
            res = con.ejecutarConsulta(sql);

            if (res.size() > 0) {

                lista = new ArrayList<Object>();
                for (Vector r : res) {


                    usu = new Usuario();
                    usu.setIdUsuario(Integer.valueOf(r.get(0).toString()));
                    usu.setNombre(r.get(1).toString());
                    usu.setApellido(r.get(2).toString());
                    usu.setDireccion(r.get(3).toString());
                    usu.setTelefono(r.get(4).toString());
                    usu.setLogin(r.get(5).toString());
                    usu.setClave(r.get(6).toString());
                    usu.setActivo(Integer.valueOf(r.get(7).toString()));
                    usu.setTipoUsuario(Integer.valueOf(r.get(8).toString()));

                    lista.add(usu);

                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return (ArrayList<Object>) lista;
    }

    /**
     * *
     *
     * @param nombre
     * @param codigo
     * @return Devuelve el id del usuario consultado
     */
    @Override
    public int existe(String nombre, String codigo) {

        int contador = 0;
        ConexionC con = new ConexionC();
        String sql = "select id from usuario where login='" + nombre + "' and clave='" + codigo + "'";
        try {
            contador = con.ejecutarConsultaUno(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contador;
    }
}
