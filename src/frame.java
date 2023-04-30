import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class frame extends JFrame implements ActionListener {
    private textPanel textPanel = new textPanel();
    private drawPanel drawPanel = new drawPanel();
    private int xcoordinate = 0;
    private int ycoordinate = 0;
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
        JMenuItem save = new JMenuItem("SAVE");
        file.add(save);
        save.addActionListener(this);

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

        //run button
        JButton b = new JButton("RUN");
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
    }
}