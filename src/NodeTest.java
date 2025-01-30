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

  
  // Test for list constructor when passed null list
  @Test
  void testListConstructorWithNullList() {
    assertThrows(IllegalArgumentException.class, () -> new Node(null), "Cannot pass null as a list");
  }

  // Add at least one more test for list constructor that would be useful and cover new ground
  @Test
  void testListConstructorWithSingleElement() {
    List<Integer> singleValue = List.of(12);
    Node head = new Node(singleValue);

    assertEquals(12, head.value, "Head should store correct value");
    assertNull(head.next, "Next should be null cause there is only one node(head)");
    assertNull(head.prev, "Prev should be null cause there is only one node(head)");
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
  // TODO: Add at least one more test for list constructor that would be useful and cover new ground.
}
