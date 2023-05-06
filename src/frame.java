import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class frame extends JFrame implements ActionListener {
    private textPanel textPanel = new textPanel();
    private drawPanel drawPanel = new drawPanel();
    private int xcoordinate =0;
    private int ycoordinate =0;
    private commandParsing parser = new commandParsing();

    public frame() {
        Container con = getContentPane();
        GridLayout layout = new GridLayout(1, 2);
        con.setLayout(layout);//sets container to be a grid layout so panels are side by side2

        //Creating the MenuBar and adding components
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);//when extending JFrame don't need to do frame. eg frame.setJMenuBar because the actions of the JFrame class are already able to be used

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


        textPanel.setVisible(true);
        textPanel.setSize(300, 300);
        textPanel.setBackground(Color.blue);
        this.add(textPanel);//adds the text panel to the frame

        drawPanel.setVisible(true);
        drawPanel.setSize(300, 300);
        drawPanel.setBackground(Color.ORANGE);
        this.add(drawPanel);//adds the draw panel to the frame

//        //run button
        JButton b=new JButton("RUN");
        textPanel.add(b);
        b.addActionListener(this);
    }

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

            if (arg.equals("CLEAR")) {
            drawPanel.clearsPanel();
        }

        if (arg.equals("LOAD")) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            textPanel.loadFromFile(chooser);
        }
        if (arg.equals("SAVE COMMANDS")) {
            String savedCommandFileName = "";
            try {
                savedCommandFileName = JOptionPane.showInputDialog("Choose the name of your saved commands file...");
                FileWriter writer = new FileWriter(savedCommandFileName);
                writer.write(textPanel.getText());
                writer.close();
                JOptionPane.showMessageDialog(null, "Text file Saved");
            }
            catch (NullPointerException | IOException ne) {
                JOptionPane.showMessageDialog(null, "File save cancelled");
            }
        }

        if (arg.equals("SAVE IMAGE")) {
            String savedImageFileName="";
            try{
                savedImageFileName=JOptionPane.showInputDialog("Choose the name of your saved image file...");//shows text box to user to enter the name of the file they want
                BufferedImage image = new BufferedImage(drawPanel.getWidth(),drawPanel.getHeight(),BufferedImage.TYPE_INT_RGB);//creates an image as an object of BufferedImage to have the parameters of the component's height and width and for image to be coloured
                (drawPanel).paint(image.getGraphics());//calls component's paint method using graphics object of the image
                ImageIO.write(image, "PNG", new File(savedImageFileName+".png"));
                JOptionPane.showMessageDialog(null, "PNG Saved");
            }
            catch (NullPointerException ne) {
                JOptionPane.showMessageDialog(null, "File save cancelled");
            }
            catch (IOException e1){
                JOptionPane.showMessageDialog(null, "A problem occurred when saving the file");
            }
        }
}
}