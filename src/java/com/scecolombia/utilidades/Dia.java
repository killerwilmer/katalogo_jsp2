/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scecolombia.utilidades;
import java.util.Hashtable;

/**
 *
 * @author cristhianlombana
 */
public class Dia{


        private Hashtable dia;

        public Dia(){
            dia = new Hashtable();
            dia.put("1", "DOMINGO");
            dia.put("2", "LUNES");
            dia.put("3", "MARTES");
            dia.put("4", "MIERCOLES");
            dia.put("5", "JUEVES");
            dia.put("6", "VIERNES");
            dia.put("7", "SABADO");
            


        }
        
        public String darDia(String clave){
            return dia.get(clave).toString();
        }



}