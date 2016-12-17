import java.util.Comparator;
import java.util.Iterator;

/**
 * Binary search tree
 * @author Zilong Wang Last Modified: <02-29-2016> - <adding iterator implemented by array> <Zilong Wang>
 * @version 3.0
 */
public class BST<E extends Comparable<E>> implements Iterable<E>
{
    private Comparator<E> cpr;
    private Node root;
    private int size, modCount;
    private E[] datas;

    public BST()
    {}

    public BST(Comparator<E> cpr)
    {
	this.cpr = cpr;
    }
    /*
     * Node inner class
     */
    private class Node
    {
	Node left, right;
	E e;

	public Node(E e)
	{
	    this.e = e;
	}
    }

    /**
     * get the pointer if the element is in the tree
     * 
     * @param e
     * @return the pointer to the same element
     */
    public E search(E e)
    {
	return search(root, e);
    }

    /**
     * get the pointer if the element is in the tree
     * 
     * @param node
     * @param e
     * @return the pointer to the same element
     */
    private E search(Node node, E e)
    {
	if(node == null) return null;
	int cmp = compareItem(e, node.e);
	if(cmp == 0) return node.e;
	else if(cmp < 0) return search(node.left, e);
	else return search(node.right, e);
    }

    /**
     * insert a element into tree
     * 
     * @param e
     */
    public void insert(E e)
    {
	if(root == null)
	{
	    root = new Node(e);
	    size++;
	}
	else insert(root, e);
    }

    /**
     * insert a element into tree in this asg there is no equal case
     * 
     * @param node
     * @param e
     */
    private void insert(Node node, E e)
    {
	if(compareItem(e, node.e) < 0)
	{
	    if(node.left == null)
	    {
		node.left = new Node(e);
		size++;
	    }
	    else insert(node.left, e);
	}
	else
	{
	    if(node.right == null)
	    {
		node.right = new Node(e);
		size++;
	    }
	    else insert(node.right, e);
	}
    }

    /**
     * find the node and parent of the node from the tree, reaady to delete them
     * @param e
     * @return deleted node, null return if no node found
     */
    public E delete(E e)
    {
	int cmp = 0;
	Node curr = root, parent = curr;
	while(curr != null)
	{
	    cmp = compareItem(e, curr.e);
	    if(cmp == 0) break;
	    parent = curr;
	    if(cmp > 0) curr = curr.right;
	    else if(cmp < 0) curr = curr.left;
	}
	if(curr == null) return null;
	fixAfterDelete(curr, parent);
	size--;
	return curr.e;
    }

    /**
     * delete node from the tree and fix the tree
     * @param curr
     * @param parent
     */
    private void fixAfterDelete(Node curr, Node parent)
    {
	if(curr.left == null && curr.right == null) //no children
	{
	    if(curr == root) root = null;
	    else if(curr == parent.left) parent.left = null;
	    else parent.right = null;
	}
	else if(curr.left != null && curr.right != null) //two children
	{
	    Node parOfSucc = parentOfSuccessor(curr);
	    if(parOfSucc == curr) //the right of current node has no left child
	    {
		Node successor = parOfSucc.right;
		curr.e =  successor.e;
		curr.right = successor.right;
	    }
	    else //the right of current node has left children
	    {
		Node successor = parOfSucc.left;
		curr.e = successor.e; // assign the value from successor to deleted node
		parOfSucc.left = successor.right; // disconnect
	    }
	}
	else //one children
	{
	    if(curr == root)
	    {
		if(curr.right == null) root = curr.left;
		else root = curr.right;
	    }
	    else if(curr == parent.left)
	    {
		if(curr.left != null) parent.left = curr.left;
		else parent.left = curr.right;
	    }
	    else
	    {
		if(curr.left != null) parent.right = curr.left;
		else parent.right = curr.right;
	    }
	}
    }

    /**
     * find the parent of successor only for the situation that current node has
     * two children
     * 
     * @param curr
     * @return parent of successor of the node that need to be delete
     */
    private Node parentOfSuccessor(Node curr)
    {
	Node prev = curr;
	curr = curr.right; // the first right path of the deleted node
	while(curr.left != null) // get the parent of its successor
	{
	    prev = curr;
	    curr = curr.left;
	} 
	return prev;
    }

    /**
     * compare either using comparator or comparable
     * 
     * @param e1
     * @param e2
     * @return positive(e1 > e2) 0(=) or negative numbers(e1 < e2)
     */
    private int compareItem(E e1, E e2)
    {
	if(cpr == null) return e1.compareTo(e2);
	return cpr.compare(e1, e2);
    }

    /**
     * it is a iterator which can traverse the tree of in-order traverse
     * if someone used get method to generate element array, then just use it
     * @return iterator
     */
    @SuppressWarnings("unchecked")
    public Iterator<E> iterator()
    {	
	if(datas == null) datas = getAll((E[]) new Comparable[size]);
	
	/*
	 * inner iterator class
	 */
	Iterator<E> itr = new Iterator<E>()
	{
	    int index = 0;

	    @Override
	    public boolean hasNext()
	    {
		return index != size;
	    }

	    @Override
	    public E next()
	    {
		return datas[index++];
	    }
	};
	return itr;
    }
    
    /**
     * get the element from the tree
     * generate element ary if iterator never been called
     * @param index
     * @return element
     */
    @SuppressWarnings("unchecked")
    public E get(int index)
    {
	if(datas == null)
	{
	    datas = (E[])new Comparable[size];
	    getAll(datas);
	}
	if(index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException");
	return datas[index];
    }

    /**
     * put data from three in order into array
     * 
     * @param datas
     * @return array of data
     */
    private E[] getAll(E[] datas)
    {
	modCount = 0;
	return getAll(datas, root);
    }

    /**
     * put data from three in order into array
     * 
     * @param datas
     * @param node
     * @return array of data
     */
    private E[] getAll(E[] datas, Node node)
    {
	if(node != null)
	{
	    getAll(datas, node.left);
	    datas[modCount++] = node.e;
	    getAll(datas, node.right);
	}
	return datas;
    }

    /**
     * height of the tree
     * @return height
     */
    public int height()
    {
	return height(root);
    }

    /**
     * height of the tree
     * @param node
     * @return height
     */
    private int height(Node node)
    {
	if(node != null) return Math.max(height(node.left), height(node.right)) + 1;
	return 0;
    }
  
    /**
     * get the value of the very left node in the tree
     * @return value of the left node
     */
    public E getVeryLeft()
    {
	if(root == null) return null;
	Node curr = root;
	while(curr.left != null)
	    curr = curr.left;
	return curr.e;
    }
    
    /**
     * total nodes in the tree
     * @return size of tree
     */
    public int size()
    {
	return size;
    }
}