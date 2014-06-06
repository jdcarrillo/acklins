/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;
import java.util.Scanner;
/**
 *
 * @author Juanda
 */
public class infoData {
    private String user;
    private String passw;
    private Scanner en;
    
    public infoData(String user,String passw) {
        this.user=user;
        this.passw=passw;

 }

    infoData() {
        
    }
    public String inputData(){
        Scanner en = new Scanner(System.in);
        String data;
        data=en.next();
        return data;
    }
    
    public String getUser(){
        return this.user;
    }   
    public void setUser(){
        this.user=inputData();
    }

    public void setPassw(){
        this.passw=inputData();
    }
    public String getPassw(){
        return this.passw;
    }
    
    public void user(String user,String passw){

        
    }
    
}
