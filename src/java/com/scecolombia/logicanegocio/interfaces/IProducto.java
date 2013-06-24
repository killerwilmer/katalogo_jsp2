package com.scecolombia.logicanegocio.interfaces;

import java.util.List;

import com.scecolombia.logicanegocio.Imagen;
import com.scecolombia.logicanegocio.Producto;
import java.util.ArrayList;

public interface IProducto {
	public boolean asociarImagen(String idImagen,String idProducto);
	public ArrayList<Imagen> consultarImagenes(int idProducto);
	public ArrayList<Producto> consultarPorCategoria(int idCategoria);
        public ArrayList<Producto> consultarPorMarca(String nombreMarca);
        public ArrayList<Producto> consultarMarcas();
}
