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


// function called when Delete button is pressed

function deleteProducto()

{

    // continue if the ajaxObj object exists

    if(ajaxObj)

    {

        // continue if the object is idle

        if (ajaxObj.readyState == 4 || ajaxObj.readyState == 0)

        {

        var y = document.getElementById('display').innerHTML;

        if(y != "")
        {

            // refer to the input text field

            var inputObject = document.getElementById('inputbox');

            // build the “DELETE” request URL

            var URL = "http://buscaonline.dyndns.org:8080/Busqueda/resources/productoes/producto/" + inputObject.value;

            // open connection and send “DELETE” request to server

            ajaxObj.open("DELETE", URL, true);

            // set the function to be called on a change in ajaxObj state

            ajaxObj.onreadystatechange = handleDeleteResponse;

            // set additional parameters (to be sent to server) to null

            ajaxObj.send(null);

        }

        else if(y == "")
        {

             window.alert("Find record before deleting");

        }

        }

    }

}


// function called when there is a change ajaxObj state

function handleDeleteResponse()

{

    // check if the request is complete

    if (ajaxObj.readyState == 4)

    {

        // continue if the response is healthy

        if (ajaxObj.status == 200)

        {

            document.getElementById('display').innerHTML = "";

            window.alert("Exitoso al Eliminar Cliente.");

        }

    }

}


