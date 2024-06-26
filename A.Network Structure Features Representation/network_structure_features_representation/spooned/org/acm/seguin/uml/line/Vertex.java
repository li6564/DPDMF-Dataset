/* Author:  Chris Seguin

This software has been developed under the copyleft
rules of the GNU General Public License.  Please
consult the GNU General Public License for more
details about use and distribution of this software.
 */
package org.acm.seguin.uml.line;
/**
 * Vertex
 *
 * @author Chris Seguin
 * @created July 28, 1999
 */
public class Vertex {
    private java.awt.Point point;

    private boolean selected;

    private boolean activated;

    private boolean rescaled;

    private double scale;

    private java.awt.Point computed;

    private static double near = 3.0;

    private static int vertexSizeHalf = 2;

    private static int vertexSize = 5;

    /**
     * Constructor for the Vertex object
     *
     * @param init
     * 		Description of Parameter
     */
    public Vertex(java.awt.Point init) {
        point = init;
        computed = new java.awt.Point();
        activated = false;
        selected = false;
        rescaled = true;
        scale = 1.0;
    }

    /**
     * Checks if the point is selected
     *
     * @return true if the point is selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Checks if this vertex is the active vertex
     *
     * @return true if it is
     */
    public boolean isActive() {
        return activated;
    }

    /**
     * Paints the object
     *
     * @param g
     * 		The graphics context
     */
    public void paint(java.awt.Graphics g) {
        if (activated) {
            g.setColor(java.awt.Color.magenta);
            g.fillOval(((int) (getX() - org.acm.seguin.uml.line.Vertex.vertexSizeHalf)), ((int) (getY() - org.acm.seguin.uml.line.Vertex.vertexSizeHalf)), org.acm.seguin.uml.line.Vertex.vertexSize, org.acm.seguin.uml.line.Vertex.vertexSize);
        } else if (selected) {
            g.setColor(java.awt.Color.black);
            g.fillOval(((int) (getX() - org.acm.seguin.uml.line.Vertex.vertexSizeHalf)), ((int) (getY() - org.acm.seguin.uml.line.Vertex.vertexSizeHalf)), org.acm.seguin.uml.line.Vertex.vertexSize, org.acm.seguin.uml.line.Vertex.vertexSize);
        } else {
            // Don't paint it
            // g.setColor(Color.black);
            // g.drawOval((int) point.getX(), (int) point.getY(), 1, 1);
        }
    }

    /**
     * Determines if it is hit by a point
     *
     * @param p
     * 		The point
     * @return true if this point hits this vertex
     */
    public boolean hit(java.awt.Point p) {
        double diffX = getX() - p.getX();
        double diffY = getY() - p.getY();
        double dist = java.lang.Math.sqrt((diffX * diffX) + (diffY * diffY));
        return dist < org.acm.seguin.uml.line.Vertex.near;
    }

    /**
     * Moves the vertex to p
     *
     * @param p
     * 		the destination of the move
     */
    public void move(java.awt.Point p) {
        point.x = ((int) (p.x / scale));
        point.y = ((int) (p.y / scale));
        rescaled = true;
    }

    /**
     * Save the vertex
     *
     * @param output
     * 		the output stream
     */
    public void save(java.io.PrintWriter output) {
        output.print(((("(" + point.x) + ",") + point.y) + ")");
    }

    /**
     * Shifts the point by a certain amount
     *
     * @param x
     * 		the amount in the x coordinate
     * @param y
     * 		the amount in the y coordinate
     */
    public void shift(int x, int y) {
        point.x += x;
        point.y += y;
        rescaled = true;
    }

    /**
     * Scales the vertex
     *
     * @param value
     * 		the scaling factor
     */
    public void scale(double value) {
        if (java.lang.Math.abs(value - scale) > 0.001) {
            rescaled = true;
            scale = value;
        }
    }

    /**
     * Gets the X attribute of the Vertex object
     *
     * @return The X value
     */
    protected int getX() {
        if (rescaled) {
            computed.x = ((int) (point.x * scale));
            computed.y = ((int) (point.y * scale));
            rescaled = false;
        }
        return computed.x;
    }

    /**
     * Gets the Y attribute of the Vertex object
     *
     * @return The Y value
     */
    protected int getY() {
        if (rescaled) {
            computed.x = ((int) (point.x * scale));
            computed.y = ((int) (point.y * scale));
            rescaled = false;
        }
        return computed.y;
    }

    /**
     * Gets the scaled point
     *
     * @return the scaled point
     */
    protected java.awt.Point getPoint() {
        if (rescaled) {
            computed.x = ((int) (point.x * scale));
            computed.y = ((int) (point.y * scale));
            rescaled = false;
        }
        return computed;
    }

    /**
     * Selects the point
     *
     * @param way
     * 		true if the point is selected
     */
    protected void select(boolean way) {
        selected = way;
        if (!selected) {
            activated = false;
        }
    }

    /**
     * Sets whether this is the active vertex
     *
     * @param way
     * 		true if this vertex is active
     */
    protected void active(boolean way) {
        activated = way;
        if (activated) {
            selected = true;
        }
    }

    /**
     * Sets the Near attribute of the Vertex class
     *
     * @param value
     * 		The new Near value
     */
    public static void setNear(double value) {
        org.acm.seguin.uml.line.Vertex.near = value;
    }

    /**
     * Sets the VertexSize attribute of the Vertex class
     *
     * @param value
     * 		The new VertexSize value
     */
    public static void setVertexSize(int value) {
        org.acm.seguin.uml.line.Vertex.vertexSizeHalf = value / 2;
        org.acm.seguin.uml.line.Vertex.vertexSize = value;
    }
}