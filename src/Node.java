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
    if(list == null || list.isEmpty() ){
      throw new IllegalArgumentException("The list is null or empty");
    }
    //making a start of this list
    // since were working inside of a constructor we have to use 'this'
    this.value = list.get(0);
    Node current = this;

    //WHENEVER TRANVERSING A NODE LIST ALWAYS USE CURRENT TO MOVE ALONG
   // starting at 1 since 0 is the first value already 
    for (int i = 1; i < list.size(); i++) {


      Node newNode = new Node(list.get(i));
      current.next = newNode;
      newNode.prev = current;

      //this is moving it along
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
    Node head = this;
    Node current  = head;

    List<Integer> listOfInts = new ArrayList<>();

    if(head == null){
      throw new IllegalArgumentException("The node is null or empty");
    }
    while(current != null){
      listOfInts.add(current.value);
      current = current.next;
      
    }
    
    return listOfInts;
  }
}
