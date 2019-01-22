package Domain.Statements;

import Domain.PrgState;
import Exceptions.*;

public interface IStmt {

    /**
     * Execute the statement
     * @param state - the current state of the program
     * @return PrgState -
     * @throws DivisionByZeroException - Dividing by 0
     * @throws InvalidOperatorException - Entering an invalid operator
     * @throws VariableNotFound - Accessing an invalid key
     */
    PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, FileAlreadyExistsException, FileException, HeapWritingException, HeapVariableNotFoundException;

    /**
     * Get the String containing the details of the program state
     * @return a String
     */
    String toString();

}
