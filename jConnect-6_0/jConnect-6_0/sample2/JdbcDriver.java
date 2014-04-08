//
//  JdbcDriver.java
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
 * JdbcDriver class demonstrates how to use the System property "jdbc.drivers"
 * to load the jConnect driver.<br>
 *
 * <P>JdbcDriver may be invoked with the optional parameters:<br>
 * -U username<br>
 * -P password<br>
 * -D debuglibraries<br>
 * -S server<p>
 *
 *  @see Sample
 */
public class JdbcDriver extends Sample
{

    JdbcDriver()
    {
        super();
    }

    public void run()
    {

        try
        {

            addMoreProps(_cmdline);

            // Define the driver using the System Property

            Properties sysProps = System.getProperties();
            StringBuffer drivers = new 
                StringBuffer("com.sybase.jdbc3.jdbc.SybDriver");

            String oldDrivers = sysProps.getProperty("jdbc.drivers");

            if (oldDrivers != null)
            drivers.append(":" + oldDrivers);

            sysProps.put("jdbc.drivers", drivers.toString());



            //  Attempt to connect to a driver.  This will also Load the
            //  jConnect driver.
            _con = DriverManager.getConnection(
                _cmdline._props.getProperty("server"), _cmdline._props);

            // If we were unable to connect, an exception
            // would have been thrown.  So, if we get here,
            // we are successfully connected to the URL

            // Check for, and display and warnings generated
            // by the connect.

            checkForWarning (_con.getWarnings ());

            // Run the sample specific code
            sampleCode();

            // Close the connection

            _con.close();
        }
        catch (SQLException ex)
        {
            displaySQLEx(ex);
        }
        catch (java.lang.Exception ex)
        {

            // Got some other type of exception.  Dump it.

            ex.printStackTrace ();
        }
    }

    public void sampleCode()
    {
        try
        {

            // Get the DatabaseMetaData object and display
            // some information about the connection

            DatabaseMetaData dma = _con.getMetaData ();

            output("\nConnected to " + dma.getURL() + "\n");
            output("Driver       " + dma.getDriverName() + "\n");
            output("Version      " + dma.getDriverVersion() + "\n");

        }
        catch (SQLException ex)
        {
            displaySQLEx(ex);
        }
    }
}
