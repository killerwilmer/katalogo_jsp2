/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.logicanegocio.interfaces;

/**
 *
 * @author skynet
 */
public interface ICliente {
    public boolean modificarPorNit(Object obj);
    public Object consultarUnoPorNit(String nit);
}
