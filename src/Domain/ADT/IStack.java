package Domain.ADT;

public interface IStack<T> {

    /**
     * Push an element to the top of the stack.
     * @param element - the element to be added in the stack
     */
    void push(T element);


    /**
     * Pop an element from the top of the stack
     * @return - an element of type T which is the one from the top of the stack
     */
    T pop();


    /**
     * Check if the stack is empty
     * @return true - if the stack is empty
     *         false - otherwise
     */
    boolean isEmpty();


    /**
     * String method to get all the elements from the stack.
     * @return - a string containing all the elements from the stack
     */
    String toString();


}
