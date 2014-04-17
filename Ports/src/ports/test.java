/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ports;

import jssc.SerialPort;
import jssc.SerialPortException;



/**
 *
 * @author root
 */
public class test {
    public static void main(String[] args) {
      SerialPort serialPort = new SerialPort("/dev/ttyS0");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(9600, 8, 1, 0);//Set params.
            byte[] buffer = serialPort.readBytes(8);//Read 10 bytes from serial port
            serialPort.closePort();//Close serial port
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }

    }
}
