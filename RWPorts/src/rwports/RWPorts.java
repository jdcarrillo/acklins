/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rwports;

//import static rwports.PuertoSerie.puerto;

 /*
 * @author Juanda
 */
public class RWPorts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     /*Si la aplicacion tiene problemas hay que poner el dll (libSerialPort.dll)
        dentro de jreX/bin/ y si es x64 hay que descargar jdk 1.X 32 bits
       */ 
        PuertoSerie ps = new PuertoSerie();
        try {
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        ps.Inicia();

        
    }
   
}
