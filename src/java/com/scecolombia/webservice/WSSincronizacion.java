/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.webservice;

import com.scecolombia.logicanegocio.Categoria;
import com.scecolombia.logicanegocio.Imagen;
import com.scecolombia.logicanegocio.ImagenProducto;
import com.scecolombia.logicanegocio.Producto;
import com.scecolombia.logicanegocio.Usuario;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author equipo1
 */
@WebService(serviceName = "WSSincronizacion")
public class WSSincronizacion {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Consulta los productos
     */
    @WebMethod(operationName = "sincronizarProductos")
    public ArrayList<Producto> sincronizarProductos(@WebParam(name = "idCategoria") int idCategoria) {

        ArrayList<Producto> lista = null;

        try {
            Producto prod = new Producto();
            lista = new ArrayList<Producto>();
            lista = prod.consultarPorCategoria(idCategoria);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return lista;
    }

    /**
     * Sincronizar Imagenes
     *
     * @param idsImagenes
     * @param fechasCategorias
     * @return ArrayList<Imagen>
     */
    @WebMethod(operationName = "sincronizarImagenes")
    public int sincronizarImagenes(@WebParam(name = "idsImagenes") String[] idsImagenes, @WebParam(name = "fechasCategorias") String[] fechasCategorias) {
        //listaLocal de Categorias         
        //listaLocal de Categorias         
        ArrayList<Imagen> listaImagenesAndroid = null;
        ArrayList<Object> listaLocal = null;
        ArrayList<Imagen> listaImagenesActualizar = null;

        try {
            listaImagenesAndroid = new ArrayList<Imagen>();
            // Creamos la lista de imagenes de Android
            for (int i = 0; i < idsImagenes.length; i++) {
                Imagen miImagen = new Imagen();
                miImagen.setId(Integer.parseInt(idsImagenes[i].toString()));
                miImagen.setFechaActualizacion(fechasCategorias[i].toString());
                listaImagenesAndroid.add(miImagen);
            }
            // Lista de Imagenes del Server
            Imagen imagenLocal = new Imagen();
            listaLocal = new ArrayList<Object>();
            listaLocal = imagenLocal.consultarTodos();

            if (listaLocal.size() > 0) {
                if (listaImagenesAndroid.size() > 0) {

                    listaImagenesActualizar = new ArrayList<Imagen>();

                    //En cada Imagene entrante de Android solo llega el id y Fecha
                    for (Imagen objAndroid : listaImagenesAndroid) {
                        for (Object objLocal : listaLocal) {
                            Imagen imgLocal = (Imagen) objLocal;
                            if (objAndroid.getId() == imgLocal.getId()) {
                                //verificar si la fecha de Android es < que la fecha de Android

                                if (Double.parseDouble(objAndroid.getFechaActualizacion())
                                        < Double.parseDouble(imgLocal.getFechaActualizacion())) {
                                    imgLocal.setTipoActulaizacion("u");
                                    listaImagenesActualizar.add(imgLocal);
                                }
                            }
                        }
                    }

                    // Otra forma de llenar los posibles eliminados
                    for (Imagen objAndroid : listaImagenesAndroid) {
                        boolean encontrado = false;
                        Imagen imgLocal = new Imagen();
                        for (Object objLocal : listaLocal) {
                            imgLocal = (Imagen) objLocal;
                            if (objAndroid.getId() == imgLocal.getId()) {
                                //verificar si la fecha de Android es < que la fecha de Local
                                encontrado = true;
                            }
                        }
                        if (encontrado == false) {
                            objAndroid.setTipoActulaizacion("d");
                            objAndroid.setIdCategoria(0);
                            objAndroid.setImagen(null);
                            listaImagenesActualizar.add(objAndroid);
                        }
                    }
                }
            }
            if (listaImagenesAndroid.size() < listaLocal.size()) {

                //codigo nuevo
                //mirar cual es el id mayor en listaCategoriaAndroid
                int idMayor = listaImagenesAndroid.get(0).getId();
                for (int i = 1; i < listaImagenesAndroid.size(); i++) {
                    Imagen img = listaImagenesAndroid.get(i);
                    if (img.getId() > idMayor) {
                        idMayor = img.getId();
                    }
                }
                //

                //mirar cual es el id mayor en listaCategoriaAndroid
                //int idMayor = listaImagenesAndroid.size() + 1;
                /*int idMayor = listaCategoriaAndroid.get(0).getId();
                 for(int i=1;i<listaCategoriaAndroid.size();i++){
                 Categoria c = listaCategoriaAndroid.get(i);
                 if(c.getId()>idMayor){
                 idMayor = c.getId();
                 }
                 }*/

                Imagen nuevasImagenes = new Imagen();
                listaImagenesActualizar.addAll(nuevasImagenes.consultarNuevos(idMayor));
            }
            // Llenar los que vamos a eliminar
            /*
             Imagen imgEliminar = new Imagen();
             if (imgEliminar.darEliminados(listaImagenesAndroid) != null){
             listaImagenesActualizar.addAll(imgEliminar.darEliminados(listaImagenesAndroid));
             }     */

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Imagen paraAct = new Imagen();
        paraAct.crearParaActualizarEnAndroid(listaImagenesActualizar);

        //return listaImagenesActualizar;
        return listaImagenesActualizar.size();
    }

    @WebMethod(operationName = "sincronizarCategorias")
    public ArrayList<Categoria> sincronizarCategorias(@WebParam(name = "idsCategorias") String[] idsCategorias, @WebParam(name = "fechasCategorias") String[] fechasCategorias) {
        //listaLocal de Categorias         
        ArrayList<Categoria> listaCategoriaAndroid = null;
        ArrayList<Object> listaLocal = null;
        ArrayList<Categoria> listaCategoriaActualizar = null;

        try {
            listaCategoriaAndroid = new ArrayList<Categoria>();

            for (int i = 0; i < idsCategorias.length; i++) {
                Categoria miCategoria = new Categoria();
                miCategoria.setId(Integer.parseInt(idsCategorias[i].toString()));
                miCategoria.setFechaActualizacion(fechasCategorias[i].toString());
                listaCategoriaAndroid.add(miCategoria);
            }

            Categoria CategoriaLocal = new Categoria();
            listaLocal = new ArrayList<Object>();
            listaLocal = CategoriaLocal.consultarTodos();

            if (listaLocal.size() > 0) {
                if (listaCategoriaAndroid.size() > 0) {

                    listaCategoriaActualizar = new ArrayList<Categoria>();

                    //en cada Categoria entrante de Android solo llega el id y Fecha
                    for (Categoria objAndroid : listaCategoriaAndroid) {
                        for (Object objLocal : listaLocal) {
                            Categoria catLocal = (Categoria) objLocal;
                            if (objAndroid.getId() == catLocal.getId()) {
                                //verificar si la fecha local > fecha android

                                if (Double.parseDouble(objAndroid.getFechaActualizacion())
                                        < Double.parseDouble(catLocal.getFechaActualizacion())) {
                                    catLocal.setTipoActualizacion("u");
                                    listaCategoriaActualizar.add(catLocal);
                                }
                            }
                        }
                    }
                    // Otra forma de llenar los posibles eliminados
                    for (Categoria objAndroid : listaCategoriaAndroid) {
                        boolean encontrado = false;
                        Categoria catLocal = new Categoria();
                        for (Object objLocal : listaLocal) {
                            catLocal = (Categoria) objLocal;
                            if (objAndroid.getId() == catLocal.getId()) {
                                //verificar si la fecha de Android es < que la fecha de Local
                                encontrado = true;
                            }
                        }
                        if (encontrado == false) {
                            objAndroid.setTipoActualizacion("d");
                            //objAndroid.setIdCategoria(0);
                            //objAndroid.setImagen(null);
                            listaCategoriaActualizar.add(objAndroid);
                        }
                    }
                }
            }
            if (listaCategoriaAndroid.size() < listaLocal.size()) {
                //mirar cual es el id mayor en listaCategoriaAndroid
                int idMayor = listaCategoriaAndroid.size() + 1;
                /*int idMayor = listaCategoriaAndroid.get(0).getId();
                 for(int i=1;i<listaCategoriaAndroid.size();i++){
                 Categoria c = listaCategoriaAndroid.get(i);
                 if(c.getId()>idMayor){
                 idMayor = c.getId();
                 }
                 }*/

                Categoria nuevos = new Categoria();
                listaCategoriaActualizar.addAll(nuevos.consultarNuevos(idMayor));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return listaCategoriaActualizar;
    }

    @WebMethod(operationName = "sincronizarImagenProducto")
    public ArrayList<ImagenProducto> sincronizarImagenProducto(@WebParam(name = "idCategoria") int idCategoria) {

        ArrayList<ImagenProducto> listaImagenesProductos = null;
        ArrayList<Object> listaLocal = null;

        try {
            ImagenProducto imagenLocalProducto = new ImagenProducto();
            listaLocal = new ArrayList<Object>();
            listaLocal = imagenLocalProducto.consultarTodos();
            listaImagenesProductos = new ArrayList<ImagenProducto>();

            for (int i = 0; i < listaLocal.size(); i++) {
                ImagenProducto miImagenP = (ImagenProducto) listaLocal.get(i);
                listaImagenesProductos.add(miImagenP);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return listaImagenesProductos;
    }

    @WebMethod(operationName = "sincronizarUsuarios")
    public ArrayList<Usuario> sincronizarUsuarios() {
        ArrayList<Usuario> listaUsuarios = null;
        ArrayList<Object> listaLocal = null;

        try {
            Usuario usuLocal = new Usuario();
            listaLocal = new ArrayList<Object>();
            listaLocal = usuLocal.consultarTodos();
            listaUsuarios = new ArrayList<Usuario>();

            for (int i = 0; i < listaLocal.size(); i++) {
                Usuario usu = (Usuario) listaLocal.get(i);
                listaUsuarios.add(usu);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return listaUsuarios;
    }

}
