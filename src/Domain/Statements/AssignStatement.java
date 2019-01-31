package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.Expression.Expression;
import Domain.ProgramState;

public class AssignStatement implements IStatement {

    private String var;
    private Expression expression;


    public AssignStatement(String var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Integer> symTable = state.getSymbolTable();
        int value = expression.evaluate(symTable, state.getHeapTable());
        if (symTable.checkExistence(var))
            symTable.update(var, value);
        else
            symTable.add(var, value);
        return null;
    }

    @Override
    public String toString() {
        return var + " = " + expression.toString();
    }
}
