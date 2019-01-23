package Domain.Expression;

import Domain.ADT.IDictionary;

public class ConstExp extends Exp {

    private int value;

    public ConstExp(int n){
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
