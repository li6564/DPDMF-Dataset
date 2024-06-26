/* Fri Feb 28 07:47:05 1997  Doug Lea  (dl at gee)
Based on ScribbleTool
 */
package CH.ifa.draw.contrib;
/**
 */
public class PolygonTool extends CH.ifa.draw.standard.AbstractTool {
    private CH.ifa.draw.contrib.PolygonFigure fPolygon;

    private int fLastX;

    private int fLastY;

    public PolygonTool(CH.ifa.draw.framework.DrawingView view) {
        super(view);
    }

    public void activate() {
        super.activate();
        fPolygon = null;
    }

    public void deactivate() {
        super.deactivate();
        if (fPolygon != null) {
            fPolygon.smoothPoints();
            if (((fPolygon.pointCount() < 3) || (fPolygon.size().width < 4)) || (fPolygon.size().height < 4))
                drawing().remove(fPolygon);

        }
        fPolygon = null;
    }

    private void addPoint(int x, int y) {
        if (fPolygon == null) {
            fPolygon = new CH.ifa.draw.contrib.PolygonFigure(x, y);
            view().add(fPolygon);
            fPolygon.addPoint(x, y);
        } else if ((fLastX != x) || (fLastY != y))
            fPolygon.addPoint(x, y);

        fLastX = x;
        fLastY = y;
    }

    public void mouseDown(java.awt.event.MouseEvent e, int x, int y) {
        // replace pts by actual event pts
        x = e.getX();
        y = e.getY();
        if (e.getClickCount() >= 2) {
            if (fPolygon != null) {
                fPolygon.smoothPoints();
                editor().toolDone();
            }
            fPolygon = null;
        } else {
            // use original event coordinates to avoid
            // supress that the scribble is constrained to
            // the grid
            addPoint(e.getX(), e.getY());
        }
    }

    public void mouseMove(java.awt.event.MouseEvent e, int x, int y) {
        if (fPolygon != null) {
            if (fPolygon.pointCount() > 1) {
                fPolygon.setPointAt(new java.awt.Point(x, y), fPolygon.pointCount() - 1);
                view().checkDamage();
            }
        }
    }

    public void mouseDrag(java.awt.event.MouseEvent e, int x, int y) {
        // replace pts by actual event pts
        x = e.getX();
        y = e.getY();
        addPoint(x, y);
    }

    public void mouseUp(java.awt.event.MouseEvent e, int x, int y) {
    }
}