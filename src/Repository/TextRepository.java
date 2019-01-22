package Repository;

import Domain.ADT.MyList;
import Domain.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextRepository implements IRepository{

    private List<PrgState> programStates;
    private String logFilePath;

    public TextRepository(String logFilePath){
        programStates = new ArrayList<PrgState>();
        this.logFilePath = logFilePath;
    }

    public List<PrgState> getProgramStates() {
        return programStates;
    }

    @Override
    public void addProgram(PrgState newPrg){
        programStates.add(newPrg);
    }

    @Override
    public void logPrgStateExec(PrgState prg) {

        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(prg);
            logFile.println("\n\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (logFile != null)
                logFile.close();
        }
    }

    @Override
    public void setProgramStates(List<PrgState> l){
        programStates = l;
    }
}
