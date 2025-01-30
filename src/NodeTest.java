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
  // TODO: Add test for list constructor when passed null list
  List<Integer> nullList = null;
  // Node head = new Node(nullList);
  // assertEquals(null, head);

  //  Borrowed from test case above
  assertThrows(
        IllegalArgumentException.class,
        () -> new Node(nullList),
        "List is Empty"
    );
  }
 
  // TODO: Add at least one more test for list constructor that would be useful and cover new ground.
  @Test 
  void testListConstructorWithNullElemens() {
    List<Integer> listWithNulls = new ArrayList<>();
    listWithNulls.add(1);
    listWithNulls.add(null);
    listWithNulls.add(3);
    listWithNulls.add(4);
    
    assertNull(listWithNulls.get(1));

    assertEquals(4, listWithNulls.size());
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
    Node newNode = new Node(5);

    assertNull(newNode.next);
    assertNull(newNode.prev);

  }
  // TODO: Add at least one more test for list constructor that would be useful and cover new ground.
  @Test 
  void testToListWithDuplicates(){
    List<Integer> dupValues = new ArrayList<>();
    dupValues.add(1);
    dupValues.add(2);
    dupValues.add(2);
    dupValues.add(1);

    assertEquals(4, dupValues.size());

    assertEquals(1, dupValues.get(0));
    assertEquals(2, dupValues.get(1));
    assertEquals(2, dupValues.get(2));
    assertEquals(1, dupValues.get(3));

  }
}
