package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.Expression.Exp;
import Domain.PrgState;
import Exceptions.*;

public class AssignStmt implements IStmt{

    private String var;
    private Exp expression;


    public AssignStmt(String var, Exp expression){
        this.var = var;
        this.expression = expression;
    }

    public Exp getExpression() {
        return expression;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapVariableNotFoundException {
        try {
            IDictionary<String, Integer> symTable = state.getSymTable();
            int value = expression.evaluate(symTable, state.getHeap());
            if (symTable.checkExistence(var))
                symTable.update(var, value);
            else
                symTable.add(var, value);
            return null;
        }
        catch (DivisionByZeroException | InvalidOperatorException | VariableNotFound | HeapVariableNotFoundException e){
            throw e;
        }
    }

    @Override
    public String toString(){
        return var + " = " + expression.toString();
    }
}
