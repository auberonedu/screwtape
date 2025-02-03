import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * A Screwtape interpreter that executes programs written in the Screwtape esoteric programming language.
 * 
 * Screwtape is a minimalistic language with the following commands:
 * 
 * - `>`: Move the tape pointer to the next memory node.
 * - `<`: Move the tape pointer to the previous memory node.
 * - `+`: Increment the value in the current memory node.
 * - `-`: Decrement the value in the current memory node.
 * - `.`: Output the character represented by the value in the current memory node.
 * - `[`: Do nothing
 * - `]`: If the value in the current memory node is not 0, jump back to the matching `[`.
 * 
 * This interpreter provides methods to manipulate the memory tape, execute programs, and handle loops efficiently.
 */
public class ScrewtapeInterpreter {

  /** The head of the doubly linked list representing the tape. */
  private Node tapeHead;

  /** The pointer to the current node in the tape. */
  private Node tapePointer;

  /**
   * Constructs a new Screwtape interpreter with an initialized memory tape of a single node set to 0.
   */
  public ScrewtapeInterpreter() {
    tapeHead = new Node(0);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the current state of the memory tape as a list of integers.
   * 
   * @return A list of integers representing the values in the memory tape, starting from the head.
   */
  public List<Integer> getTapeData() {
    return tapeHead.toList();
  }

  /**
   * Replaces the current memory tape with a new set of values.
   * 
   * @param data A list of integers to initialize the memory tape. Each integer will correspond to a memory node.
   * @throws IllegalArgumentException If the list is null or empty.
   */
  public void setTape(List<Integer> data) {
    tapeHead = new Node(data);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the value in the memory node currently pointed to by the tape pointer.
   * 
   * @return The integer value of the current memory node.
   */
  public int getTapePointerValue() {
    return tapePointer.value;
  }

  /**
   * Moves the tape pointer to the head of the memory tape.
   */
  public void moveTapePointerToHead() {
    tapePointer = tapeHead;
  }

  /**
   * Moves the tape pointer to the tail of the memory tape.
   */
  public void moveTapePointerToTail() {
    while (tapePointer.next != null) {
      tapePointer = tapePointer.next;
    }
  }

  /**
   * Creates a map of matching bracket pairs for the given Screwtape program.
   * 
   * The Screwtape language uses brackets `[` and `]` to define loops. This method identifies 
   * matching bracket pairs and returns a map that associates the index of each closing bracket 
   * (`]`) with its corresponding opening bracket (`[`).
   * 
   * For example, in the program `[>+<-]`, the opening bracket at index 0 matches the closing 
   * bracket at index 5. The returned map would contain a single entry: 
   * `{5: 0}`.
   * 
   * A few more examples:
   * 
   * input: `[+++][---]<<[+]`
   * output:`{4: 0, 9: 5, 14: 12}`
   * 
   * input: `[]`
   * output: `{1: 0}`
   * 
   * input: `>[+>[+-]<]`
   * output: `{9: 1, 7: 4}`
   * 
   * 
   * @param program The Screwtape program as a string.
   * @return A map where each key-value pair represents a matching bracket pair.
   * @throws IllegalArgumentException If the program contains unmatched brackets.
   */
  public Map<Integer, Integer> bracketMap(String program) {
    // TODO: Implement this
    // Hint: use a stack
    Stack<Integer> stack = new Stack<>();
     Map<Integer, Integer> bracketPairs = new HashMap<>();
       if (program.isEmpty() || program == null) {
        throw new IllegalArgumentException("Expecting a program");
     }

      // Looping through each character in the program
      for (int i = 0; i < program.length(); i++) {
       char c = program.charAt(i);

       if (c == '[') {
          // Push the index of the '[' onto the stack
          stack.push(i);
       } 
       else if (c == ']') {
          // When we encounter a closing bracket, we should match it with the top of the stack
          if (stack.isEmpty()) {
              // There's a ']' without a matching '['
              throw new IllegalArgumentException("Unmatched ']' at index " + i);
          }
          int openingIndex = stack.pop();
          // Record the mapping from the closing bracket to its matching opening bracket
          bracketPairs.put(i, openingIndex);
      }
   }

          // If there's still something left in the stack, we have '[' brackets without matching ']'
         if (!stack.isEmpty()) {
         throw new IllegalArgumentException("Unmatched '[' at index " + stack.peek());
  }

       return bracketPairs;
     }

  /**
   * Executes a Screwtape program and returns the output as a string.
   * 
   * The Screwtape program is executed by interpreting its commands sequentially. The memory tape is dynamically 
   * extended as needed, and the tape pointer starts at the head of the tape. Loops defined by brackets 
   * `[` and `]` are executed as long as the current memory node's value is non-zero.
   * 
   * Output is generated using the `.` command, which converts the value in the current memory node 
   * to its corresponding ASCII character. The resulting output is returned as a string.
   * 
   * Example:
   * Program: >++++++++[<+++++++++>-]<.>>++++++++[<+++++++++>-]<+.
   * Output: "HI"
   * 
   * @param program The Screwtape program as a string.
   * @return The output generated by the program as a string.
   * @throws IllegalArgumentException If the program contains unmatched brackets.
   */
  public String execute(String program) {
    // TODO: Implement this
    // If you get stuck, you can look at hint.md for a hint
    Map<Integer, Integer> pairs = bracketMap(program);
    StringBuilder output = new StringBuilder();

    // Program counter
    int i = 0;

    //Interpret each character until we reach the end
    while (i < program.length()) {
        char c = program.charAt(i);

        switch (c) {
            case '>':
                // Move the tape pointer to the right, creating a new node if needed
                if (tapePointer.next == null) {
                    // Dynamically extend the tape to the right
                    Node newNode = new Node(0);
                    tapePointer.next = newNode;
                    newNode.prev = tapePointer;
                }
                tapePointer = tapePointer.next;
                break;

            case '<':
                // Move the tape pointer to the left, creating a new node if needed
                if (tapePointer.prev == null) {
                    // Dynamically extend the tape to the left
                    Node newNode = new Node(0);
                    newNode.next = tapePointer;
                    tapePointer.prev = newNode;
                }
                tapePointer = tapePointer.prev;
                break;

            case '+':
                // Increment the value in the current memory node
                tapePointer.value++;
                break;

            case '-':
                // Decrement the value in the current memory node
                tapePointer.value--;
                break;

            case '.':
                // Output the character represented by the current node's value
                output.append((char) (tapePointer.value));
                break;

            case '[':
                // Doing nothing on '[', actual jump logic is handled in ']'
                break;

            case ']':
                // If the current cell’s value != 0, jump back to the matching '['
                if (tapePointer.value != 0) {
                    i = pairs.get(i);
                }
                break;

            default:
                // Ignore any characters not part of Screwtape
                break;
        }

        // Move to the next instruction
        i++;
    }

    // Return whatever we collected from '.' commands
    return output.toString();

  }
}
