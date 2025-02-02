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

        // Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new Node(emptyList),
            "Expected constructor to throw IllegalArgumentException for an empty list."
        );
    }

    // NEW TEST #1: Null List
    @Test
    void testListConstructorWithNullList() {
        // Arrange
        List<Integer> nullList = null;

        // Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new Node(nullList),
            "Expected constructor to throw IllegalArgumentException for a null list."
        );
    }

    // NEW TEST #2: Single-Value List
    @Test
    void testListConstructorWithSingleValue() {
        // Arrange
        List<Integer> singleValueList = List.of(42);

        // Act
        Node head = new Node(singleValueList);

        // Assert
        assertEquals(42, head.value);
        assertNull(head.next, "The next pointer should be null for a single-element list.");
        assertNull(head.prev, "The prev pointer should be null for a single-element list.");
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

    @Test
    void testToListWithSingleNode() {
        // This test fulfills the "Node with no next or prev" situation.
        Node single = new Node(100);

        List<Integer> result = single.toList();

        // Assert
        assertEquals(List.of(100), result,
                "A single-node list should return a list containing only 100.");
    }

    @Test
    void testToListStartingFromMiddleNode() {
        // This test ensures that if we treat a middle node as "head",
        // toList() only gathers values by traversing forward, ignoring 'prev'.
        
        Node head = new Node(10);
        Node middle = new Node(20);
        Node tail = new Node(30);

        head.next = middle;
        middle.prev = head;
        middle.next = tail;
        tail.prev = middle;

        // Act
        // Start toList from the 'middle' node
        List<Integer> result = middle.toList();

        // Assert
        // We expect [20, 30], since we start at 'middle' and move forward
        assertEquals(List.of(20, 30), result,
                "Starting at the middle node should return [20, 30], ignoring the prev pointer.");
}}
