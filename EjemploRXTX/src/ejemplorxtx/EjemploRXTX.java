/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejemplorxtx;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;






/**
 *
 * @author jcarrillo
 */
public class EjemploRXTX {
    static CommPortIdentifier portId;
    static CommPortIdentifier saveportId;
    static Enumeration  portList;
    static InputStream inputStream;
    static OutputStream outputStream;
    static BufferedInputStream bufferedInputStream;
    static SerialPort  serialPort;
   
   
  public static void main(String[] args){
        boolean gotPort = false;
        String port;
      
 
        
        portList = CommPortIdentifier.getPortIdentifiers();
       
        while (portList.hasMoreElements()) {       
            portId = (CommPortIdentifier) portList.nextElement(); //get next port to check
            
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if ( portId.getName().equals("/dev/ttyS0") ) {
                    port = portId.getName();
                    gotPort = true;
                }
                
                if (gotPort == true) {
                    try {
                        serialPort = (SerialPort)portId.open("SMSSender", 2000);
                    } catch (PortInUseException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        serialPort.setSerialPortParams(19200,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_2,
                                SerialPort.PARITY_NONE
                                );
                    } catch (UnsupportedCommOperationException ex) {
                        ex.printStackTrace();
                    }
                   
                    try {
                        inputStream = serialPort.getInputStream();
                        bufferedInputStream = new BufferedInputStream(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   
                    serialPort.notifyOnDataAvailable(true);
                }
            }
        }
       
//        if (!(outputStream == null))
//            outputStream.write("array de váis que queremos enviar");
        byte[] readBuffer = new byte[1];
        boolean read = false;
        while(!read) {
            try {
                String getInfo = "";
                Thread.sleep(100);
                while (bufferedInputStream.available() > 0) {
                    int numBytes = bufferedInputStream.read(readBuffer);
                    getInfo += new String(readBuffer);
                    read = true;
                }
//                feedback += getInfo;
//                int length = getInfo.length();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
}