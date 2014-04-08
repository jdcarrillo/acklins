<%-- 
    Document   : registrarProveedor
    Created on : 06/12/2010, 12:21:05 PM
    Author     : MAFER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <script type="text/javascript" src="./JavaScript/json.js"></script>
     

        <link rel="stylesheet" href="style.css" type="text/css" charset="utf-8" />
        <title>Registrar Proveedores</title>

    </head>

        <body class="body">
            <div id="wrapper">
                <div id="body">
		<div id="head"></div>
		<h1><img src="images/logo.jpg" width="401" height="230" alt="Logo" /></h1>
		<div id="MainContainer"style="overflow: auto; font-size: 16px; color: #999; font-weight: bold;">

		<div id="SubContainer">
                    <p class="description" style="text-align:center">Selecciones la Operacion que desea Realizar:</p>
		        <center>
		          <a class="link" href="buscarProveedor.jsp">Buscar Proveedor</a>
		            &nbsp;&nbsp;<a class="link" href="eliminarProveedor.jsp">Eliminar Proveedor</a>
		            &nbsp;&nbsp;<a class="link" href="registrarProveedor.jsp">Registrar Proveedor</a>
		            &nbsp;&nbsp;<a class="link" href="actualizarProveedor.jsp">Actualizar Proveedor</a>
                            &nbsp;&nbsp;<a class="link" href="index.jsp">Menu Principal</a>
                        </center>
                </div>
                            <form action="http://190.10.173.36:8080/Busqueda/resources/Proveedor/proveedor/" method="POST">
               <center>
		<h4>Crear Registro de  Proveedor	</h4>
                    <table width="*" border="0" cellspacing="1" cellpadding="2">

                <tr>
		<td class="description">ID Proveedor:</td>
		<td><input type="text" name="IDPROVEEDOR" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>

                <tr>
		<td class="description">Nombre:</td>
                <td><input type="text" name="nombre" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>
                
                <tr>
		<td class="description">Direccion:</td>
                <td><input type="text" name="direccion" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>

                <tr>
		<td class="description">Ciudad:</td>
                <td><input type="text" name="ciudad" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>

                <tr>
		<td class="description">Telefono:</td>
                <td><input type="text" name="telefono" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>
                 <tr>
		<td class="description">Email:</td>
                <td><input type="text" name="email" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>
             
                </table>
		<p>
			<input type="submit" name="submit" value="Registrar Proveedor"/>
		</p>

                </center>
	</form>
		</div>
			</div>
			</div>


    </body>
</html>

