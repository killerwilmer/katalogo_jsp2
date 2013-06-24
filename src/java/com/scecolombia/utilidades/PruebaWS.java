/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.utilidades;

import com.scecolombia.logicanegocio.Usuario;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class PruebaWS {
    public static void main(String... args)
    {
        ArrayList<Usuario> listaUsuarios = null;
         ArrayList<Object>  listaLocal = null;
         
         try{
             Usuario usuLocal = new Usuario();
             listaLocal = new ArrayList<Object>();
             listaLocal = usuLocal.consultarTodos();
             listaUsuarios = new ArrayList<Usuario>();
             
             for(int i=0;i<listaLocal.size();i++){
                 Usuario usu = (Usuario)listaLocal.get(i);
                 listaUsuarios.add(usu);
             }
         }
         catch(Exception ex){
             System.out.println(ex.getMessage());
         }
         
         
        
    }
    
    
}
