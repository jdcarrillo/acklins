<%-- 
    Document   : index
    Created on : 5/06/2014, 05:14:08 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        
        
    <%-- start web service invocation --%><hr/>
    <%
    try {
	ws.WsAppGetinfo service = new ws.WsAppGetinfo();
	ws.WsAppGetinfoSoap port = service.getWsAppGetinfoSoap12();
	 // TODO initialize WS operation arguments here
	long snum = 0L;
	int isBackOffice = 1;
	java.lang.String securityKey = "oka";
	// TODO process result here
	ws.FnWsGetProductInfoResponse.FnWsGetProductInfoResult result = port.fnWsGetProductInfo(snum, isBackOffice, securityKey);
	out.println("Result = "+result.getSchema().getLang());
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
