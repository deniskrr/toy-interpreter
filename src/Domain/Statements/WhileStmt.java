package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.ADT.IStack;
import Domain.Expression.Exp;
import Domain.PrgState;
import Exceptions.*;

public class WhileStmt implements IStmt {

    private Exp expression;
    private IStmt stmt;

    public WhileStmt(Exp expression, IStmt stmt) {
        this.expression = expression;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapVariableNotFoundException {
        IStack<IStmt> stack = state.getExeStack();
        IDictionary<String, Integer> table = state.getSymTable();
        IDictionary<Integer, Integer> heap = state.getHeap();

        if (expression.evaluate(table, heap) != 0) {
            stack.push(this);
            stack.push(stmt);
        }
        return null;

    }

    @Override
    public String toString(){
            return "while ( " + expression.toString() + ")" + stmt.toString();
    }

}
