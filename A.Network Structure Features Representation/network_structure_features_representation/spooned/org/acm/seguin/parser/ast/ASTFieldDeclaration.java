/* Author:  Chris Seguin

 This software has been developed under the copyleft
 rules of the GNU General Public License.  Please
 consult the GNU General Public License for more
 details about use and distribution of this software.
 */
package org.acm.seguin.parser.ast;
/**
 * Holds a field declaration.  The two components that this structure
 *  store are the modifiers to the field and any javadoc comments associated
 *  with the field.
 *
 * @author Chris Seguin
 * @created October 13, 1999
 */
public class ASTFieldDeclaration extends org.acm.seguin.parser.ast.SimpleNode implements org.acm.seguin.pretty.JavaDocable {
    // Instance Variables
    private org.acm.seguin.pretty.ModifierHolder modifiers;

    private org.acm.seguin.pretty.JavaDocableImpl jdi;

    /**
     * Constructor for the ASTFieldDeclaration object
     *
     * @param id
     * 		Description of Parameter
     */
    public ASTFieldDeclaration(int id) {
        super(id);
        modifiers = new org.acm.seguin.pretty.ModifierHolder();
        jdi = new org.acm.seguin.pretty.JavaDocableImpl();
    }

    /**
     * Constructor for the ASTFieldDeclaration object
     *
     * @param p
     * 		Description of Parameter
     * @param id
     * 		Description of Parameter
     */
    public ASTFieldDeclaration(org.acm.seguin.parser.JavaParser p, int id) {
        super(p, id);
        modifiers = new org.acm.seguin.pretty.ModifierHolder();
        jdi = new org.acm.seguin.pretty.JavaDocableImpl();
    }

    /**
     * Determine if the object is abstract
     *
     * @return true if this stores an ABSTRACT flag
     */
    public boolean isAbstract() {
        return modifiers.isAbstract();
    }

    /**
     * Determine if the object is explicit
     *
     * @return true if this stores an EXPLICIT flag
     */
    public boolean isExplicit() {
        return modifiers.isExplicit();
    }

    /**
     * Determine if the object is final
     *
     * @return true if this stores an FINAL flag
     */
    public boolean isFinal() {
        return modifiers.isFinal();
    }

    /**
     * Determine if the object is interface
     *
     * @return true if this stores an INTERFACE flag
     */
    public boolean isInterface() {
        return modifiers.isInterface();
    }

    /**
     * Determine if the object is native
     *
     * @return true if this stores an NATIVE flag
     */
    public boolean isNative() {
        return modifiers.isNative();
    }

    /**
     * Determine if the object is private
     *
     * @return true if this stores an PRIVATE flag
     */
    public boolean isPrivate() {
        return modifiers.isPrivate();
    }

    /**
     * Determine if the object is protected
     *
     * @return true if this stores an PROTECTED flag
     */
    public boolean isProtected() {
        return modifiers.isProtected();
    }

    /**
     * Determine if the object is public
     *
     * @return true if this stores an PUBLIC flag
     */
    public boolean isPublic() {
        return modifiers.isPublic();
    }

    /**
     * Determine if the object is static
     *
     * @return true if this stores an static flag
     */
    public boolean isStatic() {
        return modifiers.isStatic();
    }

    /**
     * Determine if the object is strict
     *
     * @return true if this stores an STRICT flag
     */
    public boolean isStrict() {
        return modifiers.isStrict();
    }

    /**
     * Determine if the object is synchronized
     *
     * @return true if this stores an SYNCHRONIZED flag
     */
    public boolean isSynchronized() {
        return modifiers.isSynchronized();
    }

    /**
     * Determine if the object is transient
     *
     * @return true if this stores an TRANSIENT flag
     */
    public boolean isTransient() {
        return modifiers.isTransient();
    }

    /**
     * Determine if the object is volatile
     *
     * @return true if this stores an VOLATILE flag
     */
    public boolean isVolatile() {
        return modifiers.isVolatile();
    }

    /**
     * Returns a string containing all the modifiers
     *
     * @return the iterator
     */
    public java.lang.String getModifiersString() {
        return modifiers.toString();
    }

    /**
     * Returns the modifier holder
     *
     * @return the holder
     */
    public org.acm.seguin.pretty.ModifierHolder getModifiers() {
        return modifiers;
    }

    /**
     * Checks to see if it was printed
     *
     * @return true if it still needs to be printed
     */
    public boolean isRequired() {
        org.acm.seguin.pretty.ForceJavadocComments fjc = new org.acm.seguin.pretty.ForceJavadocComments();
        return jdi.isRequired() && fjc.isJavaDocRequired("field", modifiers);
    }

    /**
     * Adds a modifier to a class
     *
     * @param modifier
     * 		the next modifier
     */
    public void addModifier(java.lang.String modifier) {
        modifiers.add(modifier);
    }

    /**
     * Convert this object to a string
     *
     * @return a string representing this object
     */
    public java.lang.String toString() {
        return ((super.toString() + " [") + getModifiersString()) + "]";
    }

    /**
     * Allows you to add a java doc component
     *
     * @param component
     * 		the component that can be added
     */
    public void addJavaDocComponent(org.acm.seguin.pretty.JavaDocComponent component) {
        jdi.addJavaDocComponent(component);
    }

    /**
     * Prints all the java doc components
     *
     * @param printData
     * 		the print data
     */
    public void printJavaDocComponents(org.acm.seguin.pretty.PrintData printData) {
        org.acm.seguin.util.FileSettings bundle = org.acm.seguin.util.FileSettings.getSettings("Refactory", "pretty");
        jdi.printJavaDocComponents(printData, bundle.getString("field.tags"));
    }

    /**
     * Makes sure all the java doc components are present
     */
    public void finish() {
        // Get the resource bundle
        org.acm.seguin.util.FileSettings bundle = org.acm.seguin.util.FileSettings.getSettings("Refactory", "pretty");
        // Description of the field
        jdi.require("", bundle.getString("field.descr"));
        // Require the other tags
        org.acm.seguin.parser.ast.ASTVariableDeclarator decl = ((org.acm.seguin.parser.ast.ASTVariableDeclarator) (jjtGetChild(1)));
        org.acm.seguin.parser.ast.ASTVariableDeclaratorId id = ((org.acm.seguin.parser.ast.ASTVariableDeclaratorId) (decl.jjtGetChild(0)));
        org.acm.seguin.pretty.ai.RequiredTags.getTagger().addTags(bundle, "field", id.getName(), jdi);
    }

    /**
     * Accept the visitor.
     *
     * @param visitor
     * 		Description of Parameter
     * @param data
     * 		Description of Parameter
     * @return Description of the Returned Value
     */
    public java.lang.Object jjtAccept(org.acm.seguin.parser.JavaParserVisitor visitor, java.lang.Object data) {
        return visitor.visit(this, data);
    }

    /**
     * Return true if the javadoc comments were printed
     */
    public boolean isJavadocPrinted() {
        return jdi.isPrinted();
    }
}