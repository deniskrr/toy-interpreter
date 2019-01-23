package Domain.Statements;

import Domain.ADT.IStack;
import Domain.PrgState;

public class CompStmt implements IStmt{

    private IStmt first, second;

    public CompStmt(IStmt f, IStmt s){
        first = f;
        second = s;
    }

    @Override
    public String toString(){
        return first.toString() + ";" + second.toString();
    }

    @Override
    public PrgState execute(PrgState state){

        IStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }
}
