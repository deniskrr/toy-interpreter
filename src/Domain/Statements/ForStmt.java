package Domain.Statements;

import Domain.ADT.IStack;
import Domain.Expression.BooleanExp;
import Domain.Expression.Exp;
import Domain.Expression.VarExp;
import Domain.PrgState;
import Exceptions.*;

public class ForStmt implements IStmt {
    private String var;
    private Exp start;
    private Exp cond;
    private Exp going;
    private IStmt stmt;

    public ForStmt(String var, Exp start, Exp cond, Exp going, IStmt stmt) {
        this.var = var;
        this.start = start;
        this.cond = cond;
        this.going = going;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, FileAlreadyExistsException, FileException, HeapWritingException, HeapVariableNotFoundException {
        IStack<IStmt> stack = state.getExeStack();
        IStmt forStmt = new CompStmt(new AssignStmt(var, start),
                new WhileStmt(new BooleanExp(new VarExp(var), cond, "<"), new CompStmt(stmt, new AssignStmt(var, going))));
        stack.push(forStmt);
        return null;
    }

    @Override
    public String toString() {
        return " for(" + var + '=' + start + "; " + var + " < " + cond + "; " + var + " = " + going + ") " + stmt;
    }
}
