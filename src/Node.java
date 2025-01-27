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
    // Checking if the list is null or empty
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException("List cannot be empty or null");
    }

    // Setting the value of the head node to the first value of the list
    this.value = list.get(0);

    // Checking if the size of the list is only 1 element
    // If so both prev and next are assigned to null and the method returns
    if (list.size() == 1) {
      this.prev = null;
      this.next = null;
      return;
    }

    // Initializing a reference to the current node of the LinkedList 
    Node current = this;

    // Iterating through the list starting from index 1
    for (int i = 1; i < list.size(); i++) {
      // Initializing a new node for the next value in the list
      Node nextNode = new Node(list.get(i));

      // Setting the prev reference of the nextNode to the current node
      nextNode.prev = current;

      // Setting the next reference of the current node to the nextNode
      current.next = nextNode;

      // Setting the current node to the nextNode
      current = nextNode;
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
    // Initializing a reference to the current node of the LinkedList and
    // initializing a new ArrayList to store the values of each node
    Node current = this;
    List<Integer> list = new ArrayList<>();

    // Looping over all nodes until the current node is null
    while (current != null) {
      // Adding the value of the current node to the list
      list.add(current.value);

      // Moving to the next node in the LinkedList
      current = current.next;
    }

    // Returning the list of all values from each node
    return list;
  }
}
