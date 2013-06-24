<%@page import="com.scecolombia.logicanegocio.Usuario"%>
<%@ page session="true" %>
<%
    String usuario = "";
    String clave = "";
    if (request.getParameter("usuario").trim() != null && request.getParameter("usuario").trim() != "") {
        usuario = request.getParameter("usuario");
    } else {
%>
<jsp:forward page="index.jsp">
    <jsp:param name="error" value="Favor digitar el nombre de usuario."/>
</jsp:forward>
<%            }


    if (request.getParameter("password").trim() != null && request.getParameter("password").trim() != "") {
        clave = request.getParameter("password");
    } else {
%>
<jsp:forward page="index.jsp">
    <jsp:param name="error" value="Favor digitar la contraseña."/>
</jsp:forward>
<%            }


    Usuario obj = new Usuario();

    int idUsuario = obj.existe(usuario, clave);

    if (idUsuario != 0) {
        HttpSession sesionOk = request.getSession();

        obj = (Usuario) obj.consultarUno(idUsuario);

        if (obj.getTipoUsuario() == 1) {
            sesionOk.setAttribute("admin", idUsuario);
            out.print("<script>window.location='administrador/indexadmin.jsp'</script>");
        }
    } else {
%>
<jsp:forward page="index.jsp">
    <jsp:param name="error" value="Usuario y/o clave incorrectos.<br>Vuelve a intentarlo."/>
</jsp:forward>
<%            }
%>
