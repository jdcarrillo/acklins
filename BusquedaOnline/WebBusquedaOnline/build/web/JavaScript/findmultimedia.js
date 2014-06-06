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

        // check for Safari, Mozilla, Opera�

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

function findmultimedia()

{

    // continue if the ajaxObj object exists

    if(ajaxObj)

    {

        // continue if the object is idle

        if (ajaxObj.readyState == 4 || ajaxObj.readyState == 0)

        {

            // refer to the input text field

            var inputObject = document.getElementById('inputbox');

            // build the �GET� request URL

            var URL = "http://buscaonline.dyndns.org:8080/Busqueda/resources/Multimedia/multimedia/" + inputObject.value + "/json/";

            // open connection and send �GET� request to server

            ajaxObj.open("GET", URL, true);

            // set the function to be called on a change in ajaxObj state

            ajaxObj.onreadystatechange = handleFindResponse;

            // set additional parameters (to be sent to server) to null

            ajaxObj.send(null);

        }

    }

}


// function called when there is a change ajaxObj state

function handleFindResponse()

{

    // check if the request is complete

    if (ajaxObj.readyState == 4)

    {

        // continue if the response is healthy

        if (ajaxObj.status == 200)

        {

            // store the server�s text response

            var textResponse = ajaxObj.responseText;
            myObject = JSON.parse(textResponse);
            var x = myObject.multimedia.IDMULTIMEDIA;
            if(x!=null)
            {

            // clear the 'display' div before displaying a new data

            document.getElementById('display').innerHTML = "";

            // get control of the 'display' div

            var root=document.getElementById('display');
            var tab=document.createElement('table');
            tab.setAttribute("cellpadding", 0);
            tab.setAttribute("cellspacing", 0);
            tab.setAttribute("width", "40%");
            tab.setAttribute("height", "40%");
            tab.setAttribute("border", 2);
            tab.className="mytable";
            var tbo=document.createElement('tbody');
            var row, cell, myObject, x;
                row=document.createElement('tr');
                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="ID MULTIMEDIA";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="TIPO CONTENIDO";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="DESCRIPCION";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="API";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);


                    tbo.appendChild(row);
                    row=document.createElement('tr');
                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    myObject = JSON.parse(textResponse);
                    x = myObject.multimedia.IDMULTIMEDIA;
                    cell.innerHTML = x;
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    myObject = JSON.parse(textResponse);
                    x = myObject.multimedia.tipo;
                    cell.innerHTML = x;
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    myObject = JSON.parse(textResponse);
                    x = myObject.multimedia.descripcion;
                    cell.innerHTML = x;
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    myObject = JSON.parse(textResponse);
                    x = myObject.multimedia.api;
                    cell.innerHTML = x;
                    row.appendChild(cell);


            tbo.appendChild(row);
            tab.appendChild(tbo);
            root.appendChild(tab);
            }
            else{
            window.alert("Record not found.Please enter a valid Primary Key.");
            }
        }

    }

}
