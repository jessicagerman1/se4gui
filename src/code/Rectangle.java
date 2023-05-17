package code;

import java.awt.*;

/**
 *Rectangle class extends Shape class
 * Information about the width, height, colour, if it's filled in are here
 */
public class Rectangle extends Shape {
    private int width, height;

    /**
     * Constructs new rectangle object
     * @param x coordinate of rectangle
     * @param y coordinate of rectangle
     * @param width (int) of rectangle
     * @param height (int) of rectangle
     * @param filledIn boolean if it's filled in
     * @param colour of rectangle
     */
    public Rectangle(int x, int y, int width, int height, boolean filledIn, Color colour) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.filledIn = filledIn;
        this.colour = colour;
    }

    /**
     *Draws rectangle using specified graphics object
     * @param g graphics object
     */
    public void draw(Graphics g) {
        g.setColor(colour);
        if (filledIn)
            g.fillRect(x,y,width, height);
        else
            g.drawRect(x,y,width,height);
    }
}