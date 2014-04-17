/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ports;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author root
 */
public class Mains {
    static SerialPort serialPort;

    public static void main(String[] args) {
        serialPort = new SerialPort("/dev/ttyUSB0"); 
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
            
             
            
            
            //serialPort.closePort();
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
    
    /*
     * In this class must implement the method serialEvent, through it we learn about 
     * events that happened to our port. But we will not report on all events but only 
     * those that we put in the mask. In this case the arrival of the data and change the 
     * status lines CTS and DSR
     */
    static class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR()){//If data is available
                if(event.getEventValue() == 10){//Check bytes count in the input buffer
                    //Read data, if 10 bytes available 
                    try {
                        byte buffer[] = serialPort.readBytes(8);
                    }
                    catch (SerialPortException ex) {
                        System.out.println(ex);
                    }
                }
            }
            else if(event.isCTS()){//If CTS line has changed state
                if(event.getEventValue() == 1){//If line is ON
                    System.out.println("CTS - ON");
                }
                else {
                    System.out.println("CTS - OFF");
                }
            }
            else if(event.isDSR()){///If DSR line has changed state
                if(event.getEventValue() == 1){//If line is ON
                    System.out.println("DSR - ON");
                }
                else {
                    System.out.println("DSR - OFF");
                }
            }
            if (event.isTXEMPTY()){
                System.out.println("vacio");
            }else {System.out.println("no vacio");
            
            
            Ports x = new Ports();
            try {
                System.out.println("escribiendo: " + serialPort.writeString("H"));
            } catch (SerialPortException ex) {
                Logger.getLogger(Mains.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                String hex;
                hex = serialPort.readHexString(8, "");
                System.out.println("Resultado: " + x.resultScale(hex));
            } catch (SerialPortException ex) {
                Logger.getLogger(Mains.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }
            
            
           
         
        }
        
        
    }

    
}
