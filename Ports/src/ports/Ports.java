/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ports;

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
    private final String idPuerto = "/dev/ttyUSB0";
    int baundRate = 9600;
    int dataBits = 8;
    int stopBits = 1;
    int parity = 0;

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
            System.out.println("Parametros ingresados: " + serialPort.setParams(baundRate, dataBits, stopBits, parity));
            System.out.println("Puerto abierto: " + serialPort.isOpened());
            System.out.println("\nEscribiendo: " + serialPort.writeString("H"));

            String data = serialPort.readString(2);
            System.out.println("Contiene: " + data);
            // baundRate, numberOfDataBits, numberOfStopBits, parity
            //serialPort.setParams(9600, 7, 1, 0);

            while (data.equals("?") || !data.equals("?") && serialPort.isOpened() == true) {

                if (data.equals("?")) {
                    serialPort.closePort();
                    serialPort.openPort();
                    if (serialPort.isOpened() != true) {
                        System.out.println("Puerto cerrado ");
                        serialPort.openPort();
                    }
                    System.out.println("Parametros ingresados: " + serialPort.setParams(baundRate, dataBits, stopBits, parity));
                    System.out.println("Lectura incorrecta");
                    serialPort.writeString("H");
                    data = serialPort.readString(2);
                    System.out.println("Resultado1: " + data);
                } else {
                    serialPort.closePort();
                    serialPort.openPort();
                    if (serialPort.isOpened() != true) {
                        System.out.println("Puerto cerrado ");
                        serialPort.openPort();
                    }
                    System.out.println("Parametros ingresados: " + serialPort.setParams(baundRate, dataBits, stopBits, parity));
                    serialPort.writeString("H");
                    data = serialPort.readString(8);
                    System.out.println("Resultado2: " + data);
                    break;

                }
            }

            serialPort.closePort(); //Cerrando Puerto
        } catch (SerialPortException ex) {

        }

    }

    public StringBuilder convertHextoString(String hex) {

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
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            }
        };

        t.start();

        System.out.println("Finaliza");

    }

}
