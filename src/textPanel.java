import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class textPanel extends JPanel {
    private String str;
    private JTextArea textArea;// makes it an attribute of the class so can use in multiple methods
    public textPanel() {
        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        JScrollPane scroller = new JScrollPane(textArea);// allows scrolling function in the text panel
        scroller.setPreferredSize(new Dimension(300, 300));
        add(scroller);
    }

    public String[] getInstructions() {
        return str.split("\n");//splits by each line as each command is on a new line, returns separate commands
    }

    public void updateTextArea(String[] instructions) {
        str = "";
        for (String s: instructions) { //adds all lines in instructions string array to str value
            str+= s +"\n"; //goes through each line
        }
        textArea.setText(str);//sets text area equal to string
    }
}
