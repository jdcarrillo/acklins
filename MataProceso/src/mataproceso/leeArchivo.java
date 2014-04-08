/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mataproceso;
   import java.io.*;

/**
 *
 * @author jcarrillo
 */
public class leeArchivo {
    
 

    public String leer(String nombre)

    //El parametro nombre indica el nombre del leeArchivo por ejemplo "prueba.txt" 

    {

        try {

            File f;
            FileReader lectorArchivo;

            //Creamos el objeto del leeArchivo que vamos a leer
            f = new File(nombre);

            //Creamos el objeto FileReader que abrira el flujo(Stream) de datos para realizar la lectura
            lectorArchivo = new FileReader(f);

            //Creamos un lector en buffer para recopilar datos a travez del flujo "lectorArchivo" que hemos creado
            BufferedReader br = new BufferedReader(lectorArchivo);

            String l = "";
            //Esta variable "l" la utilizamos para guardar mas adelante toda la lectura del leeArchivo

            String aux = "";/*variable auxiliar*/

            while (true) //este ciclo while se usa para repetir el proceso de lectura, ya que se lee solo 1 linea de texto a la vez
            {
                aux = br.readLine();
//leemos una linea de texto y la guardamos en la variable auxiliar
                if (aux != null) //l=l+aux+"n";
                {
                    l = l + aux;
                } /*si la variable aux tiene datos se va acumulando en la variable l,
                 * en caso de ser nula quiere decir que ya nos hemos leido todo
                 * el leeArchivo de texto*/ else {
                    break;
                }
            }

            br.close();

            lectorArchivo.close();

            return l;

        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }
    
}