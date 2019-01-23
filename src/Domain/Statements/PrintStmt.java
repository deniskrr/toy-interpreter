package Domain.Statements;


import Domain.ADT.IDictionary;
import Domain.ADT.IList;
import Domain.Expression.Exp;
import Domain.PrgState;

public class PrintStmt implements IStmt {

    private Exp exp;

    public PrintStmt(Exp e) {
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) {
        IList<Integer> queue = state.getOut();
        IDictionary<String, Integer> symTable = state.getSymTable();
        queue.add(exp.evaluate(symTable, state.getHeap()));
        return null;
    }

    @Override
    public String toString() {
        return "print (" + exp.toString() + ")";
    }
}
