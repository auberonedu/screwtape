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

  // TODO: Add test for list constructor when passed null list
  @Test
  void testListConstructorWithNullList() {
    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new Node(null),
        "Expected constructor to throw IllegalArgumentException for an empty list.");
  }

  // TODO: Add at least one more test for list constructor that would be useful
  // and cover new ground.
  @Test
  void testListConstructorWithOneValue() {
    // Arrange
    List<Integer> values = List.of(47);

    // Act
    Node head = new Node(values);

    // Assert
    assertEquals(47, head.value);
    assertNull(head.next);
    assertNull(head.prev);
  }

  @Test
  void testListConstructorWithLargeValue() {
    // Arrange
    List<Integer> values = List.of(4700, 12322, 93939);

    // Act
    Node head = new Node(values);

    // Assert
    assertEquals(4700, head.value);
    assertNotNull(head.next);
    assertEquals(12322, head.next.value);
    assertNotNull(head.next.next);
    assertEquals(93939, head.next.next.value);
    assertNull(head.next.next.next);
    assertEquals(head, head.next.prev);
    assertEquals(head.next, head.next.next.prev);
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
  void testToListWithNoNextOrPrev() {
    // Arrange
    Node head = new Node(5);

    // Act
    List<Integer> values = head.toList();

    // Assert
    assertEquals(List.of(5), values);
  }

  // TODO: Add at least one more test for list constructor that would be useful
  // and cover new ground.
  @Test
  void testToListWithManyValues() {
    // Arrange
    Node head = new Node(10);
    Node second = new Node(25);
    Node third = new Node(76);
    Node fourth = new Node(84);
    Node fifth = new Node(33);
    Node sixth = new Node(10);
    Node tail = new Node(123);

    head.next = second;
    second.prev = head;
    second.next = third;
    third.prev = second;
    third.next = fourth;
    fourth.prev = third;
    fourth.next = fifth;
    fifth.prev = fourth;
    fifth.next = sixth;
    sixth.prev = fifth;
    sixth.next = tail;
    tail.prev = sixth;

    // Act
    List<Integer> values = head.toList();

    // Assert
    assertEquals(List.of(10, 25, 76, 84, 33, 10, 123), values);
  }
}
