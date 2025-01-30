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

    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();

    if (program == null || program.isEmpty()) {
      return map;
    }

    for (int i = 0; i < program.length(); i++) {
      char c = program.charAt(i); // grab char at current index

      if (c == '[') { // if its [ push index coordinate to a stack
        stack.push(i);
      } else if (c == ']') { // if its ] it means a match is found for [
        if (stack.isEmpty()) {
          throw new IllegalArgumentException("Program contains unmatched closing bracket at index " + i);
        }
        int open = stack.pop(); // save index coordinate before popping it
        map.put(i, open); // record index of ] with the popped coordinate of [ open
      } 
    }
    
    // if stack isn't empty, there's a rouge bracket
    if (!stack.isEmpty()) {
      throw new IllegalArgumentException("Program contains unmatched opening bracket at index " + stack.peek());
    }

    return map;
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
    Map<Integer, Integer> map = bracketMap(program); // use the map of brackets to jump back/ loop
    String output = "";
    int instructionPointer = 0; // basically index

    while (instructionPointer < program.length()) {
      char c = program.charAt(instructionPointer); // current index/character/screwtape command

      if (c == '+') {
        tapePointer.value++;
      } else if (c == '-') {
        tapePointer.value--;
      } else if (c == '>') {
        if (tapePointer.next == null) {
          tapePointer.next = new Node(0); // link next with new node
          tapePointer.next.prev = tapePointer; // link prev
        }
        tapePointer = tapePointer.next; // advance node forward
      } else if (c == '<') {
        if (tapePointer.prev == null) {
          tapePointer.prev = new Node(0);
          tapeHead = tapePointer.prev; // set new head
          tapeHead.next = tapePointer;
        }
        tapePointer = tapePointer.prev;
      } else if (c == '.') {
        output += (char) tapePointer.value; // cast int to char (ASCII character) and concat to output
      } 
      
      // when ] is encountered jump to it's corresponding [ to enter a loop. If the value is zero, don't loop (zero times)
      else if (c == ']' && tapePointer.value != 0) { 
        instructionPointer = map.get(instructionPointer); // pointer/index is now key=*VALUE*
        // so now key=] the instructionPointer can jump back to value=[ to repeat the action or LOOP
      }

      instructionPointer++;
    }

    return output;
  }
}