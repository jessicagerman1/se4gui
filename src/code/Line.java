package code;

import java.awt.*;

/**
 *code.Line class represents a line object
 */
public class Line {
    private final Color colour;
    private int x, y, x2, y2;
    private Point centre;

    /**
     * Creates a line object with set coordinates and colour
     * @param x x coordinate of start of line
     * @param y y coordinate of start of line
     * @param x2 x coordinate of end of line
     * @param y2 y coordinate of end of line
     * @param colour colour of line
     */
    public Line(int x, int y, int x2, int y2, Color colour) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.colour = colour;
        setCentre(x, y);
    }

    /**
     * Draws the line object
     * @param g graphics object
     */
    public void draw(Graphics g) {
        g.setColor(colour);
        g.drawLine(getCentre().x, getCentre().y, x2, y2);
    }

    /**
     *
     * @return centre of line object
     */
    public Point getCentre() {
        return centre;
    }

    /**
     *  Sets the centre of line object to specified coordinates
     * @param xCoordinate of the centre
     * @param yCoordinate of the centre
     */
    public void setCentre(int xCoordinate, int yCoordinate) {
        Point p = new Point(xCoordinate, yCoordinate);
        this.centre = p;
    }
}

