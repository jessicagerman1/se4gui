import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Locale;

/**
 * commandParsing class executes commands from the user's input
 */
public class commandParsing {
    private List<String> variables = new ArrayList<>();
    private List<Integer> variablesValues = new ArrayList<>();

    /**
     * @param arg the String input from the user
     * @return the integer value of the string input if it can be converted to an int, or -1 if not
     */
    public int IsNumber(String arg) {
        int length = -1;
        try {
            length = Integer.parseInt(arg.trim()); //parses trimmed string to an int
        } catch (NumberFormatException e) {
            return length; //returns -1 if can't be parsed to an integer
        }
        return (length);
    }

    /**
     *
     * Takes in user input, converts to lowercase and splits into an array of Strig objects
     *
     * Each line of input is looped over, split into individual words, checking for commands to draw shapes from
     *
     * @param userInput the input the user enters which is trimmed and put to lower case
     * @param x second argument (an integer) entered by user
     * @param y third argument (integer) entered by user
     * @param draw object of type drawPanel
     */
    public void parse(String userInput, int x, int y, drawPanel draw) {
        int lineNumber = 0;
        userInput = userInput.trim().toLowerCase(Locale.ROOT);
        String[] splitInput = userInput.split("\n");

        for (String s : splitInput) {
            String[] args = s.split("\\s");
            if (lineNumber > getIfStartLine(userInput) && getIfEndLine(userInput) >= lineNumber){
                lineNumber++;
                continue;
            }

             //Checks there are 3 arguments entered when square is first argument, returns error messages if requirements aren't met for each argument.//
             //Draws a square of the requirements set if checks are passed
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

            // If first argument is rectangle checks if there are 4 arguments, 2nd and 3rd of which are checked to be integers
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

            //Draws a circle if the 2nd argument is a positive integer and there is a filled in value
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

            //clears the drawPanel object
            if (args[0].equalsIgnoreCase("clear")) {
                draw.clearsPanel();
                lineNumber++;
            }

            // Sets x and y to the 2nd and 3rd arguments if there are 3 args entered
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

         //If there's 3 args, draws a line from current x,y position to ones user enters, which then updates x and y with these
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

            //Checks whether the variable exists already.
            // If variable exists then loops around the size of the variables list and updates the corresponding value
            //in values list to set it with the newly assigned value.
            //Exits the loop once variable has been updated.
            if (args[1].equals("=")) {
                    try {
                        Integer.parseInt(args[2]);// checks args[2] is an integer
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Value is not a valid integer", "Error", JOptionPane.ERROR_MESSAGE);//returns error to user that value isn't an integer
                        return;
                    }
                }
            if (variables.contains(args[0])) {
                        for (int i = 0; i < variables.size(); i++) { //loop around the size of the variables list
                            if (variables.get(i).equals(args[0])) {
                                variablesValues.set(i, Integer.parseInt(args[2]));
                                break; // exits the loop once variable has been updated
                            }
                        }
                    }
                    else { //if the variable doesn't exist, adds variable and value to the array lists
                        variablesValues.add(Integer.parseInt(args[2]));
                        variables.add(args[0]);
                    }

            //if there's 4 arguments entered and the 2nd, 3rd and 4th are ints then these parameters are used to set the pen to the new colour of these RGB values
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

            //If there are 5 arguments, the 2nd arg is ==, 4th is a positive integer and 5th is 'then',
            //and the value of the variable matches number in input then if statement is executed
            if (args[0].equalsIgnoreCase("if")) {
                if (args.length != 5) {
                    JOptionPane.showMessageDialog(null, "Incorrect number of parameters", "Error", JOptionPane.ERROR_MESSAGE);
                }
                String commands = getIfStartAndEnd(userInput);
                if (variables.contains(args[1].toLowerCase(Locale.ROOT))) {
                    if (args[2].equals("==")) {
                        if (IsNumber(args[3]) != -1) {
                            if (args[4].equalsIgnoreCase("then")) {
                                int counter = 0;
                                int value = 0;
                                for (Object var : variables) {
                                    if (args[1].equalsIgnoreCase(var.toString())) {
                                        value = variablesValues.get(counter);
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

    /**
     * @param userInput is split by line and beginning and end of the if statement is found
     * @return text between start and end of if statement user writes in
     */
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

    /**
     *
     * @param userInput is split by line
     * @return line number where the if statement ends
     */
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

    /**
     *
     * @param userInput input user enters- trimmed and put to lower case
     * @return line number where the if statement starts
     */
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


