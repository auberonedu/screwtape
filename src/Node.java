import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Node in a doubly linked list.
 */
public class Node {
  /** The value stored in this node. */
  public int value;

  /** The previous node in the linked list. */
  public Node prev;

  /** The next node in the linked list. */
  public Node next;

  /**
   * Creates a single node with the specified value.
   * prev and next are kept as null.
   *
   * @param value The value to store in this node.
   */
  public Node(int value) {
    this.value = value;
  }

  /**
   * Creates a doubly linked list from the given list of integers.
   * The head of the list will be the constructed Node.
   * 
   * For example, if there were the list:
   * [5, 7, 3]
   * 
   * Then the linked list that would be created would be:
   * 5 <-> 7 <-> 3
   * And this node would be the head, holding a value of 5.
   *
   * @param list The list of integers to initialize the doubly linked list.
   * @throws IllegalArgumentException If the list is null or empty.
   */
  public Node(List<Integer> list) {
    // TODO: implement this
       if (list == null || list.isEmpty()) {
       throw new IllegalArgumentException("The list cannot be empty or null.");
    
     }

    // Initialize this node with the first element's value
       value = list.get(0);

    // Keep track of the current node as we build the rest
       Node current = this;


      // Loop through the remaining elements in the list, starting at index 1
      // because we've already taken care of index 0 for the head node's value.
      for (int i = 1; i < list.size(); i++) {

      // Creating a new Node to hold the current element from the list
      Node newNode = new Node(list.get(i));

     // Link the current node's 'next' pointer to our new node
     current.next = newNode;

     // Link the new node's 'prev' pointer back to the current node
     newNode.prev = current;

     // Advance 'current' so that it now refers to this new node
     current = newNode;
 }

    
  }

  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() {
    // TODO: Implement this
     // Create an empty list to store the node values
    List<Integer> result = new ArrayList<>();
    // Starting from 'this' node and walking forward
    Node current = this;
    while (current != null) {
        // Add the current node's value to the list
        result.add(current.value);
        // Move to the next node
        current = current.next;
    }

    return result;
  }
}
