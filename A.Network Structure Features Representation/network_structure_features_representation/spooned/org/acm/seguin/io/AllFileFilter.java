/* Author:  Chris Seguin

 This software has been developed under the copyleft
 rules of the GNU General Public License.  Please
 consult the GNU General Public License for more
 details about use and distribution of this software.
 */
package org.acm.seguin.io;
/**
 * Accepts all files
 *
 * @author Chris Seguin
 * @created May 30, 1999
 */
public class AllFileFilter extends javax.swing.filechooser.FileFilter {
    /**
     * Return the description of the files accepted
     *
     * @return the description to be displayed in the file box
     */
    public java.lang.String getDescription() {
        return "All Files (*.*)";
    }

    /**
     * Should this file be accepted
     *
     * @param file
     * 		the file under consideration
     * @return true - all files are accepted
     */
    public boolean accept(java.io.File file) {
        return true;
    }
}