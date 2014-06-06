//
//  AutoCommit.java
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
 * AutoCommit class demonstrates how to use the Connection methods
 * getAutoCommit(), setAutoCommit(), rollback() and commit().
 * <p>
 *
 * AutoCommit may be invoked with the optional parameters:<br>
 * -U username<br>
 * -P password<br>
 * -D debuglibraries<br>
 * -S server<p>
 *
 *  @see Sample
 */

public class AutoCommit extends Sample
{

    AutoCommit()
    {
        super();
    }

    public void sampleCode()
    {

        String createQuery = "create table #committest(f1 int, f2 char(5))";

        try
        {
            // use tempdb as pubs2 is read only on the jConnect demo server
            execDDL("use tempdb");

            // Create our table
            execDDL(createQuery);

            // get the current commit state
            output("getAutoCommit= " +  _con.getAutoCommit() + "\n");
            execTranTest(true);

            output("Executing setAutoCommit(false)\n");
            _con.setAutoCommit(false);

            output("getAutoCommit= " +  _con.getAutoCommit() + "\n");
            execTranTest(false);

            // reset AutoCommit
            _con.setAutoCommit(true);

        }
        catch (SQLException ex)
        {
            displaySQLEx(ex);
        }
    }
    /**
     * Execute a series of inserts, sp_lock and select @@tranchained and
     * then a commit() and rollback()
     * The sp_lock output will show that we are in a chained transaction as well
     * @param autoCommitState   true when setAutoCommit(true)<br>
     *                          false when setAutoCommit(false)
     * @exception SQLException .
     *
     */
    public void execTranTest (boolean autoCommitState)
        throws SQLException
    {
        String insertQuery = "insert #committest values(1, '***test***')";
        String query = "select  * from #committtest";
        String checkTranChained ="select @@tranchained";
        String checkLocks  = "sp_lock";

        // Display the current transtate
        displayRows(checkTranChained);

        // Insert a row  and display our locks
        output("Executing: an insert()\n");
        execDDL(insertQuery);
        displayRows(checkLocks);

        // Commit our Transaction and display our locks again
        if(!autoCommitState)
        {
            output("Executing: Connection.commit()\n");
            _con.commit();
            displayRows(checkLocks);
        }
        //  Insert a row
        output("Executing: an insert()\n");
        execDDL(insertQuery);
        displayRows(checkLocks);

        // Rollback our Transaction and display our locks again
        if(!autoCommitState)
        {
            output("Executing: Connection.rollback()\n");
            _con.rollback();
            displayRows(checkLocks);
        }
    }
    /**
     * Execute the desired DML statement and then call dispResultSet to
     * display the rows and columns
     * @param query  The SQL query to be executed
     * @exception SQLException .
     */

    public void displayRows(String query)
        throws SQLException
    {

        Statement stmt = _con.createStatement();;
        output("Executing: " + query + "\n");
        ResultSet rs = stmt.executeQuery (query);
        dispResultSet(rs);
        rs.close();
        stmt.close();
    }


}
