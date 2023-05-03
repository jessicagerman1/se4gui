import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class textPanel extends JPanel {
    private String str;
    public JFileChooser chooser = new JFileChooser();
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

    public String getText() {
        return textArea.getText();
    }


    public void loadFromFile(JFileChooser evt) {
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
    public void updateTextArea(String[] instructions) {
        str = "";
        for (String s: instructions) { //adds all lines in instructions string array to str value
            str+= s +"\n"; //goes through each line
        }
        textArea.setText(str);//sets text area equal to string
    }
}
