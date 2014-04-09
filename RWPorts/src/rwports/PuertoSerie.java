/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rwports;

import app.*;
import core.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import static rwports.PuertoSerie.listaPuertos;

/**
 *
 * @author Juanda
 */
public class PuertoSerie {

    static SerialPort puerto = new SerialPort();
    static List<String> listaPuertos;
    static Com com1;

    String recibido = "";
    String caracter = "";

    public void Inicia() {

        System.out.println("La aplicacion ha iniciado...");
        System.out.println(System.getProperty("java.library.path"));

        puerto = new SerialPort(); //se crea el puerto serial

        try {

            listaPuertos = puerto.getFreeSerialPort(); //se obtienen los puertos disponibles y se imprimen
            for (String string : listaPuertos) {
                System.out.println(string);
            }

            Parameters settings = new Parameters(); //se comienza con la configuracion de los tipos de datos a recibir
            //mediante la variiable settings
            // Configuraci�n del puerto
            settings.setPort("/dev/ttyS0"); //puerto COM a utilizar
            settings.setBaudRate("9600");      // velocidad 9600 bps
            settings.setStopBits("1");         //se tienen dos bits de paro

            // Se pide memoria para Com y se le pasan parametros de configuraci�n
            com1 = new Com(settings);

            //se comienza con la lectura
            System.out.println("Leyendo buffer");

            int ciclo = 1;
            while (ciclo == 1) {
                System.out.println("recibiendo: " + com1.receiveSingleChar());
            }

        } catch (Exception ex) {
            Logger.getLogger(PuertoSerie.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.getMessage());
        }
    }
    
    public void Avaible(){
        for (String string : listaPuertos) {
            System.out.println(string);
        }
    }
}
