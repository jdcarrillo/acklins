/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainjssc;

//import gnu.io.SerialPort;
import jssc.*;

public class MainJssc {

    private static SerialPort serialPort;
    
    public static void main(String[] args) {
        serialPort = new SerialPort("/dev/ttyUSB0") ;
        try {
            serialPort.openPort();
            //serialPort.setParams(9600, 8, 1, 0);
            while(true){
                System.out.println("data: "+getByte());
            }
        }
        catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }

    protected static byte getByte() throws jssc.SerialPortException {
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
        return (byte)(serialPort.readBytes(1))[0];
    }
}
