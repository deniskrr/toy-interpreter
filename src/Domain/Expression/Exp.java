package Domain.Expression;

import Domain.ADT.IDictionary;

public abstract class Exp {

    public abstract int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable);

    public abstract String toString();
}
