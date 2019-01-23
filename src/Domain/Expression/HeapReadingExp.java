package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.HeapVariableNotFoundException;
import Exceptions.VariableNotFoundException;

public class HeapReadingExp extends Exp {

    private String var;

    public HeapReadingExp(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) throws VariableNotFoundException, HeapVariableNotFoundException {
        try {
            int value = symTable.getValueForKey(var);
            return heapTable.getValueForKey(value);

        } catch (VariableNotFoundException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "rH (" + var + ") ";
    }

}
