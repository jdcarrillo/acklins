/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.rpc.*;
import org.apache.soap.rpc.Call;

/**
 *
 * @author root
 */
public class Client {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public class Hello {

        public String sayHello(String name) {
            return "Hello " + name;
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        System.out.println("\n\nCalling the SOAP Server to say hello\n\n");
        URL url = new URL("http://servicios.acklins.net/ws_app_getinfo.asmx?WSDL");
        String name="sayHello";

        Call call = new Call();
        call.setTargetObjectURI("urn:schemas-microsoft-com:xml-msdata");
        //call.setMethodName("sayHello");
        call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
        Vector params = new Vector();
        params.addElement(new Parameter("name", String.class, name, null));
        call.setParams(params);

        System.out.print("The SOAP Server says: ");
        Response resp = call.invoke(url, "");

        if (resp.generatedFault()) {
            Fault fault = resp.getFault();
            System.out.println("\nOuch, the call failed: ");
            System.out.println(" Fault Code= " + fault.getFaultCode());
            System.out.println(" Fault String = " + fault.getFaultString());
        } else {
            Parameter result = resp.getReturnValue();
            System.out.print(result.getValue());
            System.out.println();
        }

    }

}
