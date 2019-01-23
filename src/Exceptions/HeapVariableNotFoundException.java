package Exceptions;

public class HeapVariableNotFoundException extends RuntimeException {

    public HeapVariableNotFoundException(){
        super("Not defined in heap\n");
    }
}
