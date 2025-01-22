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
    // DONE: implement this
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException("The list cannot be empty or null.");
    }

    // Creating the first node using the first value from the list
    this.value = list.get(0);
    Node current = this; // head 

    // Single element list handling
    if (list.size() == 1) {
      current.next = null; // no next node
      current.prev = null; // no previous node
      return;
    }

    // Iterating
    for (int i = 1; i < list.size(); i++) {
      // Creating new nodes using the next values from the list
      Node newNode = new Node(list.get(i));
      current.next = newNode; // Linking current node to new one
      newNode.prev = current; // Linking new node to the current one
      current = newNode; // Moving to the new node
    }

    current.next = this; 
    this.prev = current; 
  }

  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() {
    // TODO: Implement this
    List<Integer> result = new ArrayList<>();
    Node current = this;
    
    return null;
  }
}
