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
import java.net.URLConnection;
import java.nio.charset.Charset;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Juanda
 */
public class Extrae {

    public String ExtraeHtml(String address) {
	try {
	    Document doc = Jsoup.connect(address).get();
	    Elements tableElements = doc.select("table");

	    Elements tableHeaderEles = tableElements.select("thead tr th");
	    System.out.println("headers");
	    for (int i = 0; i < tableHeaderEles.size(); i++) {
		System.out.println(tableHeaderEles.get(i).text());
	    }
	    System.out.println();

	    Elements tableRowElements = tableElements.select(":not(thead) tr");

	    for (int i = 0; i < tableRowElements.size(); i++) {
		Element row = tableRowElements.get(i);
		System.out.println("row");
		Elements rowItems = row.select("td");
		for (int j = 0; j < rowItems.size(); j++) {
		    System.out.println(rowItems.get(j).text());
		    return rowItems.get(j).text();
		}
		System.out.println();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String callURL(String myURL) {
	System.out.println("Requeted URL:" + myURL);
	StringBuilder sb = new StringBuilder();
	URLConnection urlConn = null;
	InputStreamReader in = null;
	try {
	    URL url = new URL(myURL);
	    urlConn = url.openConnection();
	    if (urlConn != null)
		urlConn.setReadTimeout(60 * 1000);
	    if (urlConn != null && urlConn.getInputStream() != null) {
		in = new InputStreamReader(urlConn.getInputStream(),
			Charset.defaultCharset());
		BufferedReader bufferedReader = new BufferedReader(in);
		if (bufferedReader != null) {
		    int cp;
		    while ((cp = bufferedReader.read()) != -1) {
			sb.append((char) cp);
		    }
		    bufferedReader.close();
		}
	    }
	    in.close();
	} catch (Exception e) {
	    throw new RuntimeException("Exception while calling URL:" + myURL,
		    e);
	}

	return sb.toString();
    }

}
