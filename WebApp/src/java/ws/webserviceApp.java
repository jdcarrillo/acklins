/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

//import java.util.Iterator;

import javax.xml.ws.WebServiceRef;
import org.w3._2001.xmlschema.Schema;

//import java.util.Vector;
//import javax.xml.ws.Response;


/**
 *
 * @author root
 */
public class webserviceApp {

    /**
     * @param args the command line arguments
     */
    String URL = "http://servicios.acklins.net/ws_app_getinfo.asmx?WSDL";

    
    //@WebServiceRef(wsdlLocation="http://servicios.acklins.net/ws_app_getinfo.asmx?WSDL")
    static org.tempuri.FnWsGetProductInfo service;
    org.tempuri.FnWsGetProductInfoResponse.FnWsGetProductInfoResult port=service.getFnWsGetProductInfoResult();
    
    
    public static void main(String[] args) {
        // TODO code application logic here

//        Response r = null;
//        Call c = new Call();
//        Vector parameters = new Vector();
        FnWsGetProductInfoResponse.FnWsGetProductInfoResult response = fnWsGetProductInfo(0L, 1, "oka");
        //Iterator iterador = response.getSchema().getIncludesAndImportsAndRedefines().iterator();

        System.out.println(response.getSchema().id.toCharArray().length);
        System.out.println(response.getSchema().getId());

        
        
        
       // try { // Call Web Service Operation

//            
//            org.tempuri.FnWsGetProductInfo service = new org.tempuri.FnWsGetProductInfo();
//            org.tempuri.FnWsGetProductInfoResponse x = new org.tempuri.FnWsGetProductInfoResponse();
//            int isbo = service.getIsBackOffice();
//            long snum=service.getSnum();
//            String key = service.getSecurityKey();
//        
//            
//            FnWsGetProductInfoResponse.FnWsGetProductInfoResult s=ws.webserviceApp.fnWsGetProductInfo(0, 1, "oka");
//            
//        //org.tempuri.FnWsGetProductInfoResponse port = service.getIsBackOffice();
//
//        } catch (Exception ex) {
//            // TODO handle custom exceptions here
//        }
        
        
//        
//         try {
//            System.out.println("Retrieving the port from the following service: " + service);
//            org.tempuri.FnWsGetProductInfoResponse.FnWsGetProductInfoResult port=service.getFnWsGetProductInfoResult();
//            System.out.println("Invoking the sayHello operation on the port.");
//
//            String name;
//            if (args.length > 0) {
//                name = args[0];
//            } else {
//                name = "No Name";
//            }
//
//            Schema response = port.getSchema();
//            System.out.println(response);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
    
        
//                try {
//           webserviceApp client = new webserviceApp();
//            client.doTest(args);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        
        

    }

    private static FnWsGetProductInfoResponse.FnWsGetProductInfoResult fnWsGetProductInfo(long snum, int isBackOffice, java.lang.String securityKey) {
        ws.WsAppGetinfo service = new ws.WsAppGetinfo();
        ws.WsAppGetinfoSoap port = service.getWsAppGetinfoSoap12();
        return port.fnWsGetProductInfo(snum, isBackOffice, securityKey);
    }

    private static FnWsGetActivePaymentTypeResponse.FnWsGetActivePaymentTypeResult fnWsGetActivePaymentType(long snum, int isBackOffice, java.lang.String securityKey) {
        ws.WsAppGetinfo service = new ws.WsAppGetinfo();
        ws.WsAppGetinfoSoap port = service.getWsAppGetinfoSoap12();
        return port.fnWsGetActivePaymentType(snum, isBackOffice, securityKey);
    }
    
    
//  public void doTest(String[] args) {
//        try {
//            System.out.println("Retrieving the port from the following service: " + service);
//            //org.tempuri.FnWsGetProductInfoResponse.FnWsGetProductInfoResult  port = service.getFnWsGetProductInfoResult();
//            org.tempuri.FnWsGetProductInfoResponse.FnWsGetProductInfoResult port = service.getFnWsGetProductInfoResult();
//            System.out.println("Invoking the sayHello operation on the port.");
//
//            String name;
//            if (args.length > 0) {
//                name = args[0];
//            } else {
//                name = "No Name";
//            }
//
//            Schema response = port.getSchema();
//            System.out.println(response);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }



    

}


