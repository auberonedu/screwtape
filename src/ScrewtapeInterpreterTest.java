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
  void testNestedBracketMapExtraOpen() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "[[]]>[+>[+-]<";
    //act/asssert
    assertThrows(IllegalArgumentException.class, () -> interpreter.bracketMap(program));
  }

  @Test
  void testNestedBracketClosingOnly() {
    // Arrange
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    String program = "++++]";
    assertThrows(IllegalArgumentException.class, () -> interpreter.bracketMap(program));
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

  @Test
  /*
   * s-115, c- 99, r-114, e- 101, w-119 
   * t- 116 a- 97 p 112, 
   * S- 83 C- 67 R- 82 E- 69, W-87
   * T- 84 A- 65 P- 80
   * !- 33 space - 32
   */

   // want a indicator for i can do a loop of 10 to get 30, 70 and 100 indicator 
  void testScrewtape(){
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    ScrewtapeInterpreter interpreter2 = new ScrewtapeInterpreter();
   // first attempt 30/70/99
    String program = "++++++++++[>+++>+++++++>++++++++++<<<-]>>>+++++++++++++++.----------------.+++++++++++++++.-------------.++++++++++++++++++.---.-------------------.+++++++++++++++.-----------.<<++.>+++++++++++++.----------------.+++++++++++++++.-------------.>--------------.---.<----.>----.<++++.<.>>+++++++++++++++++++++++++++++++++++.----------------.+++++++++++++++.-------------.++++++++++++++++++.---.-------------------.+++++++++++++++.-----------.<<.>++++++++++++++.----------------.+++++++++++++++.-------------.>--------------.---.<----.>----.<++++.<.>>+++++++++++++++++++++++++++++++++++.----------------.+++++++++++++++.-------------.++++++++++++++++++.---.-------------------.+++++++++++++++.-----------.<<+...";
    String program2 = "+++++++++++[>+++>++++++>++++++++>+++++++++>++++++++++<<<<<-]>>>>>+++++.<.>-.<++.>+++++.---.<----.>----.<++++.<<<-.>>-----.<+.>-.<++.>+++++.---.<----.>----.<++++.<.>>>>+++.<--.>-.<++.>+++++.---.<----.>----.<++++.<<<.>>+++.<--.>-.<++.>+++++.---.<----.>----.<++++.<.>>>>+++.<--.>-.<++.>+++++.---.<----.>----.<++++.<<<+...";
   // second attempt 0, 33, 66, 88, 99, 110 loop initialization, could not figure out the loop for screwtape SCREWTAPE third attempt will be to try and get the loop correct
   //0 32, 69, 80,101, 112

    // Act 
    String result = interpreter.execute(program);
    String result2 = interpreter2.execute(program2);
    assertEquals("screwtape SCREWTAPE screwtape SCREWTAPE screwtape!!!", result);
    assertEquals("screwtape SCREWTAPE screwtape SCREWTAPE screwtape!!!", result2);
  }
  
@Test 
void shortestScrewTest(){

  ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
  String program =  "+++++++++++[>+++>++++++>++++++++>+++++++++>++++++++++<<<<<-]++[>>>>>+++++.<.>-.<++.>+++++.---.<----.>----.<++++.<<<-.>>-----.<+.>-.<++.>+++++.---.<----.>----.<++++.<.+>--->++++++++>-->--<<<<<-]>>>>>+++++.<.>-.<++.>+++++.---.<----.>----.<++++.<<<...";
  String result = interpreter.execute(program);
  assertEquals("screwtape SCREWTAPE screwtape SCREWTAPE screwtape!!!", result);
//0, 33, 66, 88, 99, 115;

/* indexes before start of one loop of screw tape
[1] = ! (33)
[2] = B (66)
[3] = X (88)
[4] = c (99)
[5] = n (110)   

values after one loop of screwtape
[0] = (0)
[1] = (32)
[2] = E (69)
[3] = P (80)
[4] = e (101)
[5] = p (112)
*/
}
}
