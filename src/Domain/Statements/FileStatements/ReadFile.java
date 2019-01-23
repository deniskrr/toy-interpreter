package Domain.Statements.FileStatements;

import Domain.Expression.Exp;
import Domain.PrgState;
import Domain.Statements.IStmt;
import Exceptions.FileReadException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {

    private Exp expFileId;
    private String varName;

    public ReadFile(Exp expFileId, String varName) {
        this.expFileId = expFileId;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) {
        try {
            int eval = expFileId.evaluate(state.getSymTable(), state.getHeap());
            BufferedReader reader = state.getFileTable().getValueForKey(eval).getReader();
            String line = reader.readLine();

            int value;

            if (line == null)
                value = 0;
            else
                value = Integer.parseInt(line);

            if (state.getSymTable().checkExistence(varName))
                state.getSymTable().update(varName, value);
            else
                state.getSymTable().add(varName, value);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "readFile (" + varName + "," + expFileId.toString() + ") ";
    }

}
