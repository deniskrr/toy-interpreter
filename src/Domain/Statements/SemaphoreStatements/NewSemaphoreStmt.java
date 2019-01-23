package Domain.Statements.SemaphoreStatements;

import Domain.ADT.IDictionary;
import Domain.ADT.Semaphore;
import Domain.Expression.Exp;
import Domain.PrgState;
import Domain.SemaphoreEntry;
import Domain.Statements.IStmt;
import Exceptions.*;

public class NewSemaphoreStmt implements IStmt {

    private String var;
    private Exp exp;

    public NewSemaphoreStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFoundException, FileAlreadyExistsException, FileReadException, HeapWritingException, HeapVariableNotFoundException {
        IDictionary<String, Integer> symTable = state.getSymTable();
        IDictionary<Integer, SemaphoreEntry> semaphoreTable = state.getSemaphoreTable();
        int value = exp.evaluate(symTable, state.getHeap());
        int location = Semaphore.getNewFreeLocation();
        state.getSemaphoreTable().add(location, new SemaphoreEntry(value));
        if (symTable.checkExistence(var)) {
            symTable.update(var, location);
        } else {
            symTable.add(var, location);
        }
        return null;
    }

    @Override
    public String toString() {
        return "newSemaphore(" + var + ", " + exp + ")";
    }
}