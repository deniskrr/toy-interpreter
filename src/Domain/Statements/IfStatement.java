package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.ADT.IStack;
import Domain.Expression.Expression;
import Domain.ProgramState;

public class IfStatement implements IStatement {

    private Expression expression;
    private IStatement thenS, elseS;

    public IfStatement(Expression expression, IStatement thenS, IStatement elseS) {
        this.expression = expression;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, Integer> symTable = state.getSymbolTable();
        if (expression.evaluate(symTable, state.getHeapTable()) != 0)
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
