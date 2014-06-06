<%-- 
    Document   : actualizarProducto
    Created on : 05/12/2010, 10:35:36 PM
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
        <title>Actualizar Productos</title>

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

               <center>
		<h4>Buscar  Productos		</h4>


                <label class="description">Producto Id:</label>
		<input type="text" id="inputbox" style="margin-left:16px"/>

		<p>
			<input type="button" name="submit" value="Buscar" onclick="findToUpdateproducto()"/>
                        <input type="submit" value="Update" onclick="updateproducto()"/>
		</p>


                </center>
                    <div id="display" class="div_table"></div>
		</div>
			</div>
			</div>
    </body>
</html>

