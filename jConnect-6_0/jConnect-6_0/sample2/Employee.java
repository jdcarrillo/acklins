//
//  Employee.java
//
//  Copyright (c) 1997, 2004
//  Sybase, Inc.
//  One Sybase Drive, Dublin, CA 94568
//  All Rights Reserved
//


/**
 * Class used to insert data into the ASA or ASE table staff.
 * This is used via LoaderSample.java
 */
package sample2;
public class Employee implements Person, java.io.Serializable
{
    private String _firstName;
    private String _lastName;
    private String _middleInitial;

    public Employee(String fn, String ln, String mi) 
    {
        super();
        _firstName = fn;
        _lastName = ln;
        _middleInitial = mi;
    }

    public void setFirstName(String fn) 
    {
        _firstName = fn;
    }

    public String getFirstName() 
    {
        return _firstName;
    }

    public void setLastName(String ln) 
    {
        _lastName = ln;
    }

    public String getLastName() 
    {
        return _lastName;
    }

    public void setMiddleInitial(String mi) 
    {
        _middleInitial = mi;
    }

    public String getMiddleInitial() 
    {
        return _middleInitial;
    }

    public String toString() 
    {
        return getFirstName() + " " + getMiddleInitial() + " " + getLastName();
    }
}
