package Exceptions;

public class InvalidOperatorException extends RuntimeException {

    public InvalidOperatorException(){
        super("The provided operator is not valid.\n");
    }
}
