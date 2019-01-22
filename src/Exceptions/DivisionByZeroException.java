package Exceptions;

public class DivisionByZeroException extends Exception {

    public DivisionByZeroException(){
        super("You cannot divide by 0!\n");
    }
}