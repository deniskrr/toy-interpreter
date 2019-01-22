package Domain.Statements.FileStatements;

import Domain.Expression.Exp;
import Domain.PrgState;

import Domain.Statements.IStmt;
import Exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFile implements IStmt {

    private Exp expFileId;

    public CloseFile(Exp expFileId) {
        this.expFileId = expFileId;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, FileException, HeapVariableNotFoundException {
        try{
            int value = expFileId.evaluate(state.getSymTable(), state.getHeap());

            BufferedReader reader = state.getFileTable().getValueForKey(value).getReader();
            reader.close();

            state.getFileTable().delete(value);
        }
        catch (IOException e){
            throw new FileException();
        }
        catch (DivisionByZeroException | InvalidOperatorException | VariableNotFound e){
            throw e;
        }
        return null;
    }

    @Override
    public String toString(){
        return "closeFile ( " + expFileId.toString() + " )";
    }

}
