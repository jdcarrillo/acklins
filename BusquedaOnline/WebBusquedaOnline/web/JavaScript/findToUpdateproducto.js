/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getXMLHttpRequestObject()

{

    // initially set the object to false

    var XMLHttpRequestObject = false;

    if (window.XMLHttpRequest)

    {

        // check for Safari, Mozilla, Opera…

        XMLHttpRequestObject = new XMLHttpRequest();

    }

    else if (window.ActiveXObject)

    {

        // check for Internet Explorer

        XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");

    }

     if (!XMLHttpRequestObject)

    {

        alert("Your browser does not support Ajax.");

        // return false in case of failure

        return false;

    }

    // return the object in case of success

    return XMLHttpRequestObject;

}

// get the XMLHttpRequestObject and store it in a variable

var ajaxObj = getXMLHttpRequestObject();


// function called when Find button is pressed

function findToUpdateproducto()

{

    // continue if the ajaxObj object exists

    if(ajaxObj)

    {

        // continue if the object is idle

        if (ajaxObj.readyState == 4 || ajaxObj.readyState == 0)

        {

            // refer to the input text field

            var inputObject = document.getElementById('inputbox');

            // build the “GET” request URL

            var URL = "http://buscaonline.dyndns.org:8080/Busqueda/resources/productoes/producto/" + inputObject.value + "/json/";

            // open connection and send “GET” request to server

            ajaxObj.open("GET", URL, true);

            // set the function to be called on a change in ajaxObj state

            ajaxObj.onreadystatechange = handleFindToUpdateResponse;

            // set additional parameters (to be sent to server) to null

            ajaxObj.send(null);

        }

    }

}


// function called when there is a change ajaxObj state

function handleFindToUpdateResponse()

{

    // check if the request is complete

    if (ajaxObj.readyState == 4)

    {

        // continue if the response is healthy

        if (ajaxObj.status == 200)

        {

            // store the server’s text response

            var textResponse = ajaxObj.responseText;
            myObject = JSON.parse(textResponse);
            var x = myObject.producto.IDPRODUCTO;
            if(x!=null)
            {

            // clear the 'display' div before displaying a new data

            document.getElementById('display').innerHTML = "";

            // get control of the 'display' div

            var root=document.getElementById('display');

            var newParagraph, y;


            y = myObject.producto.IDPRODUCTO;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="ID PRODUCTO:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "IDPRODUCTO" + "\"" + " " + "id=" + "\"" + "IDPRODUCTO" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + " " + "disabled=" + "\"" + "disabled" + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.producto.MARCA;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="MARCA:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "MARCA" + "\"" + " " + "id=" + "\"" + "MARCA" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.producto.GARANTIA;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="GARANTIA:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "GARANTIA" + "\"" + " " + "id=" + "\"" + "GARANTIA" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.producto.NOMBREPRODUCTO;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="NOMBRE PRODUCTO:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "NOMBREPRODUCTO" + "\"" + " " + "id=" + "\"" + "NOMBREPRODUCTO" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.producto.PRECIOSINIVA;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="PRECIO SIN IVA:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "PRECIOSINIVA" + "\"" + " " + "id=" + "\"" + "PRECIOSINIVA" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);

            y = myObject.producto.PRECIOCONIVA;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="PRECIO CON IVA:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "PRECIOCONIVA" + "\"" + " " + "id=" + "\"" + "PRECIOCONIVA" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            }
            else{
            window.alert("Record not found.Please enter a valid Primary Key.");
            }
        }

    }

}


function updateproducto()

{

    // continue if the ajaxObj object exists

    if(ajaxObj)

    {

        // continue if the object is idle

        if (ajaxObj.readyState == 4 || ajaxObj.readyState == 0)

        {

            // refer to the input text field

            var IDPRODUCTOObject = document.getElementById('IDPRODUCTO');
            var MARCAObject = document.getElementById('MARCA');
            var GARANTIAObject = document.getElementById('GARANTIA');
            var NOMBREPRODUCTOObject = document.getElementById('NOMBREPRODUCTO');
            var PRECIOSINIVAObject = document.getElementById('PRECIOSINIVA');
            var PRECIOCONIVAObject = document.getElementById('PRECIOCONIVA');
            

           // build the “PUT” request URL

            var URL = "http://190.10.173.36:8080/Busqueda/resources/productoes/producto";
            // open connection and send “PUT” request to server
            ajaxObj.open("PUT", URL, true);
            ajaxObj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            // set the function to be called on a change in ajaxObj state
            ajaxObj.onreadystatechange = handleUpdateResponse;
            // set additional parameters to be sent to server.
            //ajaxObj.send("IDPRODUCTO="+IDCLIENTEObject.value +"&"+"NOMBRECLIENTE="+NOMBRECLIENTEObject.value +"&"+"APELLIDOCLIENTE="+APELLIDOCLIENTEObject.value +"&"+"CIUDADCLIENTE="+CIUDADCLIENTEObject.value +"&"+"teamName="+teamNameObject.value);
            ajaxObj.send("IDPRODUCTO="+IDPRODUCTOObject.value +"&"+"MARCA="+MARCAObject.value +"&"+"GARANTIA="+GARANTIAObject.value +"&"+"NOMBREPRODUCTO="+NOMBREPRODUCTOObject.value +"&"+"PRECIOSINIVA="+PRECIOSINIVAObject.value +"&"+"PRECIOCONIVA="+PRECIOCONIVAObject.value);
        }

    }

}


// function called when there is a change ajaxObj state

function handleUpdateResponse()

{

    // check if the request is complete

    if (ajaxObj.readyState == 4)

    {

        // continue if the response is healthy

        if (ajaxObj.status == 200)

        {

            // store the server’s text response

            var textResponse = ajaxObj.responseText;

                    myObject = JSON.parse(textResponse);

                    var x = myObject.producto.httpStatus;
                    if (x == 200)

                        {

                    window.alert("Exito al Actualizar datos del Producto");

                        }

                        else{

                    window.alert("Operation failed");

                        }

        }

    }

}

