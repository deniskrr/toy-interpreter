package Domain.ADT;

import java.util.Stack;

public interface IStack<T> {

    /**
     * Pushes an element to the top of the stack.
     *
     * @param element - the element to be pushed to the stack
     */
    void push(T element);


    /**
     * Pops an element from the top of the stack.
     *
     * @return - the top of the stack
     */
    T pop();

    /**
     * Gets the top of the stack.
     *
     * @return - the top of the stack
     */
    T peek();

    /**
     * Check if the stack is empty.
     *
     * @return <code>true</code> - if the stack is empty
     * <code>false </code> - otherwise
     */
    boolean isEmpty();

    /**
     * Gets the stack.
     *
     * @return the stack
     */
    Stack<T> getStack();

    /**
     * Gets all the elements from the stack.
     *
     * @return a string with all the elements
     */
    String toString();


}
