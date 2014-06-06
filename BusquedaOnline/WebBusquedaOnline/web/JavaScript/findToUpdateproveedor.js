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

function findToUpdateproveedor()

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

            var URL = "http://buscaonline.dyndns.org:8080/Busqueda/resources/Proveedor/proveedor/" + inputObject.value + "/json/";

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
            var x = myObject.proveedor.IDPROVEEDOR;
            if(x!=null)
            {

            // clear the 'display' div before displaying a new data

            document.getElementById('display').innerHTML = "";

            // get control of the 'display' div

            var root=document.getElementById('display');

            var newParagraph, y;


            y = myObject.proveedor.IDPROVEEDOR;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="ID PROVEEDOR:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "IDPROVEEDOR" + "\"" + " " + "id=" + "\"" + "IDPROVEEDOR" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + " " + "disabled=" + "\"" + "disabled" + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.proveedor.nombre;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="NOMBRE PROVEEDOR:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "nombre" + "\"" + " " + "id=" + "\"" + "nombre" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.proveedor.direccion;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="DIRECCION PROVEEDOR:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "direccion" + "\"" + " " + "id=" + "\"" + "direccion" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.proveedor.ciudad;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="CIUDAD PROVEEDOR:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "ciudad" + "\"" + " " + "id=" + "\"" + "ciudad" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.proveedor.telefono;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="TELEFONO PROVEEDOR:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "telefono" + "\"" + " " + "id=" + "\"" + "telefono" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);

            y = myObject.proveedor.email;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="EMAIL PROVEEDOR:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "email" + "\"" + " " + "id=" + "\"" + "email" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            }
            else{
            window.alert("Record not found.Please enter a valid Primary Key.");
            }
        }

    }

}


function updateproveedor()

{

    // continue if the ajaxObj object exists

    if(ajaxObj)

    {

        // continue if the object is idle

        if (ajaxObj.readyState == 4 || ajaxObj.readyState == 0)

        {

            // refer to the input text field

            var IDPROVEEDORObject = document.getElementById('IDPROVEEDOR');
            var NOMBREPROVEEDORObject = document.getElementById('nombre');
            var DIRECCIONPROVEEDORObject = document.getElementById('direccion');
            var CIUDADPROVEEDORObject = document.getElementById('ciudad');
            var TELEFONOPROVEEDORObject = document.getElementById('telefono');
            var EMAILPROVEEDORObject = document.getElementById('email');


           // build the “PUT” request URL

            var URL = "http://190.10.173.36:8080/Busqueda/resources/Proveedor/proveedor";
            // open connection and send “PUT” request to server
            ajaxObj.open("PUT", URL, true);
            ajaxObj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            // set the function to be called on a change in ajaxObj state
            ajaxObj.onreadystatechange = handleUpdateResponse;
            // set additional parameters to be sent to server.
            //ajaxObj.send("IDPRODUCTO="+IDCLIENTEObject.value +"&"+"NOMBRECLIENTE="+NOMBRECLIENTEObject.value +"&"+"APELLIDOCLIENTE="+APELLIDOCLIENTEObject.value +"&"+"CIUDADCLIENTE="+CIUDADCLIENTEObject.value +"&"+"teamName="+teamNameObject.value);
            ajaxObj.send("IDPROVEEDOR="+IDPROVEEDORObject.value +"&"+"nombre="+NOMBREPROVEEDORObject.value +"&"+"direccion="+DIRECCIONPROVEEDORObject.value +"&"+"ciudad="+CIUDADPROVEEDORObject.value +"&"+"telefono="+TELEFONOPROVEEDORObject.value +"&"+"email="+EMAILPROVEEDORObject.value);
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

                    var x = myObject.proveedor.httpStatus;
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


