package Exceptions;

public class HeapWritingException extends Exception {

    public HeapWritingException(){
        super("Heap writing error!\n");
    }
}
