package Domain.Expression;

import Domain.ADT.IDictionary;

public class ConstantExpression extends Expression {

    private int value;

    public ConstantExpression(int n){
        value = n;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
