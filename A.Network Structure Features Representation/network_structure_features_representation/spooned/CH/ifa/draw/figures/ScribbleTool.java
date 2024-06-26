/* @(#)ScribbleTool.java 5.1 */
package CH.ifa.draw.figures;
/**
 * Tool to scribble a PolyLineFigure
 *
 * @see PolyLineFigure
 */
public class ScribbleTool extends CH.ifa.draw.standard.AbstractTool {
    private CH.ifa.draw.figures.PolyLineFigure fScribble;

    private int fLastX;

    private int fLastY;

    public ScribbleTool(CH.ifa.draw.framework.DrawingView view) {
        super(view);
    }

    public void activate() {
        super.activate();
        fScribble = null;
    }

    public void deactivate() {
        super.deactivate();
        if (fScribble != null) {
            if ((fScribble.size().width < 4) || (fScribble.size().height < 4))
                drawing().remove(fScribble);

        }
    }

    private void point(int x, int y) {
        if (fScribble == null) {
            fScribble = new CH.ifa.draw.figures.PolyLineFigure(x, y);
            view().add(fScribble);
        } else if ((fLastX != x) || (fLastY != y))
            fScribble.addPoint(x, y);

        fLastX = x;
        fLastY = y;
    }

    public void mouseDown(java.awt.event.MouseEvent e, int x, int y) {
        if (e.getClickCount() >= 2) {
            fScribble = null;
            editor().toolDone();
        } else {
            // use original event coordinates to avoid
            // supress that the scribble is constrained to
            // the grid
            point(e.getX(), e.getY());
        }
    }

    public void mouseDrag(java.awt.event.MouseEvent e, int x, int y) {
        if (fScribble != null)
            point(e.getX(), e.getY());

    }
}