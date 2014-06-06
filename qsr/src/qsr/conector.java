package qsr;
import java.net.*;
import java.io.*;

public class conector {
    //MONITOR
    ServerSocket server;
    Socket socket;
    String ip="127.0.0.1";
    int puerto = 32768;
    
    
    DataOutputStream salida;
    BufferedReader entrada;
    public void iniciar() throws SocketException, IOException
    {
        DatagramSocket escucha = new DatagramSocket(puerto);
        byte [] dato= new byte[1024];
        DatagramPacket dgp = new DatagramPacket(dato,dato.length);
        escucha.receive(dgp);
        byte[] datos = dgp.getData();
        System.out.println(datos);       
        
//        try 
//        {
//            server = new ServerSocket(puerto);
//            socket = new Socket();
//            socket = server.accept();            
//            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String mensaje = entrada.readLine();
//            System.out.println(mensaje);
//            //salida=new DataOutputStream(socket.getOutputStream());
//            //salida.writeUTF("MAREADO");
//            socket.close();            
//        }
//        catch(Exception e)
//        {
//            
//        }
    }
 
}
