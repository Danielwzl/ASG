/**
 * Stack
 * 
 * @author Zilong Wang Last Modified: <02-082-2016> - <adding comments> <Zilong
 *         Wang>
 * @version 1.0
 */
public class Stack<T extends Comparable<T>> extends SLL<T>
{
    /**
     * push t into head
     * 
     * @param t
     */
    public void push(T t)
    {
	addHead(t);
    }

    /**
     * get t from head and remove it from stack
     * 
     * @return t
     */
    public T pop()
    {
	return deleteHead();
    }

    /**
     * get t from head, do not remove it
     * 
     * @return t
     */
    public T peek()
    {
	final int HEAD = 0;
	return get(HEAD);
    }

    /**
     * check if stack is empty
     * 
     * @return true if it is empty
     */
    public boolean isEmpty()
    {
	return size() == 0;
    }

    /**
     * empty the stack
     */
    public void empty()
    {
	emptyList();
    }
}
