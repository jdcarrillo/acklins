<%-- 
    Document   : buscarMultimedia
    Created on : 05/12/2010, 09:20:34 PM
    Author     : MAFER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="./JavaScript/json.js"></script>
	<script type="text/javascript" src="./JavaScript/findmultimedia.js"></script>
	<script type="text/javascript" src="./JavaScript/findAllmultimedia.js"></script>

        <link rel="stylesheet" href="style.css" type="text/css" charset="utf-8" />
        <title>Buscar Multimedia</title>

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
		          <a class="link" href="buscarCliente.jsp">Buscar Multimedia</a>
		            &nbsp;&nbsp;<a class="link" href="eliminarCliente.jsp">Eliminar Multimedia</a>
		            &nbsp;&nbsp;<a class="link" href="registrarCliente.jsp">Registrar Multimedia</a>
		            &nbsp;&nbsp;<a class="link" href="actualizarCliente.jsp">Actualizar Multimedia</a>
                            &nbsp;&nbsp;<a class="link" href="index.jsp">Menu Principal</a>
                        </center>
                </div>

               <center>
		<h4>Buscar  Promocion		</h4>


                <label class="description">Multimedia Id:</label>
		<input type="text" id="inputbox" style="margin-left:16px"/>

		<p>
			<input type="button" name="submit" value="Find" onclick="findmultimedia()"/>
			<input type="button" name="submit" value="FindAll" onclick="findAllmultimedia()"/>
		</p>


                </center>
                    <center> <div id="display" class="div_table"></div> </center>
		</div>
			</div>
			</div>
    </body>
</

