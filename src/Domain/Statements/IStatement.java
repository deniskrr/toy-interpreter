package Domain.Statements;

import Domain.ProgramState;

public interface IStatement {

    /**
     * Executes the statement
     *
     * @param state - the current state of the program
     * @return the child process - for {@link ForkStatement}
     * <code>null</code> - otherwise
     */
    ProgramState execute(ProgramState state);

    /**
     * Gets a text representation of the ProgramState
     *
     * @return a text representation
     */
    String toString();

}
