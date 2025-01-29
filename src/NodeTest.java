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
        "Expected constructor to throw IllegalArgumentException for an empty list.");
  }

  @Test
  void testListConstructorWithNullList() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Node(null),
        "Expected constructor to throw IllegalArgumentException for a null list.");
  }

  @Test
  void testToListWithSingleNode() {
    Node head = new Node(42);

    List<Integer> values = head.toList();

    assertEquals(List.of(42), values);
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

  @Test
  void testListConstructorWithLargeList() {
    // Arrange
    List<Integer> values = new ArrayList<>();
    for (int i = 1; i <= 1000; i++) {
      values.add(i);
    }

    // Act
    Node head = new Node(values);
    List<Integer> result = head.toList();

    // Assert
    assertEquals(values, result);
  }

}
