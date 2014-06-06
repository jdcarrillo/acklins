/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

//import java.util.Iterator;
//import java.util.Vector;
//import javax.xml.ws.Response;
import java.util.Iterator;
import org.apache.soap.rpc.Call;



/**
 *
 * @author root
 */
public class webserviceApp {

    /**
     * @param args the command line arguments
     */
    String URL = "http://servicios.acklins.net/ws_app_getinfo.asmx?WSDL";

    public static void main(String[] args) {
        // TODO code application logic here

//        Response r = null;
//        Call c = new Call();
//        Vector parameters = new Vector();

        FnWsGetProductInfoResponse.FnWsGetProductInfoResult response = fnWsGetProductInfo(0L, 1, "oka");
        //Iterator iterador = response.getSchema().getIncludesAndImportsAndRedefines().iterator();

        System.out.println(response.getSchema().id.toCharArray().length);


        

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

}
