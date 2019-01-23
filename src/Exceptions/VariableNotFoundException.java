package Exceptions;

public class VariableNotFoundException extends RuntimeException {

    public VariableNotFoundException() {
        super("The variable was not defined in the table!\n");
    }
}
