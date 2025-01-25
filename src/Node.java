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
    // handle null or empty lists
    if (list == null || list.size() == 0) {
      throw new IllegalArgumentException("Node constructor requires List of at least 1 integer");
    // handle lists with one item
    } else if (list.size() == 1) {
      value = list.get(0);
      // These technically do not need to be set to null, as that is their default value,
      // but I think it helps to explicitly assign it here
      next = null;
      prev = null;
    } else {
    
      value = list.get(0);
      next = new Node(list.subList(1, list.size()));
      next.prev = this;
    }
  }

  public Node(List<Integer> list, Node prev) {

  }

 

  // [1, 2, 3]
  // node (1), next -> node (2) -> node (3)

  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() {
    // TODO: Implement this
    return null;
  }
}
