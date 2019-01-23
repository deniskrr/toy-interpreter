package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.ADT.IStack;
import Domain.Expression.Exp;
import Domain.PrgState;

public class IfStmt implements IStmt {

    private Exp expression;
    private IStmt thenS, elseS;

    public IfStmt(Exp expression, IStmt thenS, IStmt elseS) {
        this.expression = expression;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public PrgState execute(PrgState state) {
        IStack<IStmt> stack = state.getExeStack();
        IDictionary<String, Integer> symTable = state.getSymTable();
        if (expression.evaluate(symTable, state.getHeap()) != 0)
            stack.push(thenS);
        else
            stack.push(elseS);
        return null;
    }

    @Override
    public String toString() {
        return "IF (" + expression.toString() + ") THEN (" + thenS.toString() + ") ELSE (" + elseS.toString() + ") ";
    }
}
