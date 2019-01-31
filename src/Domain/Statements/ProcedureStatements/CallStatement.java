package Domain.Statements.ProcedureStatements;

import Domain.ADT.IDictionary;
import Domain.ADT.MyDictionary;
import Domain.Expression.Expression;
import Domain.ProgramState;
import Domain.Statements.IStatement;
import Domain.ProcedureEntry;
import Exceptions.VariableNotFoundException;

import java.util.List;

public class CallStatement implements IStatement {

    private String name;
    private List<Expression> actualParams;

    public CallStatement(String name, List<Expression> actualParams) {
        this.name = name;
        this.actualParams = actualParams;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        if (state.getProcedureTable().checkExistence(name)) {
            ProcedureEntry entry = state.getProcedureTable().getValueForKey(name);
            IDictionary<String, Integer> newSymTable = new MyDictionary<>();
            for (int i = 0; i < entry.getParameters().size(); i++) {
                Integer actualParam = actualParams.get(i).evaluate(state.getSymbolTable(), state.getHeapTable());
                newSymTable.add(entry.getParameters().get(i), actualParam);
            }
            state.getSymTables().push(newSymTable);
            state.getExecutionStack().push(new ReturnStatement());
            state.getExecutionStack().push(entry.getBody());
        } else {
            throw new VariableNotFoundException();
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "call " + name + "(";
        for (Expression e : actualParams) {
            str += e;
            str += ", ";
        }
        str += ") ";
        return str;
    }
}
