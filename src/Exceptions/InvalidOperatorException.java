package Exceptions;

public class InvalidOperatorException extends Exception {

    public InvalidOperatorException(){
        super("The provided operator is not valid.\n");
    }
}
