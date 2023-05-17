package code;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *TextPanel class extends JPanel class
 */
public class textPanel extends JPanel {
    private String str;
    public JFileChooser chooser = new JFileChooser();
    public JTextArea textArea;// makes it an attribute of the class so can use in multiple methods

    /**
     * Constructs a new code.textPanel object
     */
    public textPanel() {
        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        JScrollPane scroller = new JScrollPane(textArea);// allows scrolling function in the code.text panel
        scroller.setPreferredSize(new Dimension(300, 300));
        add(scroller);
    }

//    /**
//     * @return an array of strings of each line of code.text in code.text area
//     */
//    public String[] getInstructions() {
//        return str.split("\n");//splits by each line as each command is on a new line, returns separate commands
//    }

    /**
     * @return text displayed in textArea
     */
    public String getText() {
        return textArea.getText();
    }

    /**
     * Loads the imported file into textArea
     * @param evt the file that is loaded in
     */
    public void loadFromFile(JFileChooser evt) throws FileNotFoundException {
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);
            textArea.read(br, null);
            br.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
//
//    /**
//     * Updates text area with string array
//     * @param instructions array of strings of each line of text to be shown in text area
//     */
//    public void updateTextArea(String[] instructions) {
//        str = "";
//        for (String s: instructions) { //adds all lines in instructions string array to str value
//            str+= s +"\n"; //goes through each line
//        }
//        textArea.setText(str);//sets text area equal to string
//    }
}
