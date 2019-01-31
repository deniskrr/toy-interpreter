package Domain.Statements.SemaphoreStatements;

import Domain.ADT.IDictionary;
import Domain.ADT.SemaphoreTable;
import Domain.Expression.Expression;
import Domain.ProgramState;
import Domain.SemaphoreEntry;
import Domain.Statements.IStatement;

public class NewSemaphoreStatement implements IStatement {

    private String var;
    private Expression expression;

    public NewSemaphoreStatement(String var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Integer> symTable = state.getSymbolTable();
        IDictionary<Integer, SemaphoreEntry> semaphoreTable = state.getSemaphoreTable();
        int value = expression.evaluate(symTable, state.getHeapTable());
        int location = SemaphoreTable.getNewFreeLocation();
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
        return "newSemaphore(" + var + ", " + expression + ")";
    }
}