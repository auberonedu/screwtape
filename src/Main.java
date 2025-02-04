public class Main {
    public static void main(String[] args) {
        ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();

        // screwtape expected output
        String program = "+++++>++++++++[<+++++>-]<.>++++[<++++>-]<.>+++++<.>++++++++[<++++>-]<.>+++<.>+++++++<.";
        
        // Execute the program and capture the output
        String output = interpreter.execute(program);

        // Print the output (this should match the required output)
        System.out.println(output);
    }
}
