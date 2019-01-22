package Exceptions;

public class VariableNotFound extends Exception {

    public VariableNotFound(){
        super("The variable was not defined in the symbol table!\n");
    }
}
