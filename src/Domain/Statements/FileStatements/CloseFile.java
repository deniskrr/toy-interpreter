package Domain.Statements.FileStatements;

import Domain.Expression.Expression;
import Domain.ProgramState;
import Domain.Statements.IStatement;
import Exceptions.FileReadException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFile implements IStatement {

    private Expression expressionFileId;

    public CloseFile(Expression expressionFileId) {
        this.expressionFileId = expressionFileId;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        try{
            int value = expressionFileId.evaluate(state.getSymbolTable(), state.getHeapTable());

            BufferedReader reader = state.getFileTable().getValueForKey(value).getReader();
            reader.close();

            state.getFileTable().delete(value);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return null;
    }

    @Override
    public String toString(){
        return "closeFile ( " + expressionFileId.toString() + " )";
    }

}
