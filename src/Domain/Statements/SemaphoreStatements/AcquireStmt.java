package Domain.Statements.SemaphoreStatements;

import Domain.PrgState;
import Domain.SemaphoreEntry;
import Domain.Statements.IStmt;
import Exceptions.*;

public class AcquireStmt implements IStmt {

    private String var;

    public AcquireStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFoundException, FileAlreadyExistsException, FileReadException, HeapWritingException, HeapVariableNotFoundException {

        if (state.getSymTable().checkExistence(var)) {
            int foundIndex = state.getSymTable().getValueForKey(var);
            if (state.getSemaphoreTable().checkExistence(foundIndex)) {
                SemaphoreEntry entry = state.getSemaphoreTable().getValueForKey(foundIndex);
                if (entry.getCounter() > entry.getList().size()) {
                    if (!entry.getList().contains(state.getId())) {
                        entry.getList().add(state.getId());
                    }
                }
            } else {
                throw new VariableNotFoundException();
            }
        } else {
            throw new VariableNotFoundException();
        }

        return null;
    }
}
