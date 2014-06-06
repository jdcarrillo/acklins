/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import javax.jws.WebService;

/**
 *
 * @author root
 */
@WebService(serviceName = "ws_app_getinfo", portName = "ws_app_getinfoSoap", endpointInterface = "org.tempuri.WsAppGetinfoSoap", targetNamespace = "http://tempuri.org/", wsdlLocation = "WEB-INF/wsdl/webservices/servicios.acklins.net/ws_app_getinfo.asmx.wsdl")
public class webservices {



    public org.tempuri.FnWsGetActivePaymentTypeResponse.FnWsGetActivePaymentTypeResult fnWsGetActivePaymentType(long snum, int isBackOffice, java.lang.String securityKey) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public org.tempuri.FnWsGetProductInfoResponse.FnWsGetProductInfoResult fnWsGetProductInfo(long snum, int isBackOffice, java.lang.String securityKey) {
        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
        
        return fnWsGetProductInfo(0L,1,"oka");
    }
    
    
    
    
    
}
