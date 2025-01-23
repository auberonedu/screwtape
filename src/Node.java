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
    if (list == null || list.isEmpty()){
      throw new IllegalArgumentException("List cannot be null or empty");
    }

    // if list has 1 element, set head value to that element (at index 0), 
    // and leave prev and next fields null
    this.value = list.get(0);

    // if list has more than 1 element, create a new Node with the value at index 1,
    // set it's prev to point at the constructed node and point this.next at it. 
    if (list.size() > 1){
      Node curr = new Node(list.get(1));
      this.next = curr;
      Node past = this;

      // iterate through the rest of the list
      for(int i = 2; i < list.size(); i++){
        // set curr.next to point at a new node
        curr.next = new Node(list.get(i));
        // set curr.prev to point at the past node (starting at the head node)
        curr.prev = past;
        // increment curr and past nodes. curr now points at the new node
        // and past now points at the node behind curr
        past = past.next;
        curr = curr.next;
      }
      // set prev node for the last node of the list
      curr.prev = past;
    }
    
  }

  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() {
    List<Integer> result = new ArrayList<>();
    Node curr = this;
    while (curr != null){
      result.add(curr.value);
      curr = curr.next;
    }

    return result;
  }
}
