/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpicusb;

import jPicUsb.iface;

/**
 *
 * @author jcarrillo
 */
public class Jpicusb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.load("/home/jcarrillo/NetBeansProjects/jpicusb/jpicusb.dll");
        try{
	        iface.load();
	}catch(Exception e){
            System.out.println(e.getMessage()); //Hubo una excepcion al cargar dll. La libreria no funcionara
	}
        try {
            System.out.println("Presione una tecla cuando desee conectarse con el dispositivo.");
            System.in.read();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        byte[] out = new byte[64];
        out[0] = 99;
        try{
            //byte[] QWriteRead(byte[] salida, int writeout, int readin, long timeoutmsec)
            String data_in = new String(iface.QWriteRead(out, 1, 6, 1000), "utf-8");
            if (data_in.length() == 0) {
                System.out.println("No se recibieron datos");
            } else {
                System.out.println("Datos recibidos:" + data_in);
            }
        }catch(Exception ex){
            System.out.println("ERROR:" + ex.getMessage());
        }
        return;
            
    }
    
}
