import javax.swing.*;

/**
 *Driver class has the main method that sets the frame and makes it visible
 */
public class Driver {
    /**
     * Main method that initialises frame and makes it visible
     * @param args command line arguments
     */
    public static void main(String[] args){
        frame frame = new frame();
        frame.setTitle("SE4 GUI");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // have to set frames' visibility to true in order to see them
    }
}
