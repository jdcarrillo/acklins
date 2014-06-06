/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

/**
 *
 * @author Juanda
 */
public class Datos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String user=null;
        String passw=null;
        
        infoData info = new infoData();
        
        info.setUser();
        user=info.getUser();
        info.setPassw();
        passw=info.getPassw();
        //password=info.
        System.out.println("USUARIO: " + user );
        System.out.println("PASSWORD: " + passw );
        
        
    }
    
}
