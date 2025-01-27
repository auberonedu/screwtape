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
    // a stack takes - Push is putting things ontop. -Pop is taking the top item off
    //  you can only traverse this last in first out. So you can only take the 
    // top item off and only add to the top.
    // stack is to only temporarily hold the bracket values INDEX to add to the map
    Stack<Integer> stack = new Stack<>();
    Map<Integer, Integer> matchingPairs = new HashMap<>();

    if(program.isEmpty() || program == null){
      throw new IllegalArgumentException("Expecting a program");
    }

    for(int i = 0; i < program.length(); i++){
      char cmd = program.charAt(i);
      // '' is to make a STRIng a char literal for comparison... weird
      if(cmd == '['){
        stack.push(i);
      }
      else if(cmd == ']'){
        if(stack.isEmpty()){
          throw new IllegalArgumentException("Unmatched closing bracket");
        }
        //this line is to pop the top integer of the stack into the map to then
        // create the key value pair needed to know the opening and closing brack
        int openingBrack = stack.pop();
        // i in this put represents the place of the most recent closing brack
        // which makes the key and value deal in the map data structure
        matchingPairs.put(i, openingBrack);
      }

      //this gets checked if you go through the whole string of commands and their isn't a single closing brack
      if(!stack.isEmpty()){
        throw new IllegalArgumentException("Unamtched Opening bracket");
      }
      // only want to use a map when you find the closing bracket

    }
    return matchingPairs;
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
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    Map<Integer, Integer> bracketPairs = bracketMap(program);
    StringBuilder builder = new StringBuilder();
    
    for(int i = 0; i < program.length(); i++){
      int cmd = program.charAt(i);

      if(cmd == '+'){
        //add
        tapeHead.value++;
      }
      if(cmd == '-'){
        //sub
        tapeHead.value--;
      }
      if(cmd == '>'){
        //move forward
        
        if(tapeHead.next == null){
          tapeHead.next = new Node(0);
          tapePointer = tapeHead.next;
        } else{
          tapePointer = tapeHead.next;
        }
      }
      if(cmd == '<'){
        //move back
        
        if(tapeHead.prev == null){
          tapeHead.next = new Node(0);
          tapePointer = tapeHead.next;
        }
        else{
          tapePointer = tapeHead.prev;
        }
      }
      if(cmd == '['){
        //if the tapePointer.value != 0{
        //it is going to do the command next in line and follow the for loop and the 
        // stuff inside the brackets
      //}
        if(tapePointer.value == 0){
          //this is going to put it forward to the corresponding closing brack
          i = bracketPairs.get(i);
        }
       
      }
      if (cmd == ']') {
        if (tapePointer.value != 0) {
          //moving it back to the corresponding opening bracket
            i = bracketPairs.get(i); 
        }
        tapePointer = tapeHead.next;
    }
      if(cmd == '.'){
        builder.append(cmd);
      }

      if(cmd == '['){

      }
    }
    return builder.toString();
  }
}
