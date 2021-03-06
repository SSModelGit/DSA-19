public class HelloWorld {
    public static void main(String[] args) {
        // Put a breakpoint on line 4. When the debugger stops there, choose "Step Into"
        debugPractice();

        // After you step out of debugPractice, you should end up here
        System.out.print("Hello, World!");
        System.out.println("How are you?");
    }

    /* Use this function to play around with IntelliJ's debugger */
    public static void debugPractice() {
        int x, y, z;

        x = 1; // After you stepped into, you should end up here. What are the values of x, y, and z?
        // "Step Over" and watch the values of x, y, and z in the Variables window. Go until line 20
        y = 2;
        z = 3;

        x++;
        y = z - x; // When you get to this line, "Step Out" to go back to the main method.
        System.out.print(y); // Just to get rid of this annoying 'y value isn\'t used' error.
    }
}
