package com.scecolombia.logicanegocio.interfaces;

import java.util.List;

import com.scecolombia.logicanegocio.Imagen;
import com.scecolombia.logicanegocio.Producto;
import java.util.ArrayList;

public interface IImagen {

	public ArrayList<Object> consultarProductosAsociados(int idImagen);
        public ArrayList<Object> consultarPorFabricante(int idFabricante);
        public ArrayList<Object> consultarPorCategoria(int idCategoria);
}
