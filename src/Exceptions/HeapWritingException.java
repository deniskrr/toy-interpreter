package Exceptions;

public class HeapWritingException extends RuntimeException {

    public HeapWritingException(){
        super("HeapTable writing error!\n");
    }
}
