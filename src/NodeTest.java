import org.junit.jupiter.api.Test;

import java.beans.Transient;
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
        "Expected constructor to throw IllegalArgumentException for an empty list.");
  }

  // TODO: Add test for list constructor when passed null list
  @Test
  void testListConstructorWithNullList() {
    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new Node(null),
        "Expected constructor to throw IllegalArgumentException for a null list.");
  }

  // TODO: Add at least one more test for list constructor that would be useful
  // and cover new ground.

  @Test
  void testListCondstructorWithSingleValue() {
    // Arrange
    List<Integer> values = List.of(42);

    // Act
    Node head = new Node(values);

    // Assert
    assertEquals(42, head.value);
    assertNull(head.next);
    assertNull(head.prev);

  }

  @Test
  void testListConstructorWithLargeList() {
    // Arrange
    List<Integer> values = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      values.add(i);
    }
    // Act
    Node head = new Node(values);

    // Assert
    Node current = head;
    for (int i = 0; i < 1000; i++) {
      assertNotNull(current);
      assertEquals(i, current.value);
      current = current.next;
    }
    assertNull(current);
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
  // TODO: Add at least one more test for list constructor that would be useful
  // and cover new ground.
}
