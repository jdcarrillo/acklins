/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package json;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author jcarrillo
 */
public class JsonRead {
    
    	private static final String filePath = "/home/jcarrillo/NetBeansProjects/JSON/ejemplo.json";
	
	public static void main(String[] args) {

    
    try {
			// read the json file
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			// get a String from the JSON object
			String firstName =  (String) jsonObject.get("firstname");
			System.out.println("El nombre es: " + firstName);

			// get a number from the JSON object
			long id =  (long) jsonObject.get("id");
			System.out.println("El id " + id);

			// get an array from the JSON object
			JSONArray lang= (JSONArray) jsonObject.get("languages");
			
			// take the elements of the json array
			for(int i=0; i<lang.size(); i++){
				System.out.println("The " + i + " element of the array: "+lang.get(i));
			}
			Iterator i = lang.iterator();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				System.out.println("language "+ innerObj.get("lang") + 
						" with level " + innerObj.get("knowledge"));
			}
			// handle a structure into the json object
			JSONObject structure = (JSONObject) jsonObject.get("job");
			System.out.println("Into job structure, name: " + structure.get("name"));

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}

    
}
