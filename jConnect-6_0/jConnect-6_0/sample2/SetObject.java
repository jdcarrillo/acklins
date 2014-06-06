//
//  SetObject.java
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
 * SetObject class demonstrates how to use the setObject method<br>
 *
 * <P>SetObject.java may be invoked with the optional parameters:<br>
 * -U username<br>
 * -P password<br>
 * -D debuglibraries<br>
 * -S server<p>
 *
 *  @see Sample
 */
public class SetObject extends Sample
{

    SetObject()
    {
        super();
    }

    public void sampleCode()
    {
        String fname = "Anne";
        String lname = "Ringer";
        String query = "select au_id, au_lname, au_fname" +
            " from authors where au_lname = ? and au_fname = ?";

        try
        {

            // Demonstrate the use of setObject for defining IN params
            // with a PreparedStatement object

            PreparedStatement pstmt = _con.prepareStatement(query);
            output("Executing: " + query + "\n");

            output("Value being passed for au_lname=" + lname + "\n");
            pstmt.setObject(1, lname);

            // Do the same thing, but simply specify the SQL Type mapping

            output("Value being passed for au_fname=" + fname + "\n");
            pstmt.setObject(2, fname, Types.CHAR);

            ResultSet rs = pstmt.executeQuery ();
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
