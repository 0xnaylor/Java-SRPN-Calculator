import java.util.EmptyStackException;
import java.util.Stack;

/***
 * A helper class that is responsible for performing arithmetic calculations for the SRPN calculator.
 */
public class Calculate {

    /***
     * This method takes the two top level elements in the stack
     * and adds them together. If the result of the calculation exceeds the
     * max limit the method will return the maximum permitted value.
     *
     * @param stack the stack to pop the elements from
     * @return the int to push back onto the stack
     */
    public int add(Stack stack) {

        int result;
        int num1 = 0;

        try {
            num1 = (int) stack.pop();
            int num2 = (int) stack.pop();

            // temporarily cast ints to longs so that we can check the
            // result of the calculation without wrap around
            if ((long)num2 + (long)num1 > Integer.MAX_VALUE) {
                result = Integer.MAX_VALUE;
            } else {
                result = num2 + num1;
            }

        } catch (EmptyStackException e) {
            // this exception occurs when there are not enough elements
            // on the stack to perform the calculation
            System.out.println("Stack underflow.");
            // revert the state of the stack
            return num1;
        }
        return result;
    }

    /***
     * This method takes the two top level elements in the stack
     * and subtracts them. If the result of the calculation exceeds the
     * min limit the method will return the minimum permitted value.
     *
     * @param stack the stack to pop the elements from
     * @return the int to push back onto the stack
     */
    public int subtract(Stack stack) {

        int result;
        int num1 = 0;

        try {
            num1 = (int) stack.pop();
            int num2 = (int) stack.pop();

            // temporarily cast ints to longs so that we can check the
            // result of the calculation without wrap around
            if ((long)num2 - (long)num1 < Integer.MIN_VALUE)  {
                result = Integer.MIN_VALUE;
            } else {
                result = num2 - num1;
            }

        } catch (EmptyStackException e) {
            // this exception occurs when there are not enough elements
            // on the stack to perform the calculation
            System.out.println("Stack underflow.");
            // revert the state of the stack
            return num1;
        }
        return result;
    }

    public void checkLimits(int num1, int num2) {

    }

    /***
     * This method takes the two top level elements in the stack
     * and multiplies them together. If the result of the calculation exceeds the
     * max or min limit the method will return the maximum or
     * minimum permitted value respectively.
     *
     * @param stack the stack to pop the elements from
     * @return the int to push back onto the stack
     */
    public int multiply(Stack stack) {

        int result;
        int num1 = 0;

        try {
            num1 = (int) stack.pop();
            int num2 = (int) stack.pop();

            // temporarily cast ints to longs so that we can check the
            // result of the calculation without wrap around
            if ((long)num2 * (long)num1 < Integer.MIN_VALUE)  {
                result = Integer.MIN_VALUE;
            } else if ((long)num2 * (long)num1 > Integer.MAX_VALUE) {
                result = Integer.MAX_VALUE;
            } else {
                result = num2 * num1;
            }

        } catch (EmptyStackException e) {
            // this exception occurs when there are not enough elements
            // on the stack to perform the calculation
            System.out.println("Stack underflow.");
            // revert the state of the stack
            return num1;
        }
        return result;
    }

    /***
     * This method takes the two top level elements in the stack
     * and divides them. If either of the elements are equal to zero
     * the calculation will not be attempted and an error message will be displayed
     * in the console.
     *
     * @param stack the stack to pop the elements from
     * @return the int to push back onto the stack
     */
    public int divide(Stack stack) {

        int result;
        int num1 = 0;

        try {
            num1 = (int) stack.pop();
            if (num1 != 0 && (int) stack.peek() != 0) {
                int num2 = (int) stack.pop();
                result = num2 / num1;
            } else {
                System.out.println("Divide by 0.");
                // revert the state of the stack
                return num1;
            }
        } catch (EmptyStackException e) {
            // this exception occurs when there are not enough elements
            // on the stack to perform the calculation
            System.out.println("Stack underflow.");
            // revert the state of the stack
            return num1;
        }
        return result;
    }

    /***
     * This method takes the two top level elements in the stack
     * and performs a modulus operation on them.
     *
     * @param stack the stack to pop the elements from
     * @return the int to push back onto the stack
     */
    public int modulus(Stack stack) {
        int result;
        int num1 = 0;

        try {
            num1 = (int) stack.pop();
            int num2 = (int) stack.pop();
            result = num2 % num1;
            return result;
        } catch (EmptyStackException e) {
            // this exception occurs when there are not enough elements
            // on the stack to perform the calculation
            System.out.println("Stack underflow.");
            // revert the state of the stack
            return num1;
        }
    }

    /***
     * This method takes the two top level elements in the stack
     * and raises the first number to the power of the second.
     * If the result of the calculation exceeds the
     * max or min limit the method will return the maximum or
     * minimum permitted value respectively.
     *
     * @param stack the stack to pop the elements from
     * @return the int to push back onto the stack
     */
    public int power(Stack stack) {
        int result;
        int num1 = 0;

        try {
            num1 = (int) stack.pop();
            int num2 = (int) stack.pop();

            // temporarily cast ints to longs so that we can check the
            // result of the calculation without wrap around
            if (Math.pow(num2, num1) < Integer.MIN_VALUE)  {
                result = Integer.MIN_VALUE;
            } else if (Math.pow(num2,num1) > Integer.MAX_VALUE) {
                result = Integer.MAX_VALUE;
            } else {
                result = (int)Math.pow(num2,num1);
            }
            return result;
        } catch (EmptyStackException e) {
            // this exception occurs when there are not enough elements
            // on the stack to perform the calculation
            System.out.println("Stack underflow.");
            // revert the state of the stack
            return num1;
        }
    }

    /***
     * Display the top level element of the stack
     * @param stack the stack containing the top level element to check.
     */
    public void equals(Stack stack) {
        try {
            System.out.println(stack.peek());
        } catch (EmptyStackException e) {
            System.out.println("Stack empty.");
        }
    }

}
