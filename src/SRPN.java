import java.util.*;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

/***
 * The Saturated Reverse Polish Notation Calculator
 *
 * The University of Bath
 * Module 1: Principles of Programming
 * Graded Assignment 1
 */
public class SRPN {

    // define regex strings for the different tokens the application will accept as input
    private final String digits = "(-?\\d+)";
    private final String assignments = "(\\+=|-=|\\*=|/=|\\^=|%=|=)";
    private final String operators = "([+/\\-*%^])";
    private final String commands = "([dr])";
    private final String space = "([ ])";
    private final String or = "|";
    private final String catchAll = "[^ ]"; // any character except space

    // define the maximum stack size
    static public final int MAX_STACK_SIZE = 23;

    // instantiate a new stack object
    Stack<Integer> stack = new Stack<>();

    // Create a new Random object with a seed.
    // This will generate a list of pseudorandom numbers that is the same each time.
    Random rand = new Random(15);

    // create Calculate object that is responsible for performing all calculations
    Calculate calculate = new Calculate();

    /***
     * A method to detect and match tokens based on regular expressions.
     * When a token is detected it will be passed as an argument to
     * processSingleToken method.
     *
     * @param s A full line entered by the user in the console
     */
    public void processCommand(String s) {

        // Build the regex string required to detect tokens.
        StringBuilder str = new StringBuilder();
        str.append(digits);
        str.append(or);
        str.append(assignments);
        str.append(or);
        str.append(operators);
        str.append(or);
        str.append(commands);
        str.append(or);
        str.append(catchAll);

        // To increase efficiency, compile a regex pattern object based on the string above/
        Pattern tokenRegex = Pattern.compile(str.toString());
        Pattern whiteSpace = Pattern.compile(space);

        // remove comments from user input
        String cleanToken = removeComments(s);

        // if there is no input to process then return to the calling method.
        // this could occur if the user only enters a comment
        if (s.isEmpty()) {
            return;
        }

        //try-with-resources
        try (Scanner scanner = new Scanner(cleanToken)) {

            while (scanner.hasNext()) {

                // set the scanners delimiter
                scanner.useDelimiter(whiteSpace);

                // check the line for a valid token as out lined in the tokenRegex regular expression
                String token = scanner.findInLine(tokenRegex);

                // only process token is detected
                if (token != null) {
                    processSingleToken(token);
                }
            }
        }
    }

    /***
     * A helper method which will remove any comments found in the input string.
     *
     * @param string The string to remove comments from
     * @return A string the same as the input string without comments
     */
    public String removeComments(String string) {
        return string.replaceAll("#(.)+#", "");
    }

    /***
     * This method will take a token as its argument. This token will be used to determine the
     * action to be performed on the stack. For example a "+" will result in the add() being called.
     *
     * @param s A command to to perform a specified action on the stack.
     */
    public void processSingleToken(String s) {

        switch (s) {

            case "+":
                stack.push(calculate.add(stack));
                break;
            case "-":
                stack.push(calculate.subtract(stack));
                break;
            case "*":
                stack.push(calculate.multiply(stack));
                break;
            case "/":
                stack.push(calculate.divide(stack));
                break;
            case "%":
                stack.push(calculate.modulus(stack));
                break;
            case "^":
                stack.push(calculate.power(stack));
                break;
            case "^=":
                calculate.equals(stack);
                stack.push(calculate.power(stack));
                break;
            case "+=":
                calculate.equals(stack);
                stack.push(calculate.add(stack));
                break;
            case "-=":
                calculate.equals(stack);
                stack.push(calculate.subtract(stack));
                break;
            case "*=":
                calculate.equals(stack);
                stack.push(calculate.multiply(stack));
                break;
            case "/=":
                calculate.equals(stack);
                stack.push(calculate.divide(stack));
                break;
            case "%=":
                calculate.equals(stack);
                stack.push(calculate.modulus(stack));
                break;
            case "=":
                calculate.equals(stack);
                break;
            case "d":
                displayStack(stack);
                break;
            case "r":
                // After experimenting with different seeds, I could not find a way to exactly
                // replicate the sequence of pseudorandom numbers that demo program produces.
                push(rand.nextInt(Integer.MAX_VALUE));
                break;
            default:
                // if none of the above have produced a match we assume the argument
                // is an int to be added to the stack.
                try {
                    int i = Integer.parseInt(s);
                    push(i);
                } catch (NumberFormatException e) {
                    System.out.println("Unrecognised operator or operand " + "\""+s+"\".");
                }
        }
    }

    /***
     * A helper method to display the contents of the stack.
     *
     * @param stack the stack to be displayed
     */
    public void displayStack(Stack<Integer> stack) {

        // if the stack is empty display -2147483648
        if (stack.isEmpty()) {
            System.out.println(Integer.MIN_VALUE);
        }

        // enhanced for loop to print out the contents of the stack
        for (Integer s : stack) {
            System.out.println(s);
        }
    }

    /***
     * A helper method to push an int onto the stack. Performs a check to ensure the
     * stack has not exceed the maximum allow size.
     *
     * @param i the int to be added to the stack.
     */
    public void push(int i) {
        if (stack.size() == MAX_STACK_SIZE) {
            System.out.println("Stack Overflow.");
        } else {
            stack.push(i);
        }
    }

}
