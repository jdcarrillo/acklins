//
//  AsciiToUniFileInputStream.java
//
//  Copyright (c) 1997, 2004
//  Sybase, Inc.
//  One Sybase Drive, Dublin, CA 94568
//  All Rights Reserved
//

package sample2;
import java.sql.*;
import java.io.*;

/**
 *
 *  AsciiToUniFileInputStream class assists in reading a byte stream and
 *  converting it so that it appears to be a unicode stream
 *
 *
 * <P>A char/varchar/longvarchar column value can be retrieved as a
 * stream of Unicode characters. This class extends PadByteInputStream by
 * converting stream of SQL char data to a stream of unicode chacters.
 * 
 * <P>How Object is created:<br>
 * The object is created when user calls ResultSet.getUnicodeStream().
 * <P>Lifetime of the object:<br>
 * It may be passed to the user (from getFooStream()), and will not be
 * garbage collected until the user discards all references to it.
 * <P>What happens when object is destroyed:<br>
 * No special action is needed on cleanup.
 * <P> This class is used by the sample program UnicodeStream.java
 *
 * @see java.io.FilterInputStream
 * @see com.sybase.jdbcx.SybResultSet#getUnicodeStream
 * @see Sample
 * @see UnicodeStream
 */
public class AsciiToUniFileInputStream extends FilterInputStream
{

    protected int _padByteLengthRemaining;
    // Array holding two consecutive read() return values. Each read() returns
    // one of the slots in this array
    protected int _bytes[];
    // This boolean flag indicates which of the two slots in the above
    // byte[] should be returned from the read.
    protected boolean _even;

    // Constructors:
    /**
    * <P> Create a new AsciiToUniFileInputStream
    * @param stream the FileInputStream this is a wrapper on
    * @param length how many characters are in the stream
    */
    public AsciiToUniFileInputStream(FileInputStream stream, int length )
        throws IOException
    {

        super(stream);
        _bytes = new int[2];
        _padByteLengthRemaining = length * 2;
        _even = true;
    }

    /**
     * <P>Return the next byte of a Unicode character.
     * @exception IOException .
     */
    public int read() throws IOException
    {
        //* DONE
        if (_padByteLengthRemaining == 0)
        {
            return -1;
        }
        if (_even)
        {
            _bytes[1] = super.read();
            if (-1 == _bytes[1])
            {
                return -1;
            }
        }
        _padByteLengthRemaining--;
        _even = !_even;
        return _even ? _bytes[1] : _bytes[0];
    }


}
// end of class AsciiToUniFileInputStream
