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
            if(Objects.equals(args[0], "square")){
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
        }

    }
}
