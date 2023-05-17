package code;

import java.awt.*;

/**
 * code.Circle class extends Shape class
 */
public class Circle extends Shape {
    private int radius;

    /**
     *Constructs circle object with specified params
     * @param x coordinate of centre of circle
     * @param y coordinate of centre of circle
     * @param radius of circle
     * @param filledIn boolean if it's filled in or not
     * @param colour of circle
     */
    public Circle(int x, int y, int radius, boolean filledIn, Color colour) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.filledIn = filledIn;
        this.colour = colour;
    }

    /**
     *Draws circle using graphics object
     * @param g graphics object
     */
    public void draw(Graphics g) {
        g.setColor(colour);
        if (filledIn) {
            g.fillOval(x, y, radius * 2, radius * 2);
        }
        else {
            g.drawOval(x, y, radius * 2, radius * 2);
        }
    }
}