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
public class archivoLocal {

    //no olviden importar esta librería al inicio de su programa
    //esto es solo un método, no una clase, el método deberá ser incluido en una clase java para su uso
    public void escribir(String nombreTienda,String statusTienda) {

        //LEE ARCHIVO ANTES DE ESCRIBIR
        leeArchivo archivo = new leeArchivo();
        String lecturaArchivo=archivo.leer(MataProceso.ruta);
        String cadenaTienda = nombreTienda+":"+statusTienda;
        
        if (lecturaArchivo == null || !new File(MataProceso.ruta).exists())
        {
            System.out.println("NO EXISTE EL ARCHIVO");
            File f = new File("ruta");
                            //Escritura
                try {
                    //FileWriter w = new FileWriter(f);
                    FileWriter w = new FileWriter(new File(MataProceso.ruta).getAbsolutePath());
                    BufferedWriter bw = new BufferedWriter(w);
                    PrintWriter wr = new PrintWriter(bw);
                    wr.write(nombreTienda + ":" + statusTienda);//escribimos en el archivo
                    //wr.append(" - y aqui continua"); //concatenamos en el archivo sin borrar lo existente
                    //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
                    //de no hacerlo no se escribirá nada en el archivo
                    wr.close();
                    bw.close();
                } catch (IOException e) {
                };
            
        } else if (lecturaArchivo.contentEquals(cadenaTienda)) {
                System.out.println("SIN CAMBIOS");
            } else {
                    String extraeDatos = archivo.leer(MataProceso.ruta);
                    String extraeNombre = extraeDatos.split(":")[0]; //EXTRAE NOMBRE TIENDA
                    String extraeStatus = extraeDatos.split(":")[1]; //EXTRAE STATUS DE TIENDA
                    System.out.println("Nombre: " + extraeNombre + " Status: " + extraeStatus);
                System.out.println("DENTRO DEL ARCHIVO CAMBIARA DE:"+ extraeNombre+":"+extraeStatus+" A: "  + nombreTienda + ":" + statusTienda);
                
                //f=new File();
                //f = new File("c:\\archivo.txt");
                if (!new File(MataProceso.ruta).exists()) {
                    System.out.println("NO EXISTE EL ARCHIVO");
                    File f = null;
                    f = new File(MataProceso.ruta);
                } else {
                    System.out.println("EXISTE EL ARCHIVO");
                    //f = new File("c:\\archivo.txt");

                }
                                            //Escritura
                try {
                    //FileWriter w = new FileWriter(f);
                    FileWriter w = new FileWriter(new File(MataProceso.ruta).getAbsolutePath());
                    BufferedWriter bw = new BufferedWriter(w);
                    PrintWriter wr = new PrintWriter(bw);
                    wr.write(nombreTienda + ":" + statusTienda);//escribimos en el archivo
                    //wr.append(" - y aqui continua"); //concatenamos en el archivo sin borrar lo existente
                    //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
                    //de no hacerlo no se escribirá nada en el archivo
                    wr.close();
                    bw.close();
                } catch (IOException e) {
                };

            }//SIN CAMBIOS DE ARCHIVO
    }
}
