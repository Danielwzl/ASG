/**
 * Single LinkedList class
 * 
 * @author Zilong Wang Last Modified: <01-22-2016> - <adding comments>
 * @version 2.0
 */
public class SLL<E extends Comparable<E>>
{
    private Node head, tail;
    /**
     * this class represents one node which can point to next node
     */
    private class Node
    {
	E e; // actual item
	Node next;

	public Node(E e)
	{
	    this.e = e;
	}

	public String toString()
	{
	    return e.toString();
	}
    }

    /**
     * add the new element into list by nature ordering. firstly, try to compare
     * with head and tail and insert into any of these spots. otherwise insert
     * element into the list between head and tail.
     * 
     * @param e
     */
    public void add(E e)
    {
	Node newNode = new Node(e);
	if(head == null) tail = head = newNode;
	else if(e.compareTo(tail.e) > 0) addTail(newNode);
	else if(e.compareTo(head.e) < 0) addHead(newNode);
	else
	{
	    Node curr = head;
	    while(e.compareTo(curr.next.e) > 0)
	    {
		curr = curr.next;
	    }
	    newNode.next = curr.next;
	    curr.next = newNode;
	}
    }

    /**
     * add the new element into list by new ordering. firstly, try to compare
     * with head and tail and insert into any of these spots. otherwise insert
     * element into the list between head and tail.
     * 
     * @param e
     * @param cpr
     */
    public void add(E e, java.util.Comparator<E> cpr)
    {
	Node newNode = new Node(e);
	if(head == null) tail = head = newNode;
	else if(cpr.compare(e, tail.e) > 0) addTail(newNode);
	else if(cpr.compare(e, head.e) < 0) addHead(newNode);
	else
	{
	    Node curr = head;
	    while(cpr.compare(e, curr.next.e) > 0)
	    {
		curr = curr.next;
	    }
	    newNode.next = curr.next;
	    curr.next = newNode;
	}
    }

    /**
     * add element into the head
     * 
     * @param node
     */
    private void addTail(Node node)
    {
	tail.next = node;
	tail = node;
    }

    /**
     * add element into the tail
     * 
     * @param node
     */
    private void addHead(Node node)
    {
	node.next = head;
	head = node;
    }

    /**
     * to check there is the same element in this list it uses equals method of
     * E
     * 
     * @param e
     * @return true if there is the same element in the list
     */
    public boolean contains(E e)
    {
	Node curr = head;
	while(curr != null)
	{
	    if(curr.e.equals(e)) return true;
	    else curr = curr.next;
	}
	return false;
    }

    /**
     * print the some of elements from the list
     * 
     * @param top:
     *            the maximum number in the list that needs to get
     */
    public void getUntil(int top)
    {
	int count = 0;
	Node curr = head;
	while(count < top)
	{
	    System.out.println(curr);
	    curr = curr.next;
	    count++;
	}
    }

    /**
     * print all of the elements from the list
     */
    public void getAll()
    {
	Node curr = head;
	while(curr != null)
	{
	    System.out.println(curr);
	    curr = curr.next;
	}
    }

    /**
     * re-order the order of elements in the list
     * 
     * @param cpr
     * @return the new list with new order
     */
    public SLL<E> reOrderList(java.util.Comparator<E> cpr)
    {
	SLL<E> newList = new SLL<E>();
	Node curr = head;
	while(curr != null)
	{// point the pointer to new position in the new list
	    newList.add(curr.e, cpr);
	    curr = curr.next;
	}
	return newList;
    }
}
