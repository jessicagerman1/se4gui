import com.sun.jdi.ObjectReference;

import javax.swing.*;
import java.util.*;
import java.util.Locale;

public class commandParsing {
    public int IsNumber(String arg){
        int length =0;
        try{
            length = Integer.parseInt(arg);
        }
        catch (NumberFormatException e) {
            return length;
        }
        return(length);
    }
    public void parse(String userInput, int x, int y, drawPanel draw)
    {
        String equals = "=";
        int variable1 = 0;
        userInput = userInput.trim().toLowerCase(Locale.ROOT);
        String[] splitInput = userInput.split("\n");

        for (String s : splitInput) {
            String[] args = s.split("\\s");
            if(Objects.equals(args[0], "square")) {
                if (args.length != 3) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of arguments", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;// does error message, then goes to next instruction
                }
                int length = IsNumber(args[1]);
                if (length  <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                boolean filledIn =Boolean.parseBoolean(args[2]);
                draw.drawSquare(x, y, length, filledIn); //calls the draw square method within the draw panel class with filledIn as false
            }
            if(Objects.equals(args[0], "rectangle")) {
                if (args.length!=4){
                    JOptionPane.showMessageDialog(null, "Incorrect number of arguments", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                int length = IsNumber(args[1]);
                if (length  <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                int length2 = IsNumber(args[2]);
                if (length2  <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                boolean filledIn =Boolean.parseBoolean(args[2]);
                draw.drawRectangle(x, y, length, length2, filledIn); //calls the draw square method within the draw panel class with filledIn as false
            }
            if (Objects.equals(args[0], "circle")) {
                int radius = IsNumber(args[1]);
                if (radius  <= 0 ) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                boolean filledIn =Boolean.parseBoolean(args[2]);
                draw.drawCircle(x, y, radius, filledIn);
            }

            if (Objects.equals(args[0], "clear")) {
                drawPanel.clearsPanel();
            }

            if (Objects.equals(args[0],"moveto")) {
                if (args.length != 3) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of parameters", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                int newX = IsNumber(args[1]);
                int newY = IsNumber(args[2]);//IsNumber method checks the value is a valid integer, -1 is invalid as is less than 0 or not a number as parseInt didn't work
                if (newX == -1 || newY == -1)//checking values are valid integers, not -1
                {
                    continue;
                }

                x = newX;
                y = newY;
                continue;
            }

        }
    }
}
