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

}
