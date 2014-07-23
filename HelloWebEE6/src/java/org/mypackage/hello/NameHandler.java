/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mypackage.hello;

import java.net.URL;

/**
 * 
 * @author jeff
 */
public class NameHandler {
    // private String name;
    String ci = "1713647640";
    String name;

    public NameHandler() {
	name = null;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = "https://declaraciones.sri.gob.ec/consultas-renta-internet/consultaNaturales.jsf?form%3AseleccionRadioButton=C&form%3ArucRazonSocial=&form%3Acedula="
		+ ci
		+ "&form%3Apassaport=&form%3AapellidoN=&form%3Asubmit=Buscar&form=form&autoScroll=&javax.faces.ViewState=j_id1";
    }

}
