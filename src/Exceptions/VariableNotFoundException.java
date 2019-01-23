package Exceptions;

public class VariableNotFoundException extends Exception {

    public VariableNotFoundException() {
        super("The variable was not defined in the table!\n");
    }
}
