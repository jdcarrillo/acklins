/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author root
 */
public class ExtractUrl {

    public static String getTitle(String address) throws IOException {
	URL url = new URL(address);
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new InputStreamReader(url.openStream()));

	    String line = null;
	    while ((line = reader.readLine()) != null) {
		int start = line.indexOf("<title>");
		int end = line.indexOf("</title>");

		if (start != -1) {
		    return line.substring(start + "<title>".length(), end);
		}
	    }

	    return line;
	} finally {
	    if (reader != null)
		reader.close();
	}
    }

}
