package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Frame class extends JFrame and implements ActionListener
 * Creates code.frame of the GUI: two panels and a menu bar
 */
public class frame extends JFrame implements ActionListener {
    private textPanel textPanel = new textPanel();
    private drawPanel drawPanel = new drawPanel();
    private int xcoordinate = 0;
    private int ycoordinate = 0;
    private commandParsing parser = new commandParsing();

    /**
     * Constructor
     * Creates a container with a grid layout and two panels to it
     */
    public frame() {
        //sets container to be a grid layout so panels are side by side
        Container con = getContentPane();
        GridLayout layout = new GridLayout(1, 2);
        con.setLayout(layout);

        //Creating the MenuBar and adding components
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);//when extending JFrame don't need to do code.frame. eg code.frame.setJMenuBar because the actions of the JFrame class are already able to be used

        //menu bar
        JMenu file = new JMenu("FILE");
        menuBar.add(file);
        JMenu help = new JMenu("HELP");
        menuBar.add(help);

        //help menu bar --> about dialogue box
        JMenuItem dialogue = new JMenuItem("ABOUT");
        help.add(dialogue);
        dialogue.addActionListener(this);

        //file section of menu bar
        JMenuItem load = new JMenuItem("LOAD");
        file.add(load);
        load.addActionListener(this);
        JMenuItem saveImage = new JMenuItem("SAVE IMAGE");
        file.add(saveImage);
        saveImage.addActionListener(this);
        JMenuItem saveText = new JMenuItem("SAVE COMMANDS");
        file.add(saveText);
        saveText.addActionListener(this);
        JMenuItem clear = new JMenuItem("CLEAR");
        file.add(clear);
        clear.addActionListener(this);
        JMenuItem exit = new JMenuItem("EXIT");
        file.add(exit);
        exit.addActionListener(this);//adds the action of exiting to the exit button itself

        //adds code.text panel to code.frame
        textPanel.setVisible(true);
        textPanel.setSize(300, 300);
        textPanel.setBackground(Color.blue);
        this.add(textPanel);//adds the code.text panel to the code.frame

        //adds draw panel to code.frame
        drawPanel.setVisible(true);
        drawPanel.setSize(300, 300);
        drawPanel.setBackground(Color.ORANGE);
        this.add(drawPanel);//adds the draw panel to the code.frame

        // run button on code.text panel
        JButton b = new JButton("RUN");
        textPanel.add(b);
        b.addActionListener(this);
    }

    /**
     * Method called when an action event occurs, and performs actions dependent on what event has happened.
     *
     * @param e action event that's taken place
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();
        // Exits application if exit is pressed
        if (arg.equals("EXIT")) {
            System.exit(0);
        }

        if (arg.equals("RUN")) {
            parser.parse(textPanel.getText(), xcoordinate, ycoordinate, drawPanel);
        }

        if (arg.equals("ABOUT")) {
            JOptionPane.showMessageDialog(null,//JOptionPane brings up dialogue box with the code.text in
                    "LOAD - choose load to open and load your code.text file; \n" +
                            "SAVE IMAGE- choose to save your image as a png file;\n" +
                            "SAVE COMMANDS- choose to save your command as a code.text file;\n" +
                            "CLEAR- choose to clear the screen;\n" +
                            "EXIT - choose exit to leave the application");
        }

        if (arg.equals("CLEAR")) {
            drawPanel.clearsPanel();
        }

        if (arg.equals("LOAD")) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            try {
                textPanel.loadFromFile(chooser);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if (arg.equals("SAVE COMMANDS")) {
            String savedCommandFileName = "";
            try {
                savedCommandFileName = JOptionPane.showInputDialog("Choose the name of your saved commands file...");
                FileWriter writer = new FileWriter(savedCommandFileName);
                writer.write(textPanel.getText());
                writer.close();
                JOptionPane.showMessageDialog(null, "Text file Saved");
            } catch (NullPointerException | IOException ne) {
                JOptionPane.showMessageDialog(null, "File save cancelled");
            }
        }

        if (arg.equals("SAVE IMAGE")) {
            String savedImageFileName = "";
            try {
                savedImageFileName = JOptionPane.showInputDialog("Choose the name of your saved image file...");//shows code.text box to user to enter the name of the file they want
                BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);//creates an image as an object of BufferedImage to have the parameters of the component's height and width and for image to be coloured
                (drawPanel).paint(image.getGraphics());//calls component's paint method using graphics object of the image
                ImageIO.write(image, "PNG", new File(savedImageFileName + ".png"));
                JOptionPane.showMessageDialog(null, "PNG Saved");
            } catch (NullPointerException ne) {
                JOptionPane.showMessageDialog(null, "File save cancelled");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "A problem occurred when saving the file");
            }
        }
    }

    public drawPanel getdrawPanel() {
        return drawPanel;
    }
}