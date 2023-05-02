import java.awt.*;

public class Circle {
    private int x, y, radius;
    private boolean filledIn;
    private Color colour;
    //write out these attributes and right-click Circle to generate constructor

    public Circle(int x, int y, int radius, boolean filledIn, Color colour) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.filledIn = filledIn;
        this.colour = colour;
    }

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