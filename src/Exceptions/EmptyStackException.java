package Exceptions;

public class EmptyStackException extends RuntimeException {

    public EmptyStackException(){
        super("The stack is empty!\n");
    }
}
