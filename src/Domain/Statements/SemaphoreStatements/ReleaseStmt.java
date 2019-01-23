package Domain.Statements.SemaphoreStatements;

import Domain.PrgState;
import Domain.SemaphoreEntry;
import Domain.Statements.IStmt;
import Exceptions.VariableNotFoundException;

public class ReleaseStmt implements IStmt {

    private String var;

    public ReleaseStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) {
        if (state.getSymTable().checkExistence(var)) {
            int foundIndex = state.getSymTable().getValueForKey(var);
            if (state.getSemaphoreTable().checkExistence(foundIndex)) {
                SemaphoreEntry entry = state.getSemaphoreTable().getValueForKey(foundIndex);
                if (entry.getList().contains(state.getId())) {
                    entry.getList().remove((Integer) state.getId());
                }
            } else {
                throw new VariableNotFoundException();
            }
        } else {
            throw new VariableNotFoundException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "release(" + var + ")";
    }
}
