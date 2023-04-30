import java.awt.*;

public class Rectangle {
        private int x, y, width, height;
        private boolean filledIn;
        private Color colour;

    public Rectangle(int x, int y, int width, int height, boolean filledIn, Color colour) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.filledIn = filledIn;
        this.colour = colour;
    }
    public void draw(Graphics g) {
        g.setColor(colour);
        if (filledIn)
            g.fillRect(x,y,width, height);
        else
            g.drawRect(x,y,width,height);
    }
}
