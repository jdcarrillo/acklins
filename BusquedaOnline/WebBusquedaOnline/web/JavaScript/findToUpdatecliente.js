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

function findToUpdatecliente()

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

            var URL = "http://buscaonline.dyndns.org:8080/Busqueda/resources/cliente/cliente/" + inputObject.value + "/json/";

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
            var x = myObject.cliente.IDCLIENTE;
            if(x!=null)
            {

            // clear the 'display' div before displaying a new data

            document.getElementById('display').innerHTML = "";

            // get control of the 'display' div

            var root=document.getElementById('display');

            var newParagraph, y;


            y = myObject.cliente.IDCLIENTE;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="ID CLIENTE:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "IDCLIENTE" + "\"" + " " + "id=" + "\"" + "IDCLIENTE" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + " " + "disabled=" + "\"" + "disabled" + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.cliente.nombre;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="NOMBRE CLIENTE:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "NOMBRECLIENTE" + "\"" + " " + "id=" + "\"" + "NOMBRECLIENTE" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.cliente.apellido;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="APELLIDO CLIENTE:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "APELLIDOCLIENTE" + "\"" + " " + "id=" + "\"" + "APELLIDOCLIENTE" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.cliente.ciudad;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="CIUDAD CLIENTE:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "CIUDADCLIENTE" + "\"" + " " + "id=" + "\"" + "CIUDADCLIENTE" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);


            y = myObject.cliente.direccion;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="DIRECCION CLIENTE:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "DIRECCIONCLIENTE" + "\"" + " " + "id=" + "\"" + "DIRECCIONCLIENTE" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);

            y = myObject.cliente.telefono;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="TELEFONO CLIENTE:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "TELEFONOCLIENTE" + "\"" + " " + "id=" + "\"" + "TELEFONOCLIENTE" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);

            y = myObject.cliente.email;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="EMAIL CLIENTE:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "EMAILCLIENTE" + "\"" + " " + "id=" + "\"" + "EMAILCLIENTE" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);

            y = myObject.cliente.login;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="LOGIN:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "LOGIN" + "\"" + " " + "id=" + "\"" + "LOGIN" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);

            y = myObject.cliente.password;
            newParagraph = document.createElement("p");
            newParagraph.setAttribute("class", "description");
            newParagraph.innerHTML="PASSWORD:<br/><input type=" + "\"" + "text" + "\"" + " " + "name=" + "\"" + "PASSWORD" + "\"" + " " + "id=" + "\"" + "PASSWORD" + "\"" + " " + "style=" + "\"" + "margin-left:16px" + "\"" + " " + "value=" + "\"" + y + "\"" + "/>";
            root.appendChild(newParagraph);

            }
            else{
            window.alert("Record not found.Please enter a valid Primary Key.");
            }
        }

    }

}


function updatecliente()

{

    // continue if the ajaxObj object exists

    if(ajaxObj)

    {

        // continue if the object is idle

        if (ajaxObj.readyState == 4 || ajaxObj.readyState == 0)

        {

            // refer to the input text field

            var IDCLIENTEObject = document.getElementById('IDCLIENTE');
            var NOMBRECLIENTEObject = document.getElementById('NOMBRECLIENTE');
            var APELLIDOCLIENTEObject = document.getElementById('APELLIDOCLIENTE');
            var CIUDADCLIENTEObject = document.getElementById('CIUDADCLIENTE');
            var DIRECCIONCLIENTEObject = document.getElementById('DIRECCIONCLIENTE');
            var TELEFONOCLIENTEObject = document.getElementById('TELEFONOCLIENTE');
            var EMAILCLIENTEObject = document.getElementById('EMAILCLIENTE');
            var LOGINObject = document.getElementById('LOGIN');
            var PASSWORDObject = document.getElementById('PASSWORD');

           // build the “PUT” request URL

            var URL = "http://190.10.173.36:8080/Busqueda/resources/cliente/cliente";
            // open connection and send “PUT” request to server
            ajaxObj.open("PUT", URL, true);
            ajaxObj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            // set the function to be called on a change in ajaxObj state
            ajaxObj.onreadystatechange = handleUpdateResponse;
            // set additional parameters to be sent to server.
            //ajaxObj.send("IDCLIENTE="+IDCLIENTEObject.value +"&"+"NOMBRECLIENTE="+NOMBRECLIENTEObject.value +"&"+"APELLIDOCLIENTE="+APELLIDOCLIENTEObject.value +"&"+"CIUDADCLIENTE="+CIUDADCLIENTEObject.value +"&"+"teamName="+teamNameObject.value);
            ajaxObj.send("IDCLIENTE="+IDCLIENTEObject.value +"&"+"NOMBRECLIENTE="+NOMBRECLIENTEObject.value +"&"+"APELLIDOCLIENTE="+APELLIDOCLIENTEObject.value +"&"+"CIUDADCLIENTE="+CIUDADCLIENTEObject.value +"&"+"DIRECCIONCLIENTE="+DIRECCIONCLIENTEObject.value +"&"+"TELEFONOCLIENTE="+TELEFONOCLIENTEObject.value +"&"+"EMAILCLIENTE="+EMAILCLIENTEObject.value +"&"+"LOGIN="+LOGINObject.value +"&"+"PASSWORD="+PASSWORDObject.value);
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

                    var x = myObject.cliente.httpStatus;
                    if (x == 200)

                        {

                    window.alert("Exito al Actualizar datos del Cliente");

                        }

                        else{

                    window.alert("Operation failed");

                        }

        }

    }

}
