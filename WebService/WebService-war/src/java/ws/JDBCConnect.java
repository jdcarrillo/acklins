/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

/**
 *
 * @author root
 */
import java.io.*;
import java.sql.*;

public class JDBCConnect
{
    public static void main( String args[] )
    {
        try
        {
            String arg;
            Connection con;

            // Select the JDBC driver and create a connection.
            // May throw a SQLException.
            // Choices are:
            // 1. jConnect 6.0 driver
            // 2. SQL Anywhere JDBC 3.0 driver
            // 3. SQL Anywhere JDBC 4.0 driver
            arg = "jdbc4";
            if( args.length > 0 ) arg = args[0];
            if( arg.compareToIgnoreCase( "jconnect" ) == 0 )
            {
                DriverManager.registerDriver( (Driver)
                    Class.forName(
                    "com.sybase.jdbc3.jdbc.SybDriver").newInstance()
                    );
                con = DriverManager.getConnection(
                    //"jdbc:sybase:Tds:192.168.0.105:2638", "reportuser", "pixel1047");
                        "jdbc:sybase:Tds:192.168.0.105:2638?ServiceName=PixelSqlBaseV11", "reportuser", "pixel1047");
            }
            else if( arg.compareToIgnoreCase( "jdbc3" ) == 0 )
            {
                DriverManager.registerDriver( (Driver)
                    Class.forName(
                    "sybase.jdbc.sqlanywhere.IDriver").newInstance()
                    );
                con = DriverManager.getConnection(
                    "jdbc:sqlanywhere:uid=reportuser;pwd=pixel1047" );
            }
            else
            {
                con = DriverManager.getConnection(
                    "jdbc:sqlanywhere:uid=reportuser;pwd=pixel1047" );
            }

            System.out.println("Using "+arg+" driver");
            
            // Create a statement object, the container for the SQL
            // statement. May throw a SQLException.
            Statement stmt = con.createStatement();

            // Create a result set object by executing the query.
            // May throw a SQLException.
            ResultSet rs = stmt.executeQuery(
                "SELECT ID, GivenName, Surname FROM Customers");

            // Process the result set.
            while (rs.next())
            {
                int value = rs.getInt(1);
                String FirstName = rs.getString(2);
                String LastName = rs.getString(3);
                System.out.println(value+" "+FirstName+" "+LastName);
            }
            rs.close();
            stmt.close();
            con.close();
        }
        catch (SQLException sqe)
        {
            System.out.println("Unexpected exception : " +
                                sqe.toString() + ", sqlstate = " +
                                sqe.getSQLState());
            System.exit(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}