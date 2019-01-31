package Domain.Statements.HeapStatements;


import Domain.ADT.IDictionary;
import Domain.Expression.Expression;
import Domain.ProgramState;
import Domain.Statements.IStatement;

public class HeapAllocationStatement implements IStatement {

    private String varName;
    private Expression expression;
    private static int count = 1;

    public HeapAllocationStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    public static void setCount(int count) {
        HeapAllocationStatement.count = count;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        IDictionary<String, Integer> symTable = state.getSymbolTable();
        IDictionary<Integer, Integer> heap = state.getHeapTable();
        heap.add(count, expression.evaluate(symTable, heap));
        if (symTable.checkExistence(varName))
            symTable.update(varName, count);
        else
            symTable.add(varName, count);
        count++;

        return null;
    }

    @Override
    public String toString() {
        return "new (" + this.varName + ", " + this.expression + ")";
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
