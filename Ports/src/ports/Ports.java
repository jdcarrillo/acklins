/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ports;



import java.io.BufferedReader;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author jcarrillo
 */
public class Ports {

    /**
     * @param args the command line arguments
     */
    
    private BufferedReader input;
    
    public void Inicia(){

        String[] portNames = SerialPortList.getPortNames();
        for (String port : portNames) {
            System.out.println(port);
        }

        // inicialization with selecting port for communication
        //SerialPort serialPort = new SerialPort("COM3");
        SerialPort serialPort = new SerialPort("/dev/ttyUSB0");

        try {
            // open port for communication
            serialPort.openPort();
            // baundRate, numberOfDataBits, numberOfStopBits, parity
            //serialPort.setParams(9600, 7, 1, 0);
            serialPort.setParams(9600, 8, 1, 0,false,true);
            // byte data transfer
//            serialPort.writeBytes("text to tranfer".getBytes());
            // String data transfer
//            serialPort.writeString("text to tranfer");
            System.out.println("Puerto: " + serialPort.getPortName());
            System.out.println("Puerto abierto: " + serialPort.isOpened());
            
            
            System.out.println("Lectura " + serialPort.readString());
            
            while(serialPort.readString() == null){
            System.out.println("Lectura: " + serialPort.readString());    
            System.out.println("Lectura2: " + serialPort.readHexString());    
            System.out.println("Lectura3: " + serialPort.getInputBufferBytesCount());    
            }
            //System.out.println("Lectura: " + serialPort.readString());
//            String s = serialPort.readString();
//            System.out.print(s);

            // close port
            serialPort.closePort();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        // get computer serial ports names

        Ports main = new Ports();   
        main.Inicia();
        Thread t = new Thread() {
            @Override
            public void run() {
                //the following line will keep this app alive for 1000    seconds,
                //waiting for events to occur and responding to them    (printing incoming messages to console).
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");
    }

}
