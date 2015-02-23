<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <title>Insertar inmueble</title>
    </head>
    <body>
        <div id="contenedor2">
            <h1><img src="imagenes/titulo.png" /></h1>
        <form action="Control" method="POST"  enctype="multipart/form-data">
            <table>
                <tr>
                    <td>
                        <label>Direcci&oacute;n:</label>
                    </td>
                    <td>
                        <input type="text" name="direccion" value="" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>N&uacute;mero:</label>
                    </td>
                    <td>
                        <input type="text" name="numero" value="" /> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Precio:</label>
                    </td>
                    <td>
                        <input type="text" name="precio" value="" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Tipo:</label>
                    </td>
                    <td>
                        <input type="text" name="tipo" value="" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Descripci&oacute;n:</label>
                    </td>
                    <td><textarea rows="4" cols="50" name="descripcion"></textarea></td>
                </tr>
                <tr>
                    <td>
                        <label>Imagen:</label>
                    </td>
                    <td><input type="file" name="archivo" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="target" value="inmueble" />
                        <input type="hidden" name="op" value="insert" />
                        <input type="hidden" name="action" value="op" />
                        <input type="hidden" name="view" value="web" />
                        <input id="btAceptar" type="submit" value="Insertar" />
                        <input id="btCancelar" type="button" value="Cancelar" />
                    </td>
                </tr>
            </table>
        </form>
        </div>
    </body>
</html>
