import javax.swing.*;
public class Driver {
    public static void main(String[] args){
        frame frame = new frame();
        frame.setTitle("SE4 GUI");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // have to set frames' visibility to true in order to see them
    }
}
