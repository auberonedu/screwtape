import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ScrewtapeInterpreterTest {

  @Test
  void testNestedBracketMap() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = ">[+>[+-]<]";

    Map<Integer, Integer> expectedMap = new HashMap<>();
    expectedMap.put(9, 1);
    expectedMap.put(7, 4);

    Map<Integer, Integer> actualMap = interpreter.bracketMap(program);

    assertEquals(expectedMap, actualMap);
  }

  // TODO: Implement more tests for bracketMap
  // At a bare minimum, implement the other examples from the Javadoc and at least one more you come up with
  

  /**
   * TEST #1: Tests that an empty string throws an exception.
   */
  @Test
  void testBracketMapEmptyProgram() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "";

    // Assert
    // Expecting an IllegalArgumentException due to empty program
    assertThrows(IllegalArgumentException.class,
        () -> interpreter.bracketMap(program),
        "Expected bracketMap to throw IllegalArgumentException for empty program.");
  }

  /**
   * TEST #2: Tests a simple empty bracket pair "[]".
   * Expected mapping: {1=0}
   */
  @Test
  void testBracketMapSimpleEmptyBrackets() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "[]";

    Map<Integer, Integer> expectedMap = new HashMap<>();
    expectedMap.put(1, 0);

    // Act
    Map<Integer, Integer> actualMap = interpreter.bracketMap(program);

    // Assert
    assertEquals(expectedMap, actualMap,
        "Expected {1=0} for a simple bracket pair '[]'.");
  }

  /**
   * TEST #3: Tests multiple bracket pairs: "[+++][---]<<[+]".
   * Expected mapping: {4=0, 9=5, 14=12}
   */
  @Test
  void testBracketMapMultipleSections() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "[+++][---]<<[+]";

    Map<Integer, Integer> expectedMap = new HashMap<>();
    expectedMap.put(4, 0);
    expectedMap.put(9, 5);
    expectedMap.put(14, 12);

    // Act
    Map<Integer, Integer> actualMap = interpreter.bracketMap(program);

    // Assert
    assertEquals(expectedMap, actualMap,
        "Expected {4=0, 9=5, 14=12} for program '[+++][---]<<[+]'.");
  }

  /**
   * TEST #4: Tests unmatched '['.
   * Example: "[[" -> should throw IllegalArgumentException
   */
  @Test
  void testBracketMapUnmatchedLeftBracket() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "[[";

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> interpreter.bracketMap(program),
        "Expected bracketMap to throw IllegalArgumentException for unmatched '['.");
  }

  /**
   * TEST #5: Tests unmatched ']'.
   * Example: "]]" -> should throw IllegalArgumentException
   */
  @Test
  void testBracketMapUnmatchedRightBracket() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "]]"; 

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
        () -> interpreter.bracketMap(program),
        "Expected bracketMap to throw IllegalArgumentException for unmatched ']'.");
  }


  @Test
  void testAdd() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "+++";

    // Act
    interpreter.execute(program);

    // Assert
    // The tape should look like: [3]
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(3), tapeData);

    // The tape pointer should be at the head cell, value = 3
    assertEquals(3, interpreter.getTapePointerValue());
  }

  @Test
  void testSubtract() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "---";

    // Act
    interpreter.execute(program);

    // Assert
    // The tape should look like: [-3]
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(-3), tapeData);

    // The tape pointer should be at the head cell, value = -3
    assertEquals(-3, interpreter.getTapePointerValue());
  }

  @Test
  void testRightAndAdd() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = ">>+";

    // Act
    interpreter.execute(program);

    // Assert
    // The tape should look like: [0, 0, 1]
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(0, 0, 1), tapeData);

    // The tape pointer should be at the third cell, value = 1
    assertEquals(1, interpreter.getTapePointerValue());
  }

  @Test
  void testLeftAndAdd() {
      // Arrange
      ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
      String program = "<<++";
  
      // Act
      interpreter.execute(program);
  
      // Assert
      // The tape should look like: [2, 0, 0]
      List<Integer> tapeData = interpreter.getTapeData();
      assertEquals(List.of(2, 0, 0), tapeData);
  
      // The tape pointer should be at the head cell, value = 2
      assertEquals(2, interpreter.getTapePointerValue());
  }
  
  

  @Test
  void testOutput() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    // Adds 88 times, output, then add once, output, then add once, output
    String program = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+.+.";

    // Act
    String result = interpreter.execute(program);

    // Assert
    // The tape should look like: [90]
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(90), tapeData);

    // The tape pointer should be at the head cell, value = 90
    assertEquals(90, interpreter.getTapePointerValue());

    // X has ASCII code 88
    // Y has ASCII code 89
    // Z has ASCII code 90
    // The program should increase to 88, output X, then increase to 89, output Y, then increase to 90, output Z
    assertEquals("XYZ", result);
  }

  @Test
  void testLoop() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();

    // This program executes a multiplication of 3*2
    // Explanation:
    //
    // Increase value of head node to 3
    // while head > 0
    //    move to second node
    //    increase second node by 2
    //    move to head node
    //    decrease head node by 1
    // move to second node
    String program = "+++[>++<-]>";

    // Act
    interpreter.execute(program);

    // Assert
    // The tape should look like: [0, 6]
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(0, 6), tapeData);

    // The tape pointer should be at the second cell, value = 6
    assertEquals(6, interpreter.getTapePointerValue());
  }

  @Test
  void testLoopNested() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();

    // This program executes a multiplication of 4*3*2
    // Explanation:
    //
    // Increase value of head node to 7
    // while head > 0
    //    move to second node
    //    increase second node by 3
    //    while second node > 0
    //       move to third node
    //       increase third node by 5
    //       move to second node
    //       increase decrease second node by 1
    //    move to head node
    //    decrease head node by 1
    // move to second node
    // move to third node
    // output the third node
    String program = "+++++++[>+++[>+++++<-]<-]>>.";

    // Act
    String result = interpreter.execute(program);

    // Assert
    // The tape should look like: [0, 0, 105]
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(0, 0, 105), tapeData);

    // The tape pointer should be at the second cell, value = 6
    assertEquals(105, interpreter.getTapePointerValue());

    assertEquals("i", result);
  }



 // NEW TEST #1: testExecuteEmptyProgram
@Test
void testExecuteEmptyProgram() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String emptyProgram = "";

    // Act
    String output = interpreter.execute(emptyProgram);

    // Assert
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(0), tapeData, "Tape should remain a single node with value 0.");
    assertEquals(0, interpreter.getTapePointerValue(), "Pointer value should still be 0.");
    assertEquals("", output, "Output should be an empty string for empty program.");
}


   //NEW TEST #2: testExecuteInvalidCommands
  @Test
  void testExecuteInvalidCommands() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    // 'xyz' are not valid Screwtape commands; they should be ignored.
    String program = "xyz";

    // Act
    String output = interpreter.execute(program);

    // Assert
    // The tape should remain [0], pointer = 0, no output
    List<Integer> tapeData = interpreter.getTapeData();
    assertEquals(List.of(0), tapeData, "Invalid commands should not modify the tape.");
    assertEquals(0, interpreter.getTapePointerValue(), "Pointer value should remain 0.");
    assertEquals("", output, "No valid '.' commands => empty output.");
  }


   //TEST #3: testExecuteMoveLeftFromHead
  @Test
  void testExecuteMoveLeftFromHead() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();

    String program = "<+";

    // Act
    interpreter.execute(program);

    // Assert
    assertEquals(1, interpreter.getTapePointerValue(),
        "After moving left from the head and incrementing, pointer should hold 1.");
  }


  //NEW TEST #4: testExecuteMoveRightFromTail
  @Test
  void testExecuteMoveRightFromTail() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    // Program: ">>++"
    String program = ">>++";

    // Act
    interpreter.execute(program);

    // Assert
    // The pointer should be two nodes to the right of the original, with value=2.
    assertEquals(2, interpreter.getTapePointerValue(),
        "Pointer should be on the newly created node, which has been incremented to 2.");
  }
}
