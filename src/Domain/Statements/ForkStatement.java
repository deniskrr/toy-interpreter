package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.ADT.IStack;
import Domain.ADT.MyStack;
import Domain.ProgramState;

public class ForkStatement implements IStatement {

    private IStatement stmt;

    public ForkStatement(IStatement stmt) {
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> newStack = new MyStack<>();
        IStack<IDictionary<String, Integer>> newSymTable = new MyStack<>();

        for (IDictionary<String, Integer> symTable : state.getSymTables().getStack()) {
            newSymTable.push(symTable.clone());
        }

        return new ProgramState(newStack, newSymTable, state.getOut(), stmt, state.getFileTable(), state.getHeapTable(), state.getSemaphoreTable(), state.getProcedureTable());

    }

    @Override
    public String toString() {
        return "fork " + stmt.toString();
    }
}
