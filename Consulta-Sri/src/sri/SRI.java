/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * 
 * @author Juanda
 */
public class SRI {

    /**
     * @param args
     *            the command line arguments
     */
    public String getHTML(String urlToRead) {
	URL url; // The URL to read
	HttpURLConnection conn; // The actual connection to the web page
	BufferedReader rd; // Used to read results from the web page
	String line; // An individual line of the web page HTML
	String result = ""; // A long string containing all the HTML
	try {
	    url = new URL(urlToRead);
	    conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    rd = new BufferedReader(
		    new InputStreamReader(conn.getInputStream()));
	    while ((line = rd.readLine()) != null) {
		result += line + "\n";
	    }
	    rd.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    public static void main(String[] args) {
	// TODO code application logic here

    }

}
