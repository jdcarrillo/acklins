//
//  MyPrepare.java
//
//  Copyright (c) 1997, 2004
//  Sybase, Inc.
//  One Sybase Drive, Dublin, CA 94568
//  All Rights Reserved
//

package sample2;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * MyPrepare class demonstrates how to use a PreparedStatement<br>
 *
 * <P>MyPrepare may be invoked with the optional parameters:<br>
 * -U username<br>
 * -P password<br>
 * -D debuglibraries<br>
 * -S server<p>
 *
 *  @see Sample
 */
public class MyPrepare extends Sample
{
    static public final int   MAXROWS = 5;

    MyPrepare()
    {
        super();
    }

    public void sampleCode()
    {
        String fname[] = 
        {
            "Ringer", "Green"
        }
        ;
        String query =
            "select au_id, au_lname, au_fname from authors where au_lname = ?";
        String queryNull =
            "select  au_lname, au_fname, city from authors where city != ?";

        try
        {

            // In this Example, we will demonstrate the use of re-using
            // a PreparedStatement.  

            ResultSet rs = null;
            PreparedStatement pstmt = _con.prepareStatement(query);
            output("Executing: " + query + "\n");

            for(int i=0; i < fname.length; i++)
            {
                output("au_lname=" + fname[i] + "\n");
                pstmt.setString(1, fname[i]);

                rs = pstmt.executeQuery ();
                dispResultSet(rs);

                // Clear our IN params

                pstmt.clearParameters();
                rs.close();
            }

            pstmt.close();

            // In this example we are using setNull() to specify that we want
            // a SQL NULL to be sent to the RDBMS

            pstmt = _con.prepareStatement(queryNull);
            output("Executing: " + queryNull + "\n");
            output("city= NULL\n"  );
            pstmt.setNull(1, java.sql.Types.LONGVARCHAR);

            // Display only a subset of the rows 
            pstmt.setMaxRows(MAXROWS);
            rs = pstmt.executeQuery ();
            dispResultSet(rs);
            rs.close();
            pstmt.close();

        }
        catch (SQLException ex)
        {
            displaySQLEx(ex);
        }
    }


}
