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
function findAllpromocion()

{
    // continue if the ajaxObj object exists
    if(ajaxObj)
    {
        // continue if the object is idle
        if (ajaxObj.readyState == 4 || ajaxObj.readyState == 0)
        {
            // build the “GET” request URL
            var URL = "http://buscaonline.dyndns.org:8080/Busqueda/resources/Promocion/promociones/";

            // open connection and send “GET” request to server
            // window.alert(URL);
            ajaxObj.open("GET", URL, true);

            // set the function to be called on a change in ajaxObj state

            ajaxObj.onreadystatechange = handleFindAllResponse;

            // set additional parameters (to be sent to server) to null

            ajaxObj.send(null);
        }
    }
}

// function called when there is a change ajaxObj state

function handleFindAllResponse()

{
    // check if the request is complete
    if (ajaxObj.readyState == 4)
    {
        // continue if the response is healthy
        if (ajaxObj.status == 200)
        {
            // store the server’s text response
            var textResponse = ajaxObj.responseText;
             //window.alert(textResponse);
            var  myObject = JSON.parse(textResponse);

            // clear the 'display' div before displaying a new data
           //window.alert("No records found");
            document.getElementById('display').innerHTML = "";

            // get control of the 'display' div

            var root=document.getElementById('display');

            var tab=document.createElement('table');
            tab.setAttribute("cellpadding", 0);
            tab.setAttribute("cellspacing", 0);
            tab.setAttribute("width", "20%");
            tab.setAttribute("height", "20%");
            tab.setAttribute("border", 2);
            tab.className="mytable";
            var tbo=document.createElement('tbody');
            var row, cell, x;
             maxRows=myObject.promociones[0].RowNumber;

            if (maxRows != 0){

            var nrRows=Number(maxRows)+1;

                row=document.createElement('tr');

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="ID PROMOCION";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="TIPO";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="DESCUENTO";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x ="TOTAL DESCUENTO";
                    cell.innerHTML = x.bold();
                    row.appendChild(cell);

                    
                    tbo.appendChild(row);
                   for(var i=1;i<nrRows;i++){
                   row=document.createElement('tr');

                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x = myObject.promociones[i].IDPROMOCION;
                    cell.innerHTML = x;
                    row.appendChild(cell);

                    tbo.appendChild(row);
                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x = myObject.promociones[i].TIPO;
                    cell.innerHTML = x;
                    row.appendChild(cell);

                    tbo.appendChild(row);
                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x = myObject.promociones[i].DESCUENTO;
                    cell.innerHTML = x;
                    row.appendChild(cell);

                    tbo.appendChild(row);
                    cell=document.createElement('td');
                    cell.setAttribute("style", "padding-left:5px;");
                    x = myObject.promociones[i].TOTALDESCUENTO;
                    cell.innerHTML = x;
                    row.appendChild(cell);


                    tbo.appendChild(row);
                    tab.appendChild(tbo);
            root.appendChild(tab);
           }
            //tbo.appendChild(row);
//            tab.appendChild(tbo);
//            root.appendChild(tab);
            }
            else {
            window.alert("No records found");
            }
        }
    }
}
