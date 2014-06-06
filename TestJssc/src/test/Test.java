/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Test {

    private static SerialPort serialPort;

    public static void main(String[] args) {
        //Передаём в конструктор имя порта
        serialPort = new SerialPort("/dev/ttyUSB0");
        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
                                          SerialPort.FLOWCONTROL_RTSCTS_OUT);
            
//            while(true){
//                System.out.println(getByte());
//            }
            
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            serialPort.writeString("0");
            System.out.println("read "+serialPort.readString());
            
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
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
    
    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() &&event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue());
                    //И снова отправляем запрос
                    serialPort.writeString("Get data");
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}