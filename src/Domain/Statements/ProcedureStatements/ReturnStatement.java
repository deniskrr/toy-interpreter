package Domain.Statements.ProcedureStatements;

import Domain.ProgramState;
import Domain.Statements.IStatement;

public class ReturnStatement implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) {
        state.getSymTables().pop();
        return null;
    }

    @Override
    public String toString() {
        return "return ";
    }
}
