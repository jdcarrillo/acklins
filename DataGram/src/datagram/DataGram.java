/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datagram;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author root
 */
public class DataGram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        /*
        
         byte[] ary = new byte[128];
         DatagramPacket pack = new DatagramPacket(ary, 128);

         // read
         DatagramSocket sock = new DatagramSocket(8080);
         sock.receive(pack);
         String word = new String(pack.getData());
         System.out.println("From: " + pack.getAddress() + " Port: " + pack.getPort());
         System.out.println(word);
         sock.close();
         // write
         sock = new DatagramSocket();
         pack.setAddress(InetAddress.getByName(args[1]));
         pack.setData(args[2].getBytes());
         pack.setPort(8080);
         sock.send(pack);
         sock.close();
         */
//        Socket MyClient;
//    try {
//           MyClient = new Socket("Machine name", 8300);
//    }
//    catch (IOException e) {
//        System.out.println(e);
//    }
        
        
        
            //byte[] inbuf = new byte[256]; // default size
//    DatagramSocket socket = new DatagramSocket(445);
//    DatagramPacket packet = new DatagramPacket(inbuf, inbuf.length);
//    socket.receive(packet);
//    int numBytesReceived = packet.getLength();
//    System.out.println(numBytesReceived);
//  }
        
        
        
        /////////////////////
//        ServerSocket MyService;
//        Socket clientSocket = null;
//        try {
//            MyService = new ServerSocket(32768); //re 33537 33536
//            System.out.println("abriendo "+MyService);
//            clientSocket = MyService.accept();
//            System.out.println("recepcion "+clientSocket);
//            DataInputStream input;
//            String descripcion;
//            try {
//                input = new DataInputStream(clientSocket.getInputStream());
//                while ((descripcion=input.readLine())!=null) {
//                    System.out.println("descripcion "+descripcion);
//      }
//                //System.out.println("input "+input.toString());
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//////////////
        
        
        DatagramSocket socket = new DatagramSocket();
    byte[] data = new byte[1];
InetAddress address = InetAddress.getByName("192.168.0.72");
DatagramPacket pack = null;
pack = new DatagramPacket(data, data.length, address, 2323);
        socket.receive(pack);
        int i=0;
        while (data.length>data.length+1){
            i++;
            System.out.println("data " +data[i]);}
        
        
    }



    
}
