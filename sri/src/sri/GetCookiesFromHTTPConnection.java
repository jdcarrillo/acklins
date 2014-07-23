/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sri;

import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author root
 */
public class GetCookiesFromHTTPConnection {

    public static void main(String[] args) throws Exception {

	URL url = new URL(
		"https://declaraciones.sri.gob.ec/consultas-renta-internet/consultaNaturales.jsf?form%3AseleccionRadioButton=C&form%3ArucRazonSocial=&form%3Acedula=0704356765&form%3Apassaport=&form%3AapellidoN=&form%3Asubmit=Buscar&form=form&autoScroll=&javax.faces.ViewState=j_id1");
	URLConnection conn = url.openConnection();

	Map<String, List<String>> headerFields = conn.getHeaderFields();

	Set<String> headerFieldsSet = headerFields.keySet();
	Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();

	while (hearerFieldsIter.hasNext()) {

	    String headerFieldKey = hearerFieldsIter.next();

	    if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {

		List<String> headerFieldValue = headerFields
			.get(headerFieldKey);

		for (String headerValue : headerFieldValue) {

		    System.out.println("Cookie Found...");

		    String[] fields = headerValue.split(";\\s*");

		    String cookieValue = fields[0];
		    String expires = null;
		    String path = null;
		    String domain = null;
		    boolean secure = false;

		    // Parse each field
		    for (int j = 1; j < fields.length; j++) {
			if ("secure".equalsIgnoreCase(fields[j])) {
			    secure = true;
			} else if (fields[j].indexOf('=') > 0) {
			    String[] f = fields[j].split("=");
			    if ("expires".equalsIgnoreCase(f[0])) {
				expires = f[1];
			    } else if ("domain".equalsIgnoreCase(f[0])) {
				domain = f[1];
			    } else if ("path".equalsIgnoreCase(f[0])) {
				path = f[1];
			    }
			}

		    }

		    System.out.println("cookieValue:" + cookieValue);
		    System.out.println("expires:" + expires);
		    System.out.println("path:" + path);
		    System.out.println("domain:" + domain);
		    System.out.println("secure:" + secure);

		    System.out
			    .println("*****************************************");

		}

	    }

	}

    }
}
