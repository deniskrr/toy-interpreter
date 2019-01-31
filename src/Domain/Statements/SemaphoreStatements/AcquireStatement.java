package Domain.Statements.SemaphoreStatements;

import Domain.ProgramState;
import Domain.SemaphoreEntry;
import Domain.Statements.IStatement;
import Exceptions.VariableNotFoundException;

public class AcquireStatement implements IStatement {

    private String var;

    public AcquireStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        if (state.getSymbolTable().checkExistence(var)) {
            int foundIndex = state.getSymbolTable().getValueForKey(var);
            if (state.getSemaphoreTable().checkExistence(foundIndex)) {
                SemaphoreEntry entry = state.getSemaphoreTable().getValueForKey(foundIndex);
                if (entry.getCounter() > entry.getList().size()) {
                    if (!entry.getList().contains(state.getId())) {
                        entry.getList().add(state.getId());
                    }
                } else {
                    state.getExecutionStack().push(this);
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
        return "acquire(" + var +") ";
    }
}
