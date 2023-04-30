import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class drawPanel extends JPanel {
    private ArrayList <Object> shapes = new ArrayList<Object>();//In future could change<Object> to stn to more specific if make a class for shapes to inherit from
    private Color colour;
    public drawPanel(){
        colour= Color.BLACK;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//the graphics object g is inherited. Sets the graphics attribute of the inherited paint component method to the graphics object parameter, g
        for (Object s:shapes) { //for each (loop).. for each shape added to Object goes through the loop
            if(s instanceof Square){
                ((Square) s).draw(g);
            }
        }
    }

    public void drawSquare(int x, int y, int length, boolean filledIn) {
        shapes.add(new Square(x,y,length,filledIn, colour));
        repaint();
    }
}
