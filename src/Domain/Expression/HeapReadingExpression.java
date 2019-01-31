package Domain.Expression;

import Domain.ADT.IDictionary;

public class HeapReadingExpression extends Expression {

    private String var;

    public HeapReadingExpression(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) {
        int value = symTable.getValueForKey(var);
        return heapTable.getValueForKey(value);
    }

    @Override
    public String toString() {
        return "rH (" + var + ") ";
    }

}
