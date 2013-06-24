/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.interfaz.combos;

import com.scecolombia.logicanegocio.Categoria;
import com.scecolombia.logicanegocio.Producto;
import java.util.ArrayList;

/**
 *
 * @author killer
 */
public class Combos {
    
        //Metodo para generar un combo
    public String darCombosCategoria() {
        Categoria miCategoria = new Categoria();
        String combo = "";
        ArrayList<Object> misCategorias = miCategoria.consultarTodos();
        
        try {
            if ( misCategorias.size() > 0 )
            {
                for (int i = 0; i < misCategorias.size(); i++) {
                    miCategoria = (Categoria) misCategorias.get(i);
                    combo += "<option value=" + miCategoria.getId() + ">" + miCategoria.getNombre() + "</option>";
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return combo;
    }    
    
        public String darComboMarcas() {
        Producto miProducto = new Producto();
        String combo = "";
        ArrayList<Producto> misProductos = miProducto.consultarMarcas();
        
        try {
            if ( misProductos.size() > 0 )
            {
                for (int i = 0; i < misProductos.size(); i++) {
                    miProducto = (Producto) misProductos.get(i);
                    combo += "<option value=" + miProducto.getMarca() + ">" + miProducto.getMarca() + "</option>";
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return combo;
    }   
}
