/* Author:  Chris Seguin

This software has been developed under the copyleft
rules of the GNU General Public License.  Please
consult the GNU General Public License for more
details about use and distribution of this software.
 */
package org.acm.seguin.summary;
/**
 * Stores the summary of a type (either class or interface)
 *
 * @author Chris Seguin
 * @created June 6, 1999
 */
public class TypeSummary extends org.acm.seguin.summary.Summary {
    // Instance Variables
    private java.lang.String name;

    private boolean bInterface;

    private org.acm.seguin.summary.TypeDeclSummary parentClass;

    private java.util.LinkedList interfaceList;

    private java.util.LinkedList methodList;

    private java.util.LinkedList fieldList;

    private java.util.LinkedList typeList;

    private org.acm.seguin.pretty.ModifierHolder modifiers;

    /**
     * Creates a TypeSummary object
     *
     * @param parentSummary
     * 		the parent summary
     * @param typeDecl
     * 		the type declaration
     */
    public TypeSummary(org.acm.seguin.summary.Summary parentSummary, org.acm.seguin.parser.ast.SimpleNode typeDecl) {
        // Invoke parent class constructor
        super(parentSummary);
        // Initialize the variables
        name = "";
        bInterface = false;
        methodList = null;
        fieldList = null;
        typeList = null;
        interfaceList = null;
        parentClass = null;
    }

    /**
     * Set the name of this object
     *
     * @param newName
     * 		the name
     */
    public void setName(java.lang.String newName) {
        name = newName;
    }

    /**
     * Check to see if this is an interface or a class
     *
     * @return true if this is an interface
     */
    public boolean isInterface() {
        return bInterface;
    }

    /**
     * Get the name of this object
     *
     * @return the name
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Return the list of methods
     *
     * @return an iterator of methods
     */
    public java.util.Iterator getMethods() {
        if (methodList == null) {
            return null;
        }
        return methodList.iterator();
    }

    /**
     * Get the total number of methods - class and instance - that are
     *  associated with this object.
     *
     * @return the number of methods
     */
    public int getMethodCount() {
        if (methodList == null) {
            return 0;
        }
        return methodList.size();
    }

    /**
     * Return a list of fields
     *
     * @return an iterator of fields
     */
    public java.util.Iterator getFields() {
        if (fieldList == null) {
            return null;
        }
        return fieldList.iterator();
    }

    /**
     * Get the total number of fields - class and instance - that are associated
     *  with this object.
     *
     * @return the number of fields
     */
    public int getFieldCount() {
        if (fieldList == null) {
            return 0;
        }
        return fieldList.size();
    }

    /**
     * Return the iterator over the types
     *
     * @return an iterator full of types
     */
    public java.util.Iterator getTypes() {
        if (typeList == null) {
            return null;
        }
        return typeList.iterator();
    }

    /**
     * Get the total number of nested class and interface declarations that are
     *  associated with this object.
     *
     * @return the number of nested classes and interfaces
     */
    public int getTypeCount() {
        if (typeList == null) {
            return 0;
        }
        return typeList.size();
    }

    /**
     * Return a list of the types that this class/interface implements
     *
     * @return an iterator of the types
     */
    public java.util.Iterator getImplementedInterfaces() {
        if (interfaceList == null) {
            return null;
        }
        return interfaceList.iterator();
    }

    /**
     * Return the parent class
     *
     * @return the type declaration representing the parent class
     */
    public org.acm.seguin.summary.TypeDeclSummary getParentClass() {
        return parentClass;
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
     * Finds the package summary associated with this type
     *
     * @return the package summary
     */
    public org.acm.seguin.summary.PackageSummary getPackageSummary() {
        org.acm.seguin.summary.Summary current = getParent();
        while ((current != null) && (!(current instanceof org.acm.seguin.summary.PackageSummary))) {
            current = current.getParent();
        } 
        return ((org.acm.seguin.summary.PackageSummary) (current));
    }

    /**
     * Gets a field by a name
     *
     * @param name
     * 		the name of the field
     * @return the field summary
     */
    public org.acm.seguin.summary.FieldSummary getField(java.lang.String name) {
        java.util.Iterator iter = getFields();
        if (iter != null) {
            while (iter.hasNext()) {
                org.acm.seguin.summary.FieldSummary next = ((org.acm.seguin.summary.FieldSummary) (iter.next()));
                if (next.getName().equals(name)) {
                    return next;
                }
            } 
        }
        return null;
    }

    /**
     * Provide method to visit a node
     *
     * @param visitor
     * 		the visitor
     * @param data
     * 		the data for the visit
     * @return some new data
     */
    public java.lang.Object accept(org.acm.seguin.summary.SummaryVisitor visitor, java.lang.Object data) {
        return visitor.visit(this, data);
    }

    /**
     * Convert this object to a string
     *
     * @return a string
     */
    public java.lang.String toString() {
        return getName();
    }

    /**
     * Sets the modifier holder
     *
     * @param mod
     * 		the holder
     */
    protected void setModifiers(org.acm.seguin.pretty.ModifierHolder mod) {
        modifiers = mod;
    }

    /**
     * Add a method summary
     *
     * @param methodSummary
     * 		the method summary
     */
    protected void add(org.acm.seguin.summary.MethodSummary methodSummary) {
        if (methodSummary != null) {
            if (methodList == null) {
                initMethodList();
            }
            methodList.add(methodSummary);
        }
    }

    /**
     * Add a field summary
     *
     * @param fieldSummary
     * 		the field summary
     */
    protected void add(org.acm.seguin.summary.FieldSummary fieldSummary) {
        if (fieldSummary != null) {
            if (fieldList == null) {
                initFieldList();
            }
            fieldList.add(fieldSummary);
        }
    }

    /**
     * Add a type summary
     *
     * @param typeSummary
     * 		the type summary
     */
    protected void add(org.acm.seguin.summary.TypeSummary typeSummary) {
        if (typeSummary != null) {
            if (typeList == null) {
                initTypeList();
            }
            typeList.add(typeSummary);
        }
    }

    /**
     * Add an interface summary
     *
     * @param typeDeclSummary
     * 		the interface summary
     */
    protected void add(org.acm.seguin.summary.TypeDeclSummary typeDeclSummary) {
        if (typeDeclSummary != null) {
            if (interfaceList == null) {
                initInterfaceList();
            }
            interfaceList.add(typeDeclSummary);
        }
    }

    /**
     * Mark this as an interface or a class
     *
     * @param way
     * 		the way to set the interface variable
     */
    void setInterface(boolean way) {
        bInterface = way;
    }

    /**
     * Set the parent class
     *
     * @param tds
     * 		the type declaration representing the parent class
     */
    void setParentClass(org.acm.seguin.summary.TypeDeclSummary tds) {
        parentClass = tds;
    }

    /**
     * Gets the Initializer attribute of the TypeSummary object
     *
     * @param isStatic
     * 		Description of Parameter
     * @return The Initializer value
     */
    org.acm.seguin.summary.MethodSummary getInitializer(boolean isStatic) {
        java.util.Iterator iter = getMethods();
        if (iter != null) {
            while (iter.hasNext()) {
                org.acm.seguin.summary.MethodSummary next = ((org.acm.seguin.summary.MethodSummary) (iter.next()));
                if (next.isInitializer()) {
                    if (next.getModifiers().isStatic() == isStatic) {
                        return next;
                    }
                }
            } 
        }
        return createInitializer(isStatic);
    }

    /**
     * Initialize the interface list
     */
    private void initInterfaceList() {
        interfaceList = new java.util.LinkedList();
    }

    /**
     * Initialize the method list
     */
    private void initMethodList() {
        methodList = new java.util.LinkedList();
    }

    /**
     * Initialize the field list
     */
    private void initFieldList() {
        fieldList = new java.util.LinkedList();
    }

    /**
     * Initialize the type list
     */
    private void initTypeList() {
        typeList = new java.util.LinkedList();
    }

    /**
     * Description of the Method
     *
     * @param isStatic
     * 		Description of Parameter
     * @return Description of the Returned Value
     */
    private org.acm.seguin.summary.MethodSummary createInitializer(boolean isStatic) {
        // Get the current type summary
        org.acm.seguin.summary.MethodSummary methodSummary = new org.acm.seguin.summary.MethodSummary(this);
        add(methodSummary);
        // Load the method summary
        // Remember the modifiers
        org.acm.seguin.pretty.ModifierHolder holder = new org.acm.seguin.pretty.ModifierHolder();
        if (isStatic) {
            holder.add("static");
        }
        holder.add("private");
        methodSummary.setModifiers(holder);
        // Load the method names
        methodSummary.setName("***Initializer***");
        return methodSummary;
    }
}