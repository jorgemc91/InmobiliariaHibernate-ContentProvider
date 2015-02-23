<%@page import="java.util.List"%>
<%@page import="com.izv.hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <title>Listado de Inmuebles</title>
    </head>
    <body>
        <div id="contenedor">
            <h1><img src="imagenes/titulo.png" /></h1>
            <h2>
                <a href="Control?target=inmueble&op=target&action=view">A&ntilde;adir inmueble</a>
            </h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Direcci&oacute;n</th>
                        <th>N&uacute;mero</th>
                        <th>Precio</th>
                        <th>Tipo</th>
                        <th>Descripci&oacute;n</th>
                        <th>Imagen</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Inmueble> lista = (List<Inmueble>) request.getAttribute("datos");
                        for (Inmueble i : lista) {
                    %>
                    <tr>
                        <td><%= i.getId()%></td>
                        <td><%= i.getDireccion()%></td>
                        <td><%= i.getNumero()%></td>
                        <td><%= i.getPrecio()%></td>
                        <td><%= i.getTipo()%></td>
                        <td><%= i.getDescripcion()%></td>
                        <td><img height="100" width="100" src="imagenes/<%= i.getImagen()%>"></td>
                        <td><a href="Control?target=inmueble&op=delete&action=op&id=<%= i.getId()%>"><img height="25" width="25" src="imagenes/borrar.png"/></a></td>
                        <td><a href="Control?target=inmueble&op=update&action=view&id=<%= i.getId()%>">Editar</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>