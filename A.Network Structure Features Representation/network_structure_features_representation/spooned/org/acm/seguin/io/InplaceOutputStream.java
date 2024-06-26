/* Author:  Chris Seguin

 This software has been developed under the copyleft
 rules of the GNU General Public License.  Please
 consult the GNU General Public License for more
 details about use and distribution of this software.
 */
package org.acm.seguin.io;
import java.io.IOException;
/**
 * To the user of this object, it appears that the file is written in place.
 *
 * @author Chris Seguin
 * @date May 12, 1999
 */
public class InplaceOutputStream extends java.io.OutputStream {
    // Instance Variables
    private java.io.File finalDestination;

    private java.io.File temporary;

    private java.io.FileOutputStream out;

    /**
     * Creates an InplaceOutputStream
     *
     * @param dest
     * 		the output file location
     * @exception IOException
     * 		throws an IOException
     */
    public InplaceOutputStream(java.io.File dest) throws java.io.IOException {
        finalDestination = dest;
        do {
            temporary = createTempFile("inplace", ".java");
            temporary.delete();
        } while (temporary.exists() );
        out = new java.io.FileOutputStream(temporary);
    }

    /**
     * Closes the file
     *
     * @exception IOException
     * 		throws an IOException
     */
    public void close() throws java.io.IOException {
        if (out == null) {
            return;
        }
        // Close the file
        out.close();
        // Copy it inplace
        if (temporary.exists() && (temporary.length() > 0)) {
            new org.acm.seguin.io.FileCopy(temporary, finalDestination, false).run();
        }
        // Delete the temporary file
        temporary.delete();
        // Note that we are done
        out = null;
    }

    /**
     * Flush the file
     *
     * @exception IOException
     * 		throws an IOException
     */
    public void flush() throws java.io.IOException {
        if (out == null) {
            return;
        }
        out.flush();
    }

    /**
     * Write a byte to the file
     *
     * @param b
     * 		the byte to be written
     * @exception IOException
     * 		throws an IOException
     */
    public void write(int b) throws java.io.IOException {
        if (out == null) {
            return;
        }
        out.write(b);
    }

    /**
     * Write a byte array to the file
     *
     * @param b
     * 		the byte array to be written
     * @exception IOException
     * 		throws an IOException
     */
    public void write(byte[] b) throws java.io.IOException {
        if (out == null) {
            return;
        }
        out.write(b);
    }

    /**
     * Write a byte array to the file
     *
     * @param b
     * 		the byte array to be written
     * @param off
     * 		the offset into the array
     * @param len
     * 		the number of bytes to write
     * @exception IOException
     * 		throws an IOException
     */
    public void write(byte[] b, int off, int len) throws java.io.IOException {
        if (out == null) {
            return;
        }
        out.write(b, off, len);
    }

    /**
     * Make sure to clean up after itself
     */
    protected void finalize() {
        if (out == null) {
            return;
        }
        try {
            close();
        } catch (java.io.IOException ioe) {
        }
    }

    /**
     * Description of the Method
     *
     * @param prefix
     * 		Description of Parameter
     * @param suffix
     * 		Description of Parameter
     * @return Description of the Returned Value
     */
    private java.io.File createTempFile(java.lang.String prefix, java.lang.String suffix) {
        for (int ndx = 0; ndx < 1024; ndx++) {
            double number = (java.lang.Math.random() * 1024) * 1024;
            long rounded = java.lang.Math.round(number);
            java.io.File base = new java.io.File(java.lang.System.getProperty("user.dir"));
            java.io.File possible = new java.io.File(base, (prefix + rounded) + suffix);
            if (!possible.exists()) {
                return possible;
            }
        }
        return null;
    }
}