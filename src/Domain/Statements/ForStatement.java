package Domain.Statements;

import Domain.ADT.IStack;
import Domain.Expression.BooleanExpression;
import Domain.Expression.Expression;
import Domain.Expression.VariableExpression;
import Domain.ProgramState;

public class ForStatement implements IStatement {
    private String var;
    private Expression start;
    private Expression cond;
    private Expression going;
    private IStatement stmt;

    public ForStatement(String var, Expression start, Expression cond, Expression going, IStatement stmt) {
        this.var = var;
        this.start = start;
        this.cond = cond;
        this.going = going;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        IStatement forStmt = new CompoundStatement(new AssignStatement(var, start),
                new WhileStatement(new BooleanExpression(new VariableExpression(var), cond, "<"), new CompoundStatement(stmt, new AssignStatement(var, going))));
        stack.push(forStmt);
        return null;
    }

    @Override
    public String toString() {
        return " for(" + var + '=' + start + "; " + var + " < " + cond + "; " + var + " = " + going + ") " + stmt;
    }
}
