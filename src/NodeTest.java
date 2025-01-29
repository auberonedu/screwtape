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


  @Test
  void testListConstructorWithNullList(){
    List<Integer> nullList = null;

    assertThrows(
        IllegalArgumentException.class,
        () -> new Node(nullList),
        "Expected constructor to throw IllegalArgumentException for an empty list."
    );
  }

  @Test
  void testListConstructorWithFourValues(){
    List<Integer> values = List.of(3, 9, 7, 2);

    Node head = new Node(values);

    assertEquals(3, head.value);
    assertNull(head.prev);
    assertEquals(9, head.next.value);
    assertNotNull(head.next.next);
    assertEquals(7, head.next.next.value);
    assertNotNull(head.next.next.next);
    assertEquals(2, head.next.next.next.value);
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
  void testToListWithOneValue() {
    Node head = new Node(2);

    List<Integer> value = head.toList();

    assertEquals(List.of(2), value);
  }
  
  @Test
  void testToListWithSecondNode() {
    Node head = new Node(5);
    Node middle1 = new Node(9);
    Node middle2 = new Node(7);
    Node tail = new Node(6);

    head.next = middle1;
    middle1.prev=head;
    middle1.next=middle2;
    middle2.next=tail;
    tail.prev=middle2;

    List<Integer> values = middle1.toList();
    assertEquals(List.of(9, 7, 6), values);

  }
}
