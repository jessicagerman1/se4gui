import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Locale;

public class commandParsing {
    private List<String> variables = new ArrayList<>();
    private List<Integer> variablesValues = new ArrayList<>();

    public int IsNumber(String arg) {
        int length = -1;
        try {
            length = Integer.parseInt(arg.trim());
        } catch (NumberFormatException e) {
            return length;
        }
        return (length);
    }

    public void parse(String userInput, int x, int y, drawPanel draw) {
        int lineNumber = 0;
        String equals = "=";
        int variable1 = 0;
        userInput = userInput.trim().toLowerCase(Locale.ROOT);
        String[] splitInput = userInput.split("\n");

        for (String s : splitInput) {
            String[] args = s.split("\\s");
            if (lineNumber > getIfStartLine(userInput) && getIfEndLine(userInput) >= lineNumber){
                lineNumber++;
                continue;
            }
            if (args[0].equalsIgnoreCase( "square")) {
                if (args.length != 3) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of arguments", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                int length = IsNumber(args[1]);
                if (length <= 0) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                boolean filledIn = Boolean.parseBoolean(args[2]);
                draw.drawSquare(x, y, length, filledIn); //calls the draw square method within the draw panel class with filledIn as false
                lineNumber++;
            }
            if (args[0].equalsIgnoreCase("rectangle")) {
                if (args.length != 4) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of arguments", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                int length = IsNumber(args[1]);
                if (length <= 0) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                int length2 = IsNumber(args[2]);
                if (length2 <= 0) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                boolean filledIn = Boolean.parseBoolean(args[2]);
                draw.drawRectangle(x, y, length, length2, filledIn); //calls the draw square method within the draw panel class with filledIn as false
                lineNumber++;
            }
            if (args[0].equalsIgnoreCase( "circle")) {
                int radius = IsNumber(args[1]);
                if (radius <= 0) {
                    JOptionPane.showMessageDialog(null, "Argument has to be an integer above zero", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                boolean filledIn = Boolean.parseBoolean(args[2]);
                draw.drawCircle(x, y, radius, filledIn);
                lineNumber++;
            }

            if (args[0].equalsIgnoreCase("clear")) {
                draw.clearsPanel();
                lineNumber++;
            }

            if (args[0].equalsIgnoreCase( "moveto")) {
                if (args.length != 3) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of parameters", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                int newX = IsNumber(args[1]);
                int newY = IsNumber(args[2]);//IsNumber method checks the value is a valid integer, -1 is invalid as is less than 0 or not a number as parseInt didn't work
                if (newX == -1 || newY == -1)//checking values are valid integers, not -1
                {
                    lineNumber++;
                    continue;
                }

                x = newX;
                y = newY;
                lineNumber++;
                continue;
            }

            if (args[0].equalsIgnoreCase("drawto")) {
                if (args.length != 3) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of parameters", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                int newX = IsNumber(args[1]);
                int newY = IsNumber(args[2]);
                draw.drawLine(x, y, newX, newY);
                lineNumber++;
                x = newX;
                y = newY;
                //setting the x and y coordinates to the newly set ones by user
            }


            if (args[0].equalsIgnoreCase( "colour")) {
                if (args.length != 4) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of parameters", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                int redValue = IsNumber(args[1]);//parse int is used to ensure parameters are integers
                int greenValue = IsNumber(args[2]);
                int blueValue = IsNumber(args[3]);//setting RGB to their positions in the inputted instruction string
                if (redValue == -1 || greenValue == -1 || blueValue == -1 || redValue > 255 || greenValue > 255 || blueValue > 255)//checking colour values are between 0 and 255
                {
                    JOptionPane.showMessageDialog(null, "RGB values must be between 0 and 255", "Error", JOptionPane.ERROR_MESSAGE);
                    lineNumber++;
                    continue;
                }
                Color newColour = new Color(redValue, greenValue, blueValue);// creates new colour created using RGB values passed in
                draw.changeColour(newColour);// calls change colour method, sets colour of pen using new colour variable
                lineNumber++;
                continue;
            }

            if (args[0].equalsIgnoreCase("if")) {
                if (args.length != 5) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of parameters", "Error", JOptionPane.ERROR_MESSAGE);
                }
                variables.add("x"); // TODO remove these when I've implemented variables
                variablesValues.add(1); // TODO remove these when I've implemented variables
                String commands = getIfStartAndEnd(userInput);
                if (variables.contains(args[1].toLowerCase(Locale.ROOT))) {
                    if (args[2].equals("==")) {
                        if (IsNumber(args[3]) != -1) {
                            if (args[4].equalsIgnoreCase("then")) {
                                int counter = 0;
                                int value = 0;
                                for (Object var : variables) {
                                    if (args[1].equalsIgnoreCase(var.toString())) {
                                        value = (int) variablesValues.get(counter);
                                    }
                                    counter++;
                                }
                                if (IsNumber(args[3]) == value) {
                                    parse(commands, x, y, draw);
                                }
                            }
                        }
                    }
                }
                lineNumber++;
            }

        }
    }

    public String getIfStartAndEnd(String userInput) {
        String text = "";
        String[] lines = userInput.split("\r\n|\r|\n");
        int numOfLines = userInput.split("\r\n|\r|\n").length;
        int ifStart = 0;
        int ifEnd = 0;
        for (int x = 0; x < numOfLines; x++) {
            String command = lines[x];
            if (command.toLowerCase(Locale.ROOT).startsWith("if")) {
                ifStart = x;
            }
            if (command.toLowerCase(Locale.ROOT).startsWith("endif")) {
                ifEnd = x;
            }
        }
        for (int x = ifStart; x <= ifEnd - 1; x++) {
            text += lines[x] + "\n";
        }
        return text;
    }

    public int getIfEndLine(String userInput) {
        String[] lines = userInput.split("\r\n|\r|\n");
        int numOfLines = userInput.split("\r\n|\r|\n").length;
        for (int x = 0; x < numOfLines; x++) {
            String command = lines[x];
            if (command.toLowerCase(Locale.ROOT).startsWith("endif")) {
                return x;
            }
        }
        return -1;
    }
    public int getIfStartLine(String userInput) {
        String[] lines = userInput.split("\r\n|\r|\n");
        int numOfLines = userInput.split("\r\n|\r|\n").length;
        for (int x = 0; x < numOfLines; x++) {
            String command = lines[x];
            if (command.toLowerCase(Locale.ROOT).startsWith("if")) {
                return x;
            }
        }
        return -1;
    }
}


