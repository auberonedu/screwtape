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
    List<Integer> nullList = null;

    // assert throws is good for testing if code throws a specific exception
    assertThrows(

        // the .class at the end of the illegalarguemtn... is there to ensure that both
        // the throw given by the program and the one we expect are of the same. Almost
        // like === when comparing objects
        IllegalArgumentException.class,
        () -> new Node(nullList),
        "Expected constructor to throw IllegalArgumentException for a null list.");

  }

  // TODO: Add at least one more test for list constructor that would be useful
  // and cover new ground.
  @Test
  void testListConstructorWithOneItem() {
    List<Integer> listOfOne = List.of(1);

    Node head = new Node(listOfOne);
    assertEquals(1, head.value);

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
  void testToListWithNoPrevOrNext() {
    // TODO: Add test for Node with no next or prev
    Node head = new Node(5);

    List<Integer> values = head.toList();

    assertEquals(List.of(5), values);

  }

  // TODO: Add at least one more test for list constructor that would be useful
  // and cover new ground.
  @Test
  void testToListWithNullNode() {
    List<Integer> negativeNumbers = List.of(-1, -2, -3, -4);
    Node head = new Node(negativeNumbers);

    List<Integer> result = head.toList();
    assertEquals(negativeNumbers, result);
  }
}
