package Domain.ADT;

import java.util.Queue;

public interface IList<T> {

    /**
     * Adds an element to the list.
     *
     * @param element - the element to be added
     */
    void add(T element);


    /**
     * Pops the element from the head of the list.
     *
     * @return the first T in the list
     */
    T pop();


    /**
     * Checks if the list is empty.
     *
     * @return <code>true</code>- if the list is empty
     * <code>false </code> - otherwise
     */
    boolean isEmpty();

    /**
     * Gets all the elements from the list.
     *
     * @return a string with all the elements
     */
    String toString();

    /**
     * Gets the queue.
     *
     * @return the queue
     */
    Queue<T> getQueue();

}
