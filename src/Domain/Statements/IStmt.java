package Domain.Statements;

import Domain.PrgState;

public interface IStmt {

    /**
     * Execute the statement
     * @param state - the current state of the program
     * @return PrgState -
     */
    PrgState execute(PrgState state);

    /**
     * Get the String containing the details of the program state
     * @return a String
     */
    String toString();

}
