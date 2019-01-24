package Domain.Statements.ProcedureStatements;

import Domain.PrgState;
import Domain.Statements.IStmt;

public class ReturnStmt implements IStmt {

    @Override
    public PrgState execute(PrgState state) {
        state.getSymTables().pop();
        return null;
    }
}
