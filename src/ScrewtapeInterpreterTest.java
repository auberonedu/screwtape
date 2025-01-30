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

  @Test 
  void testTooManyOpenBrackets() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "[[]";

    // Act/Assert
    assertThrows(IllegalArgumentException.class, () -> interpreter.bracketMap(program));
  }

  @Test 
  void testTooManyClosedBrackets() {
     // Arrange
     ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
     String program = "[]]";
 
     // Act/Assert
     assertThrows(IllegalArgumentException.class, () -> interpreter.bracketMap(program));
  }

  @Test
  void testMultipleSetsBracketMap() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "[+++][---]<<[+]";

    Map<Integer, Integer> expectedMap = new HashMap<>();
    expectedMap.put(4, 0);
    expectedMap.put(9, 5);
    expectedMap.put(14, 12);

    Map<Integer, Integer> actualMap = interpreter.bracketMap(program);

    assertEquals(expectedMap, actualMap);
  }

  @Test
  void testEmptyBracketMap() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "[]";

    Map<Integer, Integer> expectedMap = new HashMap<>();
    expectedMap.put(1, 0);

    Map<Integer, Integer> actualMap = interpreter.bracketMap(program);

    assertEquals(expectedMap, actualMap);
  }

  @Test
  void testNoBracketsBracketMap() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "+-<<>>_+_+";

    Map<Integer, Integer> expectedMap = new HashMap<>();

    Map<Integer, Integer> actualMap = interpreter.bracketMap(program);

    assertEquals(expectedMap, actualMap);
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
}
