import java.awt.*;

public class Line {
    private final Color colour;
    private int x, y, x2, y2;
    private Point centre;

    public Line(int x, int y, int x2, int y2, Color colour) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.colour = colour;
        setCentre(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(colour);
        g.drawLine(getCentre().x, getCentre().y, x2, y2);
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(int xCoordinate, int yCoordinate) {
        Point p = new Point(xCoordinate, yCoordinate);
        this.centre = p;
    }
}

