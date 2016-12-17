/**
 * Queue
 * 
 * @author Zilong Wang Last Modified: <02-082-2016> - <adding comments> <Zilong
 *         Wang>
 * @version 1.0
 */
public class Queue<T extends Comparable<T>> extends SLL<T>
{
    /**
     * add t into tail
     * 
     * @param t
     */
    public void enqueue(T t)
    {
	addTail(t);
    }

    /**
     * get t from head and delete it from queue
     * 
     * @return t
     */
    public T dequeue()
    {
	return deleteHead();
    }

    /**
     * get t from head, don't remove it
     * 
     * @return t
     */
    public T peek()
    {
	final int HEAD = 0;
	return get(HEAD);
    }

    /**
     * check if queue is empty
     * 
     * @return true if it is empty
     */
    public boolean isEmpty()
    {
	return size() == 0;
    }

    /**
     * empty the queue
     */
    public void empty()
    {
	emptyList();
    }
}
