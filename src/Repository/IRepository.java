package Repository;

import Domain.ADT.MyList;
import Domain.PrgState;

import java.util.List;

public interface IRepository {

    /**
     * Add a new program state to the Execution Stack
     * @param newPrgState: the program state to be added
     */
    void addProgram(PrgState newPrgState);


    /**
     * Log the ProgramState
     * @param prg: the program state
     */
    void logPrgStateExec(PrgState prg);


    /**
     * Get the list of program states
     * @return a list with all program states
     */
    List<PrgState> getProgramStates();


    /**
     * Set the list of program states to a given list
     * @param l: the given list
     */
    void setProgramStates(List<PrgState> l);
}
