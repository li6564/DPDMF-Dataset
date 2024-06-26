package org.acm.seguin.uml;
/**
 * Icon that draws the interface symbol
 *
 * @author Chris Seguin
 */
public class InterfaceIcon extends org.acm.seguin.uml.UMLIcon {
    /**
     * Constructor for the InterfaceIcon object
     *
     * @param wide
     * 		the size of the icon
     * @param high
     * 		the size of the icon
     */
    public InterfaceIcon(int wide, int high) {
        super(wide, high);
    }

    /**
     * Draws the icon
     *
     * @param c
     * 		The component on which we are drawing
     * @param g
     * 		The graphics object
     * @param x
     * 		the x location
     * @param y
     * 		the y location
     */
    public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
        // Set the color to black
        g.setColor(java.awt.Color.black);
        // Draw the icons
        int circleHeight = ((int) ((0.6 * iconHeight) * scale));
        int xOffset = ((int) (scale * (1 + ((iconWidth - circleHeight) * 0.5))));
        int scaledHeight = ((int) (scale * iconHeight));
        g.drawOval(x + xOffset, y, circleHeight, circleHeight);
        g.drawLine((x + xOffset) + (circleHeight / 2), y + circleHeight, (x + xOffset) + (circleHeight / 2), y + scaledHeight);
    }
}