import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

  // -------- WAVE 1 -------

  @Test
  void testListConstructorWithThreeValues() {
    // Arrange
    List<Integer> values = List.of(5, 7, 3);

    // Act
    Node head = new Node(values);

    // Assert
    assertEquals(5, head.value);
    assertNotNull(head.next);
    assertEquals(7, head.next.value);
    assertNotNull(head.next.next);
    assertEquals(3, head.next.next.value);
    assertNull(head.next.next.next);
    assertEquals(head, head.next.prev);
    assertEquals(head.next, head.next.next.prev);
  }

  @Test
  void testListConstructorWithEmptyList() {
    // Arrange
    List<Integer> emptyList = new ArrayList<>();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new Node(emptyList),
        "Expected constructor to throw IllegalArgumentException for an empty list."
    );
  }

  
  // TODO: Add test for list constructor when passed null list
  @Test
  void testListConstructorWithNullList() {

    // Arrange 
    List<Integer> nullList = null;

    // Act and Assert
    assertThrows(
      IllegalArgumentException.class, 
      () -> new Node(nullList),
      "Expected construtor to throw IllegalArgumentException for a null list."
    );
  }

  // DONE
  // TODO: Add at least one more test for list constructor that would be useful and cover new ground.
  @Test
  void testListConstructorWithRepeatedElements() {

    // Arrange 
    List<Integer> coupleOfElements = List.of(2, 2);

    // Act and Assert
    Node head = new Node(coupleOfElements);
    
    assertEquals(2, head.value);
    assertNotNull(head.next);
    assertEquals(2, head.next.value);
    assertNull(head.next.next);

    assertEquals(head, head.next.prev);
  }

  // -------- WAVE 2 -------

  @Test
  void testToListWithThreeValues() {
    // Arrange
    Node head = new Node(5);
    Node middle = new Node(7);
    Node tail = new Node(3);

    head.next = middle;
    middle.prev = head;
    middle.next = tail;
    tail.prev = middle;

    // Act
    List<Integer> values = head.toList();

    // Assert
    assertEquals(List.of(5, 7, 3), values);
  }

  // TODO: Add test for Node with no next or prev
  @Test
  void testToListWithNoNextOrNoPrev() {
    // Arrange
    Node oneNode = new Node(2);

    // Act and Assert
    List<Integer> result = oneNode.toList();
    
    assertEquals(1, result.size());
    assertEquals(2, result.get(0));
    
  }

  // TODO: Add at least one more test for list constructor that would be useful and cover new ground.
}
