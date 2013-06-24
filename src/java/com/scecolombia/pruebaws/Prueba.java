/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.pruebaws;

import java.util.ArrayList;
import java.util.List;
import com.scecolombia.logicanegocio.Imagen;

/**
 *
 * @author equipo1
 */
public class Prueba {

    public static void main(String[] args) {

        //WSSincronizacion_Service ws = new WSSincronizacion_Service();
        //WSSincronizacion ws2 = ws.getWSSincronizacionPort();

        String ids[] ={"648","649","650","654"};
        String fechas[] ={"1351532993","1351554794","1351555309","1357293282"};

        /*ArrayList ids = new ArrayList();
        ids.add("648");
        ids.add("649");
        ids.add("650");
        ids.add("654");

        ArrayList fechas = new ArrayList();
        fechas.add("1351532993");
        fechas.add("1351554794");
        fechas.add("1351555309");
        fechas.add("1357293282");*/

        List<Imagen> oo = sincronizarImagenes(ids, fechas);
    }

    private static ArrayList<Imagen> sincronizarImagenes(String[] idsImagenes, String[] fechasCategorias) {
        ArrayList<com.scecolombia.logicanegocio.Imagen> listaImagenesAndroid = null;
        ArrayList<Object> listaLocal = null;
        ArrayList<com.scecolombia.logicanegocio.Imagen> listaImagenesActualizar = null;

        try {
            listaImagenesAndroid = new ArrayList<com.scecolombia.logicanegocio.Imagen>();

            for (int i = 0; i < idsImagenes.length; i++) {
                com.scecolombia.logicanegocio.Imagen miImagen = new com.scecolombia.logicanegocio.Imagen();
                miImagen.setId(Integer.parseInt(idsImagenes[i].toString()));
                miImagen.setFechaActualizacion(fechasCategorias[i].toString());
                listaImagenesAndroid.add(miImagen);
            }

            com.scecolombia.logicanegocio.Imagen imagenLocal = new com.scecolombia.logicanegocio.Imagen();
            listaLocal = new ArrayList<Object>();
            listaLocal = imagenLocal.consultarTodos();

            if (listaLocal.size() > 0) {
                if (listaImagenesAndroid.size() > 0) {

                    listaImagenesActualizar = new ArrayList<com.scecolombia.logicanegocio.Imagen>();

                    //en cada Categoria entrante de Android solo llega el id y Fecha
                    for (com.scecolombia.logicanegocio.Imagen objAndroid : listaImagenesAndroid) {
                        for (Object objLocal : listaLocal) {
                            com.scecolombia.logicanegocio.Imagen imgLocal = (com.scecolombia.logicanegocio.Imagen) objLocal;
                            if (objAndroid.getId() == imgLocal.getId()) {
                                //verificar si la fecha local > fecha android

                                if (Double.parseDouble(objAndroid.getFechaActualizacion())
                                        < Double.parseDouble(imgLocal.getFechaActualizacion())) {
                                    imgLocal.setTipoActulaizacion("u");
                                    listaImagenesActualizar.add(imgLocal);
                                }
                            }
                        }
                    }
                }
            }
            if (listaImagenesAndroid.size() < listaLocal.size()) {
                //mirar cual es el id mayor en listaCategoriaAndroid
                int idMayor = listaImagenesAndroid.size() + 1;
                /*int idMayor = listaCategoriaAndroid.get(0).getId();
                 for(int i=1;i<listaCategoriaAndroid.size();i++){
                 Categoria c = listaCategoriaAndroid.get(i);
                 if(c.getId()>idMayor){
                 idMayor = c.getId();
                 }
                 }*/

                com.scecolombia.logicanegocio.Imagen nuevos = new com.scecolombia.logicanegocio.Imagen();
                listaImagenesActualizar.addAll(nuevos.consultarNuevos(idMayor));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        com.scecolombia.logicanegocio.Imagen paraAct = new com.scecolombia.logicanegocio.Imagen();
        paraAct.crearParaActualizarEnAndroid(listaImagenesActualizar);

        return listaImagenesActualizar;
    }
}
