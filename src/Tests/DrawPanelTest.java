package Tests;

import code.Rectangle;
import code.*;
import org.junit.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class DrawPanelTest {
     private drawPanel panel;

    /**
     * testing that the paint component correctly draws the shapes
     */
    @Test
     public void testPaintComponent() {
         drawPanel panel = new drawPanel();
         Object [] shapes = {new Circle(100, 150, 40, true, Color.blue), new Rectangle(100, 150, 50, 25, true, Color.BLACK),
         new Square(150, 150, 30, false, Color.green), new Line(60, 150, 200, 250, Color.green)};

         BufferedImage image = new BufferedImage(300, 400, BufferedImage.TYPE_INT_RGB);
         Graphics2D g2d = image.createGraphics();
         panel.paintComponent(g2d);
         g2d.dispose();

         // Test if shapes are drawn correctly
         assertTrue(image.getRGB(100, 150) != Color.BLUE.getRGB()); // circle
         assertTrue(image.getRGB(100, 150) != Color.GREEN.getRGB()); // rectangle
         assertTrue(image.getRGB(150, 150) != Color.GREEN.getRGB()); // square
         assertTrue(image.getRGB(200, 250) != Color.WHITE.getRGB()); // line
     }

    /**
     * testing that a circle has been drawn in panel
     */
    @Test
    public void testDrawCircle() {
        drawPanel panel = new drawPanel();
        panel.clearsPanel();
        panel.drawCircle(100, 100, 30, false);
        ArrayList<Object> shapes = getShapes(panel);
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Circle);
    }

    /**
     *  testing that a line has been drawn
     */
    @Test
    public void testDrawLine() {
        drawPanel panel = new drawPanel();
        panel.clearsPanel();
        panel.drawLine(50, 50, 100, 100);
        ArrayList<Object> shapes = getShapes(panel);
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Line);
    }

    /**
     *  testing that a rectangle has been drawn
     */
    @Test
    public void testDrawRectangle() {
        drawPanel panel = new drawPanel();
        panel.clearsPanel();
        panel.drawRectangle(50, 50, 50, 30, true);
        ArrayList<Object> shapes = getShapes(panel);
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Rectangle);
    }

    /**
     *  testing that a square has been drawn in panel
     */
    @Test
    public void testDrawSquare() {
        drawPanel panel = new drawPanel();
        panel.clearsPanel();
        panel.drawSquare(50, 50, 40, false);
        ArrayList<Object> shapes = getShapes(panel);
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Square);
    }

    /**
     *  testing that the clear panel clears panel
     */
    @Test
    public void testClearsPanel() {
        drawPanel panel = new drawPanel();
        panel.clearsPanel();
        panel.drawCircle(50, 50, 20, true);
        panel.drawLine(50, 50, 100, 100);
        panel.clearsPanel();
        ArrayList<Object> shapes = getShapes(panel);
        assertEquals(0, shapes.size());
    }

    /**
     *
     * @param panel
     * @return the list of shapes from the drawpanel
     */
    private ArrayList<Object> getShapes(drawPanel panel) {
        return (ArrayList<Object>) getPrivateField(panel, "shapes");
    }

    /**
     *
     * @param panel
     * @param fieldName
     * @return the field populated in the drawpanel
     */
    private Object getPrivateField(drawPanel panel, String fieldName) {
        try {
            java.lang.reflect.Field field = panel.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(panel);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
