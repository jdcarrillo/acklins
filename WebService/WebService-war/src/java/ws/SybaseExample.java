/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

// Example Java Program - Sybase SQL Anywhere 12 Database Connectivity with JDBC 4.0
import java.sql.*;

public class SybaseExample {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // uid - user id
        // pwd - password
        // eng - Sybase database server name
        // database - sybase database name
        // host - database host machine ip
        //String dburl = "jdbc:sqlanywhere:uid=dba;pwd=banana1;eng=PixelSqlBase;database=PixelSqlBaseV11;links=tcpip(host=192.168.0.105)";
String path = System.getProperty("java.library.path");
System.out.println(path);
        DriverManager.registerDriver((Driver) Class.forName("sybase.jdbc.sqlanywhere.IDriver").newInstance());
        // uid - user id
        // pwd - password
        // eng - Sybase database server name
        // database - sybase database name
        // host - database host machine ip

        //String dburl = "jdbc:sqlanywhere:uid=DBA;pwd=DBA;eng=devdb;database=devdb;links=tcpip(host=172.20.20.20)";
        String dburl = "jdbc:sqlanywhere:uid=dba;pwd=banana1;eng=PixelSqlBase;database=PixelSqlBaseV11;links=tcpip(host=192.168.0.105)";

        // Connect to Sybase Database
        Connection con = DriverManager.getConnection(dburl);
        Statement statement = con.createStatement();

        // We use Sybase specific select getdate() query to return date
        ResultSet rs = statement.executeQuery("SELECT GETDATE()");

        if (rs.next()) {
            Date currentDate = rs.getDate(1); // get first column returned
            System.out.println("Current Date from Sybase is : " + currentDate);
        }
        rs.close();
        statement.close();
        con.close();
    }
}
