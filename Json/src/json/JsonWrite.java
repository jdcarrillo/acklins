/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



/**
 *
 * @author jcarrillo
 */
public class JsonWrite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JSONObject obj = new JSONObject();
        obj.put("nombre", "juanda");
        obj.put("edad", new Integer(100));

        JSONArray list = new JSONArray();
        list.add("msj 1");
        list.add("msj 2");
        list.add("msj 3");

        obj.put("mensajes", list);

        try {

            FileWriter file = new FileWriter("/home/jcarrillo/NetBeansProjects/JSON/ejemplo.json");
            file.write(obj.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }
    
}
