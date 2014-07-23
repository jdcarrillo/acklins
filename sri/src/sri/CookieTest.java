/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author root
 */
public class CookieTest {

    public static void setCookiePropertyInUrlConn(URLConnection uc,
	    String cookieValue) {
	uc.setRequestProperty("COOKIE", cookieValue);
    }

    public static List<String> getCookiePropertyFtomUrlConn(URLConnection uc) {
	ArrayList<String> result = new ArrayList<String>();
	;
	String headerName = null;
	for (int i = 1; (headerName = uc.getHeaderFieldKey(i)) != null; i++) {
	    if (headerName.equals("Set-Cookie")) {
		result.add(uc.getHeaderField(i));
	    }
	}

	return result;
    }

    public static void main(String[] args) throws MalformedURLException,
	    IOException {
	URL u = new URL(
		"https://declaraciones.sri.gob.ec/consultas-renta-internet/consultaNaturales.jsf?form%3AseleccionRadioButton=C&form%3ArucRazonSocial=&form%3Acedula=0704356765&form%3Apassaport=&form%3AapellidoN=&form%3Asubmit=Buscar&form=form&autoScroll=&javax.faces.ViewState=j_id1");
	URLConnection uc = u.openConnection();
	// uc.setRequestProperty("Cookie", "name1=value1; name2=value2");
	uc.connect();

	String headerName = null;
	for (int i = 1; (headerName = uc.getHeaderFieldKey(i)) != null; i++) {
	    if (headerName.equals("Set-Cookie")) {
		String cookie = uc.getHeaderField(i);
		System.out.println(cookie);
	    }
	}

    }
}
