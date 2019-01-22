package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.*;

public class HeapReadingExp extends Exp {

    private String var;

    public HeapReadingExp(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> table, IDictionary<Integer, Integer> heap) throws VariableNotFound, HeapVariableNotFoundException {
        try {
            int value = table.getValueForKey(var);
            return heap.getValueForKey(value);

        } catch (VariableNotFound e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "rH (" + var + ") ";
    }

}
