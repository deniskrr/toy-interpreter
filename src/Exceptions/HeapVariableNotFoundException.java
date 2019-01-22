package Exceptions;

public class HeapVariableNotFoundException extends Exception{

    public HeapVariableNotFoundException(){
        super("Not defined in heap\n");
    }
}
