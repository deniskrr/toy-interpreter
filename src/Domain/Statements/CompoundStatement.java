package Domain.Statements;

import Domain.ADT.IStack;
import Domain.ProgramState;

public class CompoundStatement implements IStatement {

    private IStatement first, second;

    public CompoundStatement(IStatement f, IStatement s){
        first = f;
        second = s;
    }

    @Override
    public String toString(){
        return first.toString() + ";" + second.toString();
    }

    @Override
    public ProgramState execute(ProgramState state){

        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }
}
