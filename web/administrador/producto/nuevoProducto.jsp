<%-- 
    Document   : nuevoProducto
    Created on : 22-feb-2012, 17:18:41
    Author     : usuarui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/cssAdministrador/nproducto.css" type="text/css"/>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
    </head>
    <body>
        <div id="stylized" class="myform">
            <form id="form">
            <h1>Registro de Producto</h1>
            <label>Nombre
                <span class="small">Add your name</span>
            </label>
            <input type="text" name="nombre" id="nombre" />
            <label>Precio 1
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="precio1" id="precio1" />
            <label>Precio 2
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="precio2" id="precio2" />
            <label>Precio 3
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="precio3" id="precio3" />
            <label>Descuento 1
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="descuento1" id="descuento1" />
            <label>Descuento 2
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="descuento2" id="descuento2" />
            <label>Inpuesto 1
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="impuesto1" id="impuesto1" />
            <label>Inpuesto 2
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="impuesto2" id="impuesto2" />
            <label>Codigo
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="codigo" id="codigo" />
            <label>Fabricante
                <span class="small">Add a valid address</span>
            </label>
            <select id="gInvetigacion" name="gInvetigacion">
                <option value="estudiante">Gismar</option>
                <option value="docente">Docente</option>
                <option value="administrativo">ooo</option>
                <option selected="selected" value="otros">Otros</option>
            </select>      
            <label>Precio sin Iva
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="preciosinIva" id="preciosinIva" />
            <label>Iva
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="iva" id="iva" />
            <label>Categoria
                <span class="small">Add a valid address</span>
            </label>
            <select id="gInvetigacion" name="gInvetigacion">
                <option value="estudiante">Gismar</option>
                <option value="docente">Docente</option>
                <option value="administrativo">ooo</option>
                <option selected="selected" value="otros">Otros</option>
            </select> 
            <label>Link de Video
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="link" id="link" />
            <label>Fecha Actualización
                <span class="small">Add a valid address</span>
            </label>
            <input type="text" name="fecha" id="fecha" />
            <label>Imagen Producto
                <span class="small">Add a valid address</span>
            </label>
            <input type="file" id="imagen" name="imagen" />
            
            <input id="boton" type="submit" value="Guardar" name="enviar"/>
            <div class="spacer"></div>
        </form>
    </div>
    </body>
</html>
