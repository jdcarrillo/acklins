/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mataproceso;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jcarrillo
 */
public class MataProceso {
public static String ruta= "/home/jcarrillo/archivo.txt";
    /**
     * @param args the command line arguments
     */
    public static void main(String... args) throws java.io.IOException, InterruptedException, SQLException {

        TimerTask timerTask;
        timerTask = new TimerTask() {
            archivoLocal creaArchivo = new archivoLocal();
            leeArchivo archivo = new leeArchivo();
            String extraeDatos = archivo.leer(MataProceso.ruta);
            String extraeNombre = extraeDatos.split(":")[0]; //EXTRAE NOMBRE TIENDA
            String nombreTienda = "SEGAFREDO";
            String statusTienda = "";

            @Override
            public void run() {

                ComprobarConexion comconexion = new ComprobarConexion();
                boolean comprobar = comconexion.ComprobarConexion();
                //String estado;

                //COMPRUEBA CONEXION A LA BASE DE DATOS
                if (comprobar == true) {

                    consultaNombreTienda consulta = new consultaNombreTienda();

                    //CONSULTA SI EXISTE LA TIENDA Y ACCEDE A LA VERIFICACION DE STATUS
                    if (consulta.encuentraTienda(nombreTienda)) {
                        System.out.println("***TIENDA ENCONTRADA***");

                        ////////////////////////////////////                    
                        ///OBTIENE ESTADO
                        statusTienda = consulta.encuentraStatusTienda(nombreTienda);
                        ///CREA ARCHIVO
                        creaArchivo.escribir(nombreTienda, statusTienda);
                        /////////////////////////////////////

                        ///////////////////////////////////////////////////////////////////////////
                        //EXTRAE DATOS DE TIENDA
                        String extraeDatos = archivo.leer(MataProceso.ruta);
                        String extraeNombre = extraeDatos.split(":")[0]; //EXTRAE NOMBRE TIENDA
                        String extraeStatus = extraeDatos.split(":")[1]; //EXTRAE STATUS DE TIENDA
                        System.out.println("Nombre: " + extraeNombre + " Status: " + extraeStatus);
                        ///////////////////////////////////////////////////////////////////////////

                        //SI EL STATUS DE LA TIENDA ENCONTRADA ES 0 BAJA LA APLICACION
                        if (extraeStatus.equals("0")) {

                            try {
                                String[] cmd = {"TASKKILL", "/F", "/IM", "notepad.exe"}; //Comando de apagado en windows
                                Runtime.getRuntime().exec(cmd);
                            } catch (IOException ioe) {
                                System.out.println(ioe);

                            }
                        } else;
                    } else {
                        System.out.println("***TIENDA NO ENCONTRADA***");
                    }
                }//cierra comprobacion de conexion al Servidor de Base de Datos
                else {//////////REVISA ARCHIVO LOCAL SI NO EXISTA CONEXION////////////////////////

                    //EXTRAE DATOS DE TIENDA
                    String extraeDatos = archivo.leer(MataProceso.ruta);
                    String extraeNombre = extraeDatos.split(":")[0]; //EXTRAE NOMBRE TIENDA
                    String extraeStatus = extraeDatos.split(":")[1]; //EXTRAE STATUS DE TIENDA
                    System.out.println("Nombre: " + extraeNombre + " Status: " + extraeStatus);


                    //CONSULTA SI EXISTE LA TIENDA Y ACCEDE A LA VERIFICACION DE STATUS
                    if (extraeNombre.equals(nombreTienda)) {
                        System.out.println("***TIENDA ENCONTRADA***");

                        //SI EL STATUS DE LA TIENDA ENCONTRADA ES 0 BAJA LA APLICACION
                        if (extraeStatus.equals("0")) {
                            try {
                                String[] cmd = {"TASKKILL", "/F", "/IM", "notepad.exe"}; //Comando de apagado en windows
                                Runtime.getRuntime().exec(cmd);
                            } catch (IOException ioe) {
                                System.out.println(ioe);
                            }
                        } else;
                    } else {
                        System.out.println("***TIENDA NO ENCONTRADA***");
                    }

                }//FIN DE REVISION DE ARCHIVO SIN CONEXION
            }
        };


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);


    }


    
}
