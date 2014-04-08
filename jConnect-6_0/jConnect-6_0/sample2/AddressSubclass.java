//
//  Address.java
//
//  Copyright (c) 2001, 2004
//  Sybase, Inc.
//  One Sybase Drive, Dublin, CA 94568
//  All Rights Reserved
//

package sample2;

/**
 * The AddressSubclass class is used by the HandleObject sample, which shows
 * how to insert, update and delete data stored as a Java Object in a Sybase
 * Database.
 *
 * The HandleObject sample requires that this class and the Address class 
 * be put together into a jar and installed on the targeted server.
 * Note that this class is a subclass of Address and is included to show how
 * in/out Java Object parameters can be properly set to avoid class casting
 * errors.
 * Use the following instructions to install the jar file:
 *
 * 1) Build the samples using the Makefile (for Unix) or make_nt.bat (NT).
 * 2) Place the sample2/Address.class and sample2/AddressSubclass.class files
 *    into a jar file:
 *    a) go to the $JDBC_HOME/classes directory
 *    b) run the jar command:
 *     jar cvf0 Address.jar sample2/Address.class sample2/AddressSubclass.class
 * 3) Install it onto the server
 *    a) for ASE (SQL Server), use:
 *       $SYBASE/bin/installjava -f <dir>/Address.jar -new -U sa -P passwd
 *    b) for ASA (Anywhere):
 *       install java new jar from file '<dir>/Address.jar'
 *
 * When running HandleObject sample, add the 'Address.jar' to the classpath.
 *
 */
public class AddressSubclass extends Address implements java.io.Serializable
{
    public String _name;

    // A default constructor
    public AddressSubclass ()
    {
        _name = "Joseph Foo Bar";
    }

    // A constructor with parameters
    public AddressSubclass (String s, String z, String n)
    {
        super (s, z);
        _name = n;
    }

    // A method to return a display representation of the full address
    public String display ()
    {
        return "Street= " + _street + " ZIP= " + _zip + " Name= " + _name;
    }
}

