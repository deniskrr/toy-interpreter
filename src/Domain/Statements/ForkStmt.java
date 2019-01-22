package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.ADT.IStack;
import Domain.ADT.MyStack;
import Domain.PrgState;

public class ForkStmt implements IStmt {

    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) {
        IStack<IStmt> newStack = new MyStack<IStmt>();
        IDictionary<String, Integer> symTable = state.getSymTable();
        IDictionary<String, Integer> newSymTable = symTable.clone();

        return new PrgState(newStack, newSymTable, state.getOut(), stmt, state.getFileTable(), state.getHeap());

    }

    @Override
    public String toString() {
        return "fork " + stmt.toString();
    }
}
