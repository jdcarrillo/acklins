/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mataproceso;
import java.net.Socket;
/**
 *
 * @author jcarrillo
 */
public class ComprobarConexion {

    public boolean ComprobarConexion() {

        String dirWeb = "acklins.dyndns.org";
        int puerto = 3306;
        boolean conexion=false;
        
        try {
            Socket s = new Socket(dirWeb, puerto);
            if (s.isConnected()) {
                System.out.println("Conexión establecida con la dirección: " + dirWeb + " a travéz del puerto: " + puerto);
                conexion=true;
                return conexion;
            }
        } catch (Exception e) {
            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
            //return conexion;
            
        }
        return conexion;
    }
}
