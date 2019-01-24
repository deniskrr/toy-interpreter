package Domain.Statements.ProcedureStatements;

import Domain.ADT.IDictionary;
import Domain.ADT.MyDictionary;
import Domain.Expression.Exp;
import Domain.PrgState;
import Domain.Statements.IStmt;
import Domain.Statements.ProcedureEntry;
import Exceptions.VariableNotFoundException;

import java.util.List;

public class CallStmt implements IStmt {

    private String name;
    private List<Exp> actualParams;

    public CallStmt(String name, List<Exp> actualParams) {
        this.name = name;
        this.actualParams = actualParams;
    }

    @Override
    public PrgState execute(PrgState state) {
        if (state.getProcTable().checkExistence(name)) {
            ProcedureEntry entry = state.getProcTable().getValueForKey(name);
            IDictionary<String, Integer> newSymTable = new MyDictionary<>();
            for (int i = 0; i < entry.getParameters().size(); i++) {
                Integer actualParam = actualParams.get(i).evaluate(state.getSymTable(), state.getHeap());
                newSymTable.add(entry.getParameters().get(i), actualParam);
            }
            state.getSymTables().push(newSymTable);
            state.getExeStack().push(new ReturnStmt());
            state.getExeStack().push(entry.getBody());
        } else {
            throw new VariableNotFoundException();
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "call " + name + "(";
        for (Exp e : actualParams) {
            str += e;
            str += ", ";
        }
        str += ") ";
        return str;
    }
}
