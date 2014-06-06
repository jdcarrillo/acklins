<%-- 
    Document   : index
    Created on : 05/12/2010, 12:12:38 PM
    Author     : GIOVA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="./JavaScript/json.js"></script>
	<script type="text/javascript" src="./JavaScript/buscar.js"></script>

        <link rel="stylesheet" href="style.css" type="text/css" charset="utf-8" />
        <title>Fotos Flickr</title>
        

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
		           &nbsp;&nbsp;<a class="link" href="fotosFlickr.jsp">Fotos Multimedia</a>
                            &nbsp;&nbsp;<a class="link" href="index.jsp">Menu Principal</a>
                            <br>
                            <h4> <br> Buscar Fotos Flickr		</h4>
                <br>
                <form action="javascript:buscar();">
                    <input id="txt_buscar"  value="" > <input type="submit" value="Buscar" onclick="buscar()">
                </form>
                <br>
                    <div id="resultat"></div>
                        </center>
                </div>                              
		</div>
			</div>
			</div>
    </body>
   
    
</html>