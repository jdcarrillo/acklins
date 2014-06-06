package ws;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.soap.util.xml.*;
import org.apache.soap.*;
import org.apache.soap.rpc.*;

/**
 * TemplClient is a sample class that calls Soap Objects future development
 * should be done somewhere else!
 */
public class TempClient {

    private static URL url;

    public static String getTemp(String zipcode) throws Exception {

        Call call = new Call();
        org.apache.soap.transport.http.SOAPHTTPConnection hc;
        URL url = new URL("http://services.xmethods.com:80/soap/servlet/rpcrouter");
        // Service uses standard SOAP encoding
        String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
        call.setEncodingStyleURI(encodingStyleURI);

        // Set service locator parameters
        call.setTargetObjectURI("urn:xmethods-Temperature");
        call.setMethodName("getTemp");

        // Create input parameter vector
        Vector params = new Vector();
        params.addElement(new Parameter("zipcode", String.class, zipcode, null));
        call.setParams(params);

          //hc = (org.apache.soap.transport.http.SOAPHTTPConnection) call.getSOAPTransport();
        //hc.setTimeout(10000);
        // Invoke the service ....
        Response resp = call.invoke(url, "");

        // ... and evaluate the response
        if (resp.generatedFault()) {
            throw new Exception();
        } else {
            // Call was successful. Extract response parameter and return result
            Parameter result = resp.getReturnValue();
            Float rate = (Float) result.getValue();
            return rate.toString();
        }
    }

    public static String getSoapNews() {
        Call call = new Call();
        try {
            url = new URL("http://drbob42.tdmweb.com/cgi-bin/DrBobsClinic.exe/soap");

            // Service uses standard SOAP encoding
            String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
            call.setEncodingStyleURI(encodingStyleURI);

            // Set service locator parameters
            call.setTargetObjectURI("urn:Headline-IHeadLine#SOAPNews");
            call.setMethodName("SOAPNews");

            // Create input parameter vector
            Vector params = new Vector();
            params.addElement(new Parameter("Argument", Integer.class, "1", null));
            //params.addElement (new Parameter("RequestName", String.class, "newslinx", null));
            call.setParams(params);

            // Invoke the service ....
            Response resp = call.invoke(url, "");

            // ... and evaluate the response
            if (resp.generatedFault()) {
                Fault fault = resp.getFault();
                return resp.getFault().toString() + "**";

            } else {
                // Call was successful. Extract response parameter and return result
                Parameter result = resp.getReturnValue();
                String news = (String) result.getValue();
                return news;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getBreakingNews() throws Exception {
        Call call = new Call();

        url = new URL("http://www22.brinkster.com/prasads/breakingnewsservice.asmx");
        // Service uses standard SOAP encoding
        String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
        call.setEncodingStyleURI(encodingStyleURI);
        // Set service locator parameters
        call.setTargetObjectURI("http://www22.brinkster.com/webservices");
        call.setMethodName("");

          // Create input parameter vector
        //  Vector params = new Vector ();
        //  params.addElement (new Parameter("SRLFile", String.class, "NEWS.SRI", null));
        //  params.addElement (new Parameter("RequestName", String.class, "newslinx", null));
//  call.setParams (params);
        // Invoke the service ....
        Response resp = call.invoke(url, "GetCNNNews");

        // ... and evaluate the response
        if (resp.generatedFault()) {
            Fault fault = resp.getFault();
            return resp.getFault().toString() + "**";

        } else {
            // Call was successful. Extract response parameter and return result
            Parameter result = resp.getReturnValue();
            String news = (String) result.getValue();
            return news;
        }

    }

    public static String getNews() throws Exception {

        Call call = new Call();

        url = new URL("http://www.SoapClient.com/xml/SQLDataSoap.WSDL");
        // Service uses standard SOAP encoding
        String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
        call.setEncodingStyleURI(encodingStyleURI);

        // Set service locator parameters
        call.setTargetObjectURI("urn:soapClient-HeadlineNews");
        call.setMethodName("ProcessSRL");

        // Create input parameter vector
        Vector params = new Vector();
        params.addElement(new Parameter("SRLFile", String.class, "NEWS.SRI", null));
        params.addElement(new Parameter("RequestName", String.class, "newslinx", null));
        call.setParams(params);

        // Invoke the service ....
        Response resp = call.invoke(url, "");

        // ... and evaluate the response
        if (resp.generatedFault()) {
            Fault fault = resp.getFault();
            System.out.println((String) resp.getReturnValue().getValue());
            return resp.getFault().toString() + "**";

        } else {
            // Call was successful. Extract response parameter and return result
            Parameter result = resp.getReturnValue();
            String news = (String) result.getValue();
            return news;
        }
    }

    public static String getTopicNews() throws Exception {
        Call call = new Call();
        url = new URL("http://v003u23wlw.net.maximumasp.com/V2WSCustNews.asmx");
        // Service uses standard SOAP encoding
        String encodingStyleURI = Constants.NS_URI_SOAP_ENC;

        // Set service locator parameters
        call.setTargetObjectURI("");
        call.setMethodName("GetCustomNews");
        // Create input parameter vector
        Vector params = new Vector();
        params.addElement(new Parameter("Topic", String.class, "Business", null));
        call.setParams(params);

        // Invoke the service ....
        call.setEncodingStyleURI(encodingStyleURI);
        try {
            Response resp = call.invoke(url, "GetCustomNews");
            // ... and evaluate the response
            if (resp.generatedFault()) {
                Fault fault = resp.getFault();

                return resp.getFault().toString() + "**";
            } else {
                // Call was successful. Extract response parameter and return result
                Parameter result = resp.getReturnValue();
                String news = (String) result.getValue();
                return news;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String FindMP3() throws Exception {

        Call call = new Call();
        URL url = new URL("http://ws.ebyz.com/soap/servlet/rpcrouter");

        // Service uses standard SOAP encoding
        String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
        call.setEncodingStyleURI(encodingStyleURI);

        // Set service locator parameters
        call.setTargetObjectURI("urn:ebyz_CorpSearchService");
        call.setMethodName("CorpSearch");

        // Create input parameter vector
        Vector params = new Vector();
        params.addElement(new Parameter("CorpSearch", String.class, "Cartoon", null));

        call.setParams(params);
        // Invoke the service ....
        Response resp = call.invoke(url, "");

        // ... and evaluate the response
        if (resp.generatedFault()) {
            Fault fault = resp.getFault();
            return resp.getFault().toString() + "**";
        } else {
            // Call was successful. Extract response parameter and return result
            Parameter result = resp.getReturnValue();
            return result.toString();
        }
    }
    /*
     223     public String getSoapNews2() throws java.net.MalformedURLException{
     224         String strReturn = "";
     225         try{
     226             strReturn = ws.getRandomQuestion();
     227             return strReturn;
     228         }catch(org.apache.soap.SOAPException e){
     229             e.printStackTrace();
     230             return "";
     231         }
     232     }
     233 
     234     public String getBreakingNews2() throws java.net.MalformedURLException{
     235      String strReturn = "";
     236      localhost.webservices.BreakingNewsSoap ws = new localhost.webservices.BreakingNewsSoap(new java.net.URL("http://www22.brinkster.com/prasads/BreakingNewsService.asmx"));
     237         try{
     238             strReturn = ws.GetCNNNews("");
     239             return strReturn;
     240         }catch(org.apache.soap.SOAPException e){
     241             e.printStackTrace();
     242             return "";
     243         }
     244     }
     245 */
     // Driver to illustrate service invocation
 
          public static void main(String[] args)
          {
              TempClient m2 = new TempClient();
         try
        {
                  System.out.println("Running");
            url=new URL("http://services.xmethods.com:80/soap/servlet/rpcrouter");
            String zipcode= "10018";
            String temp = getTemp(zipcode);
           System.out.println(temp);
                  System.out.println(getNews());
                  System.out.println(m2.getTopicNews()+"");
                  System.out.println(getSoapNews());
                  System.out.println(getBreakingNews());
        }
          catch (Exception e) {e.printStackTrace();}
          }
       
}
