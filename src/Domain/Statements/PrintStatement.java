package Domain.Statements;


import Domain.ADT.IDictionary;
import Domain.ADT.IList;
import Domain.Expression.Expression;
import Domain.ProgramState;

public class PrintStatement implements IStatement {

    private Expression expression;

    public PrintStatement(Expression e) {
        expression = e;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IList<Integer> queue = state.getOut();
        IDictionary<String, Integer> symTable = state.getSymbolTable();
        queue.add(expression.evaluate(symTable, state.getHeapTable()));
        return null;
    }

    @Override
    public String toString() {
        return "print (" + expression.toString() + ")";
    }
}
