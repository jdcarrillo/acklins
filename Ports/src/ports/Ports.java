/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ports;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author jcarrillo
 */
public class Ports implements SerialPortEventListener {

    /**
     * @param args the command line arguments
     */
    private BufferedReader input;
    //private String buffer = "";
    private final String idPuerto = "/dev/ttyUSB0";

    public void Inicia() {

        String[] portNames = SerialPortList.getPortNames();
        for (String port : portNames) {
            System.out.println("Puertos encontrados: " + port);
        }

        // inicialization with selecting port for communication
        //SerialPort serialPort = new SerialPort("COM3");
        //SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
        SerialPort serialPort = new SerialPort(idPuerto);

        try {
            // open port for communication
            serialPort.openPort();
            System.out.println("\nPuerto abierto: " + serialPort.isOpened());
            // baundRate, numberOfDataBits, numberOfStopBits, parity
            //serialPort.setParams(9600, 7, 1, 0);
            System.out.println("Parametros ingresados: " + serialPort.setParams(9600, 8, 1, 0));

//            System.out.println("escribiendo: " + serialPort.writeString("?h"));
//
//            while (serialPort.readString(1) != null) {
//
//                System.out.println("reading " + serialPort.readString(1));
//                System.out.println("escribiendo: " + serialPort.writeString("H"));
//
//                if (serialPort.readString(2) != "?h") {
//                    serialPort.writeString("H");
//                    System.out.println("reading " + serialPort.readString(1));
//
//                    serialPort.writeString("H");
//
//                    break;
//                }
//            }
//            try {
//                serialPort.openPort();
//            serialPort.setParams(9600, 8, 1, 0);
//                while (true) {
//                    getByte();
//
//                }
//
//            } catch (SerialPortException ex) {
//               // System.out.println("error \n");
//                ex.printStackTrace();
//            }

            System.out.println("escribiendo: " + serialPort.writeString("H"));
            //String hex = serialPort.readHexString(8, "");
            
            String hex = serialPort.readString(3);
            int i=0;
            while (hex.contains("?") && i<10){
                i++;
                System.out.println("maal "+i);
                
                
                
                //else hex = serialPort.readHexString(8, "");
                    System.out.println("cerrando "+serialPort.closePort());
                    System.out.println("abriendo ");
                    openPort();
                    serialPort.writeString("H");
                    hex = serialPort.readString(3);
            }

            if (serialPort.closePort()==true) {
                System.out.println("entra al if");
                //closePort();
                openPort();
                serialPort.writeString("H");
                hex = serialPort.readString(8);
                
            } else {               serialPort.writeString("H");
                hex = serialPort.readString(8);}
//            if (serialPort.readString(1).toString().contains(" ")){
//                hex="";
//                closePort();
//                
//            }else hex=serialPort.readString(8);
     
            System.out.println("Resultado: " + hex);// resultScale(hex));}
            // close port
            System.out.println("\nPort closed: " + serialPort.closePort() + "\n");
            //serialPort.closePort(); //Cerrando Puerto
        } catch (SerialPortException ex) {

        }

    }

    protected byte getByte() throws jssc.SerialPortException {
        
        SerialPort serialPort = new SerialPort(idPuerto);

        while (serialPort.getInputBufferBytesCount() < 1) {
            try {
                Thread.sleep(1);
                if (Thread.currentThread().isInterrupted()) {
                    return 0;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return 0;
            }
        }
        return (byte) (serialPort.readBytes(1))[0];
    }

    public void openPort() {
        String[] portNames = SerialPortList.getPortNames();
        for (String port : portNames) {
            System.out.println("Puertos encontrados: " + port);
        }

        SerialPort serialPort = new SerialPort(idPuerto);

        try {
            // open port for communication
            serialPort.openPort();
            System.out.println("\nPuerto abierto: " + serialPort.isOpened());
            System.out.println("Parametros ingresados: " + serialPort.setParams(9600, 8, 1, 0));
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public void closePort() {
        SerialPort serialPort = new SerialPort(idPuerto);
        try {
            serialPort.closePort();
        } catch (SerialPortException ex) {
        }

    }

    public StringBuilder resultScale(String hex) {

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        //System.out.println(output);
        return output;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        // get computer serial ports names

        System.out.println("Inicia\n");
        Ports main = new Ports();

        main.Inicia();

        Thread t = new Thread() {
            @Override
            public void run() {
                //the following line will keep this app alive for 1000    seconds,
                //waiting for events to occur and responding to them    (printing incoming messages to console).
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            }
        };

        t.start();

        System.out.println("Finaliza");
       
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

        if (serialPortEvent.isTXEMPTY()) {
            try {
                String inputLine = null;
                if (input.ready()) {
                    inputLine = input.readLine();
                    System.out.println(inputLine);
                }

            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.

//        
//                               while (serialPort.readString(1) != null) {
//
//                if (serialPort.readString(1)!=serialPort.readString()){//cierra puerto 
//                    
//                    //vuelve abrir
//                }
    }

}
