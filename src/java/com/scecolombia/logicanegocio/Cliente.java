/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.logicanegocio;

import com.scecolombia.logicanegocio.interfaces.ICliente;
import com.scecolombia.logicanegocio.interfaces.ICrud;
import com.scecolombia.utilidades.ConexionC;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author skynet
 */
public class Cliente implements ICrud, ICliente {

    int idCliente;
    String nombre;
    String direccion;
    String telefono1;
    String nit;
    String telefono2;
    String fechaActulizacion;
    String nomTienda;
    String sucursal;

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFechaActulizacion() {
        return fechaActulizacion;
    }

    public void setFechaActulizacion(String fechaActulizacion) {
        this.fechaActulizacion = fechaActulizacion;
    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }

    @Override
    public int crear(Object obj) {
        ConexionC con = new ConexionC();
        Cliente cli = (Cliente) obj;
        int ultimoId = -1;

        try {
            String sql = "insert into cliente (nombre,direccion,telefono1,nit,telefono2,fechaactualizacion,nomtienda) values('" + cli.getNombre() + "','" + cli.getDireccion() + "','" + cli.getTelefono1() + "','" + cli.getNit()
                    + "','" + cli.getTelefono2() + "','" + cli.getFechaActulizacion() +"','" + cli.getNomTienda() + "')";
            if (con.ejecutarOperacion(sql)) {
                String sql2 = "select max(id) from usuario;";
                ultimoId = con.ejecutarConsultaUno(sql2);                
            }
            //System.out.println(sql);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ultimoId;
    }

    @Override
    public boolean modificar(Object obj) {
        ConexionC con = new ConexionC();
        Cliente cli = (Cliente) obj;

        boolean respuesta = false;

        try {
            String sql = "update cliente set"
                    + " nombre='" + cli.getNombre() + "',"
                    + " direccion='" + cli.getDireccion() + "',"
                    + " telefono1='" + cli.getTelefono1() + "',"
                    + " nit='" + cli.getNit() + "',"
                    + " telefono2='" + cli.getTelefono2() + "',"
                    + " fechaactualizacion='" + cli.getFechaActulizacion() + "',"
                    + " nomtienda='," + cli.getNomTienda() + "',"
                    + " sucursal='" + cli.getSucursal() + "'"
                    + " where id=" + cli.getIdCliente() + "";
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object consultarUno(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Object> consultarLike(String parte) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean modificarPorNit(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object consultarUnoPorNit(String nit) {
        Cliente cli = null;
        ConexionC con = new ConexionC();
        try {
            ArrayList<Vector> res = new ArrayList<Vector>();

            String sql = "select id,nombre,direccion,telefono1,nit,telefono2,fechaactualizacion,nomtienda from cliente where nit='" + nit + "'";
            res = con.ejecutarConsulta(sql);

            cli = new Cliente();

            for (Vector r : res) {

                cli.setIdCliente(Integer.valueOf(r.get(0).toString()));
                cli.setNombre(r.get(1).toString());
                cli.setDireccion(r.get(2).toString());
                cli.setTelefono1(r.get(3).toString());
                cli.setNit(r.get(4).toString());
                cli.setTelefono2(r.get(5).toString());
                cli.setFechaActulizacion(r.get(6).toString());
                cli.setNomTienda(r.get(7).toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return cli;
    }
}