package Domain.Statements.HeapStatements;

import Domain.ADT.IDictionary;
import Domain.Expression.Exp;
import Domain.PrgState;
import Domain.Statements.IStmt;
import Exceptions.*;

public class HeapWrittingStmt implements IStmt {

    private String var;
    private Exp exp;

    public HeapWrittingStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapWritingException, HeapVariableNotFoundException {

        try {
            IDictionary<String, Integer> symTable = state.getSymTable();
            IDictionary<Integer, Integer> heap = state.getHeap();

            int keyHeap = symTable.getValueForKey(var);
            int valueHeap = exp.evaluate(symTable, heap);

            if(heap.checkExistence(keyHeap))
                heap.update(keyHeap, valueHeap);
            else
                throw new HeapWritingException();
        }
        catch (VariableNotFound e){
            throw e;
        }
        return null;
    }

    @Override
    public String toString() {
        return "wH (" + var + ", " + exp.toString() + ")";
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }
}
