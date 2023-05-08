import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *DrawPanel extends JPanel
 */
public class drawPanel extends JPanel {
    private static ArrayList <Object> shapes = new ArrayList<>();
    private Color colour;

    /**
     *Constructs a new drawPanel object
     */
    public drawPanel(){
        colour= Color.BLACK;
    }

    /**
     * Checks instance of each shape and draws the respective shapes from their draw methods
     * @param g graphics object g is inherited. Sets the graphics attribute of the inherited paint component method to the graphics object parameter, g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object s:shapes) { //for each (loop).. for each shape added to Object goes through the loop
            if(s instanceof Circle){
                ((Circle) s).draw(g);
            }
            if(s instanceof Line) {
                ((Line) s).draw(g);
            }
            if(s instanceof Rectangle) {
                ((Rectangle) s).draw(g);
            }
            if(s instanceof Square) {
                ((Square) s).draw(g);
            }
        }
    }

    /**
     * adds the circle object to list of shapes
     * repaints the drawpanel with circle shape
     * @param x current x position
     * @param y current y position
     * @param radius integer entered by user's input
     * @param filledIn Boolean value whether it's filled or not
     */
    public void drawCircle(int x, int y, int radius, boolean filledIn){
        shapes.add(new Circle(x,y,radius,filledIn,colour));
        repaint();//repaint re-paints
    }

    /**
     * clears shapes from list and updates the drawpanel with removal of shapes to clear it
     */
    public void clearsPanel(){
        shapes.clear();
        repaint();
    }

    /**
     * draws a line from current x, y coordinates to newly set ones
     * @param x current x coordinate
     * @param y current y coordinate
     * @param newX newly set x coordinate
     * @param newY newly set y coordinate
     */
    public void drawLine(int x, int y, int newX, int newY){
        shapes.add(new Line(x, y, newX, newY, colour));
        repaint();
    }

    /**
     *
     * @param colour changes the colour to be equal to the new colour passed in
     */
    public void changeColour(Color colour){
        this.colour = colour;
     }

//     public void writesText(int x, int y, String text, Boolean fontIsBold){
//         shapes.add(new Text(x, y, text, fontIsBold));
//        repaint();
//     }

    /**
     * adds rectangle to the shapes list
     * updates draw panel with new rectangle shape with parameters set
     * @param x current x coordinate
     * @param y current y coordinate
     * @param width int set by user
     * @param height int specified by user's input
     * @param filledIn bool set by user
     */
     public void drawRectangle(int x, int y, int width, int height, boolean filledIn){
        shapes.add(new Rectangle(x,y,width,height,filledIn,colour));
        repaint();
     }

    /**
     * adds square to shapes list
     * @param x current x coordinate
     * @param y current y coordinate
     * @param length int set by user
     * @param filledIn bool set by user
     */
    public void drawSquare(int x, int y, int length, boolean filledIn) {
        shapes.add(new Square(x, y, length, filledIn, colour));
        repaint();
    }
}
