import java.awt.*;

public class Square {
    private int x, y, length;
    private boolean filledIn;
    private Color colour;

    public Square(int x, int y, int length, boolean filledIn, Color colour) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.filledIn = filledIn;
        this.colour = colour;
    }
    public void draw(Graphics g) {
        g.setColor(colour);
        if (filledIn)
            g.fillRect(x,y,length, length);
        else
            g.drawRect(x,y,length, length);
    }
}