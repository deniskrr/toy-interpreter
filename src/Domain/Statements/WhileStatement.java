package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.ADT.IStack;
import Domain.Expression.Expression;
import Domain.ProgramState;

public class WhileStatement implements IStatement {

    private Expression expression;
    private IStatement stmt;

    public WhileStatement(Expression expression, IStatement stmt) {
        this.expression = expression;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, Integer> table = state.getSymbolTable();
        IDictionary<Integer, Integer> heap = state.getHeapTable();

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
