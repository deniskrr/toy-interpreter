package Repository;

import Domain.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextRepository implements IRepository{

    private List<ProgramState> programStates;
    private String logFilePath;

    public TextRepository(String logFilePath){
        programStates = new ArrayList<ProgramState>();
        this.logFilePath = logFilePath;
    }

    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    @Override
    public void addProgram(ProgramState newPrg){
        programStates.add(newPrg);
    }

    @Override
    public void logPrgStateExec(ProgramState prg) {

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
    public void setProgramStates(List<ProgramState> l){
        programStates = l;
    }
}
