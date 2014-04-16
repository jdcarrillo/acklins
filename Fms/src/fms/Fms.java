/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fms;



import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.comm.*;
import javax.comm.CommPortIdentifier;





/**
 *
 * @author root
 */
//public class Fms {
/**
 * @param args the command line arguments
 */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
public class Fms implements Runnable {

    static Enumeration portList;
    static CommPortIdentifier portId;
    static SerialPort serialPort;
    static OutputStream outputStream;
    static InputStream inputStream;
    static Thread readThread, dataBase;
    static int crf;
    static int cwf;
    static byte cxc[] = new byte[4];
    static int cxm = 128;//0x80
    static byte[] creadBuffer;//1byte read
    static int creadBuff[];
    write rt = new write();
    static read rd;

    public void run() {
    }

    ;
    public static void main(String[] args) {
        int a = 10;
        write wr = new write();
        read rd = new read(1);
        int b = 0;
        int speed = 0, tcount = 0, jcount = 0, status = 0;
        while (b != 10) {
            for (int j = 0; j <= 1; j++) {
                try {
                    wr.cwritemain(a);
                    rd.cmainx(a);
                    readThread.sleep(1000);
                } catch (Exception e) {
                }
                if (j == 0) {
                    status = creadBuff[1];
                    jcount = creadBuff[5];
                    System.out.println("results : " + status + "\t" + jcount);
                }
                if (j == 1) {
                    speed = creadBuff[1] * 256 + creadBuff[2];//to convert received data in decimal
                    tcount = creadBuff[6];
                    System.out.println("results : " + speed + "\t" + tcount);
                }
            }
        }
    }

    static class write {

        public void cwritemain(int m) {
            cwf = m;
            cxc[0] = 0x26;
            portList = CommPortIdentifier.getPortIdentifiers();
            System.out.println(portList.hasMoreElements());
            while (portList.hasMoreElements()) {
                portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {

                    if (portId.getName().equals("/dev/ttyUSB0")) {
                        try {
                            serialPort = (SerialPort) portId.open("SimpleWriteApp32", 2000);
                        } catch (PortInUseException e) {
                        }
                        try {
                            outputStream = serialPort.getOutputStream();

                        } catch (Exception e) {
                        }
                        try {
                            serialPort.setSerialPortParams(9600,
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                        } catch (Exception e) {
                        }
                        if (cwf == 10) {
                            try {
                                outputStream.write(cxm);// 0x80 written
                                serialPort.close();
                                outputStream.close();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }
    }

    static class read implements SerialPortEventListener {

        public read(int s) {
        }

        public void cmainx(int cp) {
            crf = 10;
            portList = CommPortIdentifier.getPortIdentifiers();
            while (portList.hasMoreElements()) {
                portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals("/dev/ttyUSB0")) {
                        try {
                            serialPort = (SerialPort) portId.open("readapp32",
                                    2000);
                        } catch (PortInUseException e) {
                            System.out.println(e.getMessage());
                        }
                        try {
                            inputStream = serialPort.getInputStream();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        try {
                            serialPort.addEventListener(this);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        serialPort.notifyOnDataAvailable(true);
                        try {
                            serialPort.setSerialPortParams(9600,
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        }

        public void serialEvent(SerialPortEvent event) {
            switch (event.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    creadBuffer = new byte[8];
                    creadBuff = new int[8];
                    try {
                        int i = 0;
                        while (inputStream.available() > 0) {
                            creadBuff[i] = inputStream.read();
                            System.out.println("Data available is: " + creadBuff[i]);
                            i++;
                        }
                        inputStream.close();
                    } catch (IOException e) {
                    }
                    System.out.println("event");
                    System.out.println("recieved \t" + creadBuffer[0]);
                    if (creadBuffer[0] == 0x70) {
                        System.out.println("Got 0x70" + creadBuffer[0]);
                        serialPort.close();
                        rd = new read(5);
                        rd.cmainx(1);// ag
                    }
                    if (creadBuffer[0] == 0x32) {
                        System.out.println("Got" + creadBuffer[0]);
                        serialPort.close();
                    }
                    serialPort.close();
            }
        }
    }
}
    
    
//}
