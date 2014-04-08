/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valida;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Juanda
 */
public class Sybase {
     public static void main(String[] args) throws SQLException {
        // uid - user id
        // pwd - password
        // eng - Sybase database server name
        // database - sybase database name
        // host - database host machine ip
        String dburl = "jdbc:sqlanywhere:uid=DBA;pwd=banana1;eng=pixelsqlbase;database=pixelsqlbase;links=tcpip(host=192.168.0.120:2638)";
        
        // Connect to Sybase Database
        Connection con = DriverManager.getConnection(dburl);
        Statement statement = con.createStatement();

        // We use Sybase specific select getdate() query to return date
        ResultSet rs = statement.executeQuery("SELECT GETDATE()");
        
        
        if (rs.next()) {
            Date currentDate = rs.getDate(1); // get first column returned
            System.out.println("Current Date from Sybase is : "+currentDate);
        }
        rs.close();
        statement.close();
        con.close();
    }
}
