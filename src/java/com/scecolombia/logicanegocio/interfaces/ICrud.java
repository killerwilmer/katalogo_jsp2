package com.scecolombia.logicanegocio.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface ICrud {

	public int crear(Object obj);
	public boolean modificar(Object obj);
	public boolean eliminar(Object obj);
	public Object consultarUno(int id);
	public ArrayList<Object> consultarTodos();
	public ArrayList<Object> consultarLike(String parte);	
}
