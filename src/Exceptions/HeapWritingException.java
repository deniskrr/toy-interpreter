package Exceptions;

public class HeapWritingException extends Exception {

    public HeapWritingException(){
        super("HeapTable writing error!\n");
    }
}
