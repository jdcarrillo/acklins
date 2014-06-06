<%-- 
    Document   : registrarProducto
    Created on : 05/12/2010, 09:55:27 PM
    Author     : MAFER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <script type="text/javascript" src="./JavaScript/json.js"></script>
       <script type="text/javascript" src="./JavaScript/findToUpdateproducto.js"></script>

        <link rel="stylesheet" href="style.css" type="text/css" charset="utf-8" />
        <title>Registrar Productos</title>

        <SCRIPT LANGUAGE="JavaScript">
         var valor1 = 0;
     var valor2 =0.12;
     ///////////////////////////////////////////////
     //function Set1 ( ingreso ) { valor1 = parseInt( ingreso.value ); }<br class="CompletoP" />
    function totalizar ( boton ) { document.myform.precioconiva.value = (valor1*valor2) + valor1; }
     </SCRIPT>

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
		          <a class="link" href="buscarProducto.jsp">Buscar Producto</a>
		            &nbsp;&nbsp;<a class="link" href="eliminarProducto.jsp">Eliminar Producto</a>
		            &nbsp;&nbsp;<a class="link" href="registrarProducto.jsp">Registrar Producto</a>
		            &nbsp;&nbsp;<a class="link" href="actualizarProducto.jsp">Actualizar Producto</a>
                            &nbsp;&nbsp;<a class="link" href="index.jsp">Menu Principal</a>
                        </center>
                </div>
                            <form NAME="myform" action="http://192.168.0.12:8080/Busqueda/resources/productoes/producto/" method="POST">
               <center>
		<h4>Crear Registro de  Producto		</h4>
                    <table width="*" border="0" cellspacing="1" cellpadding="2">

                <tr>
		<td class="description">ID Producto:</td>
		<td><input type="text" name="IDPRODUCTO" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>
                <tr>
		<td class="description">Marca:</td>
                <td><input type="text" name="marca" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>
                <tr>
		<td class="description">Garantia:</td>
                <td><input type="text" name="garantia" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>

                <tr>
		<td class="description">Nombre:</td>
                <td><input type="text" name="nombre" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>

            <tr>
		<td class="description">Precio sin Iva:</td>
                <td><input type="text" NAME="preciosiniva" onChange="Set1( this )" style="margin-left:16px"/></td>
                <td class="requerido">*</td>
                </tr>
                                <tr>
		<td class="description">Precio con Iva:</td>
                <td><input type="text" NAME="precioconiva" style="margin-left:16px" /></td>
                <td class="requerido">*</td>
                </tr>

                </table>
		<p>
			<input type="submit" name="submit" value="Registrar Producto"/>

		</p>

                <p> <INPUT TYPE="button" VALUE="Calcular  Iva" onClick="totalizar( this )"></p>

                </center>
	</form>
		</div>
			</div>
			</div>


    </body>
</html>
