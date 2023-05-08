import java.awt.*;

/**
 * Square class extends Shape class
 */
public class Square extends Shape{
    private int length;

    /**
     * Constructs square object with specified parameters
     * @param x coordinate of square
     * @param y coordinate of square
     * @param length (int) of square
     * @param filledIn filled in or not
     * @param colour of square
     */
    public Square(int x, int y, int length, boolean filledIn, Color colour) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.filledIn = filledIn;
        this.colour = colour;
    }

    /**
     * Draws square object using graphics object
     * @param g graphics object
     */
    public void draw(Graphics g) {
        g.setColor(colour);
        if (filledIn)
            g.fillRect(x,y,length, length);
        else
            g.drawRect(x,y,length, length);
    }
}
