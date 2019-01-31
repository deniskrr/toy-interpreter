package Domain.Statements.HeapStatements;

import Domain.ADT.IDictionary;
import Domain.Expression.Expression;
import Domain.ProgramState;
import Domain.Statements.IStatement;
import Exceptions.HeapWritingException;

public class HeapWritingStatement implements IStatement {

    private String var;
    private Expression expression;

    public HeapWritingStatement(String var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Integer> symTable = state.getSymbolTable();
        IDictionary<Integer, Integer> heap = state.getHeapTable();

        int keyHeap = symTable.getValueForKey(var);
        int valueHeap = expression.evaluate(symTable, heap);

        if (heap.checkExistence(keyHeap))
            heap.update(keyHeap, valueHeap);
        else
            throw new HeapWritingException();

        return null;
    }

    @Override
    public String toString() {
        return "wH (" + var + ", " + expression.toString() + ")";
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
