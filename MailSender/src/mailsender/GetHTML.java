/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsender;
import java.io.*;
import java.net.*;
//import mailsender.ReadURL.*;

/**
 *
 * @author jcarrillo
 */
public class GetHTML {

    public GetHTML() {
    }

    public String getHTML(String urlToRead) {

        URL url;
        HttpURLConnection conn; // The actual connection to the web page
        BufferedReader rd; // Used to read results from the web page
        String line; // An individual line of the web page HTML
        String result = ""; // A long string containing all the HTML
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}
