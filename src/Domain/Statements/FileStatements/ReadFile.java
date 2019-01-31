package Domain.Statements.FileStatements;

import Domain.Expression.Expression;
import Domain.ProgramState;
import Domain.Statements.IStatement;
import Exceptions.FileReadException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {

    private Expression expressionFileId;
    private String varName;

    public ReadFile(Expression expressionFileId, String varName) {
        this.expressionFileId = expressionFileId;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        try {
            int eval = expressionFileId.evaluate(state.getSymbolTable(), state.getHeapTable());
            BufferedReader reader = state.getFileTable().getValueForKey(eval).getReader();
            String line = reader.readLine();

            int value;

            if (line == null)
                value = 0;
            else
                value = Integer.parseInt(line);

            if (state.getSymbolTable().checkExistence(varName))
                state.getSymbolTable().update(varName, value);
            else
                state.getSymbolTable().add(varName, value);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "readFile (" + varName + "," + expressionFileId.toString() + ") ";
    }

}
