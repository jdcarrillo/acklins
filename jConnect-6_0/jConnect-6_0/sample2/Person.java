//
//  Person.java
//
//  Copyright (c) 1997, 2004
//  Sybase, Inc.
//  One Sybase Drive, Dublin, CA 94568
//  All Rights Reserved
//


/**
 * Interface class used by LoaderSample.java.
 * This must be in your CLASSPATH prior to running LoaderSample
 */
package sample2;
public interface Person 
{
    public void setFirstName(String fn);
    public String getFirstName();
    public void setLastName(String ln);
    public String getLastName();
    public void setMiddleInitial(String mi);
    public String getMiddleInitial() ;
    public String toString();
}
