package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.VariableNotFoundException;

public class VarExp extends Exp {

    private String var;


    public VarExp(String i) {
        var = i;
    }


    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) throws VariableNotFoundException {
        return symTable.getValueForKey(var);
    }

    @Override
    public String toString() {
        return var;
    }
}
