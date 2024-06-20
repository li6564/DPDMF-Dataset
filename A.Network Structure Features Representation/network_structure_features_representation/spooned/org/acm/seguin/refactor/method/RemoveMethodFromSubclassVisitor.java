/* Author:  Chris Seguin

This software has been developed under the copyleft
rules of the GNU General Public License.  Please
consult the GNU General Public License for more
details about use and distribution of this software.
 */
package org.acm.seguin.refactor.method;
/**
 * Removes the method from all subclasses of a particular class.
 *
 * @author Chris Seguin
 */
public class RemoveMethodFromSubclassVisitor extends org.acm.seguin.summary.TraversalVisitor {
    private org.acm.seguin.summary.MethodSummary target;

    private org.acm.seguin.summary.TypeSummary ancestor;

    private org.acm.seguin.refactor.ComplexTransform complex;

    private org.acm.seguin.summary.TypeSummary notHere;

    /**
     * Constructor for the RemoveMethodFromSubclassVisitor object
     *
     * @param type
     * 		the ancestor type
     * @param init
     * 		the method
     * @param notThisOne
     * 		a type to skip
     */
    public RemoveMethodFromSubclassVisitor(org.acm.seguin.summary.TypeSummary type, org.acm.seguin.summary.MethodSummary init, org.acm.seguin.summary.TypeSummary notThisOne, org.acm.seguin.refactor.ComplexTransform initComplex) {
        target = init;
        ancestor = type;
        notHere = notThisOne;
        complex = initComplex;
    }

    /**
     * Visits a file summary node and updates it if necessary
     *
     * @param fileSummary
     * 		Description of Parameter
     * @param data
     * 		Description of Parameter
     * @return Description of the Returned Value
     */
    public java.lang.Object visit(org.acm.seguin.summary.FileSummary fileSummary, java.lang.Object data) {
        complex.clear();
        super.visit(fileSummary, data);
        if (complex.hasAnyChanges()) {
            java.lang.String title = (("Removing " + target.getName()) + " from children of ") + ancestor.getName();
            java.lang.String question = (("Would you like to remove\n" + target.toString()) + "\nfrom ") + fileSummary.getName();
            if (org.acm.seguin.awt.Question.isYes(title, question)) {
                complex.apply(fileSummary.getFile(), fileSummary.getFile());
            }
        }
        return data;
    }

    /**
     * Visits a type summary and updates it
     *
     * @param typeSummary
     * 		Description of Parameter
     * @param data
     * 		Description of Parameter
     * @return Description of the Returned Value
     */
    public java.lang.Object visit(org.acm.seguin.summary.TypeSummary typeSummary, java.lang.Object data) {
        if ((typeSummary != notHere) && org.acm.seguin.summary.query.Ancestor.query(typeSummary, ancestor)) {
            java.util.Iterator iter = typeSummary.getMethods();
            if (iter != null) {
                while (iter.hasNext()) {
                    visit(((org.acm.seguin.summary.MethodSummary) (iter.next())), data);
                } 
            }
        }
        return data;
    }

    /**
     * Visits the method summary and determines if it should be removed.
     *
     * @param methodSummary
     * 		Description of Parameter
     * @param data
     * 		Description of Parameter
     * @return Description of the Returned Value
     */
    public java.lang.Object visit(org.acm.seguin.summary.MethodSummary methodSummary, java.lang.Object data) {
        if (methodSummary.equals(target)) {
            complex.add(new org.acm.seguin.refactor.method.RemoveMethodTransform(target));
        }
        return data;
    }
}