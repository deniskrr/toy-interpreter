package Repository;

import Domain.ProgramState;

import java.util.List;

/**
 * A class used for storing a list of ProgramStates.
 */
public interface IRepository {

    /**
     * Adds a new program state to the Execution Stack
     * @param newProgramState: the program state to be added
     */
    void addProgram(ProgramState newProgramState);


    /**
     * Logs the ProgramState
     * @param prg: the program state
     */
    void logPrgStateExec(ProgramState prg);


    /**
     * Gets the list of program states
     * @return a list with all program states
     */
    List<ProgramState> getProgramStates();


    /**
     * Sets the list of program states to a given list
     * @param l: the given list
     */
    void setProgramStates(List<ProgramState> l);
}
