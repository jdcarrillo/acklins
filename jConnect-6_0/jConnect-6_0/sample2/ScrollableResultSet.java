//
//  ScrollableResultSet.java
//
//  Copyright (c) 1997, 2004
//  Sybase, Inc.
//  One Sybase Drive, Dublin, CA 94568
//  All Rights Reserved
//

package sample2;
import java.sql.*;

/**
* <P>This interface is an extension of the JDBC 1.0 
* <CODE>java.sql.ResultSet</CODE> interface.  The purpose is to define
* the additional methods for scrollable behavior.  The methods are duplicates
* of the methods added to the JDBC 2.0 API for the <CODE>ResultSet</CODE>
* interface.  However, this interface is a subset of the JDBC 2.0 ResultSet.
* This interface does not provide update or delete methods.
* <P>
* The constants defined on this interface have the same semantics as those
* found in the JDBC 2.0 API for java.sql.ResultSet. No guarantee is made
* that ScrollableResultSet.FETCH_FORWARD == ResultSet.FETCHFORWARD.
*
* @see java.sql.ResultSet
*/
public interface ScrollableResultSet extends java.sql.ResultSet
{

}
