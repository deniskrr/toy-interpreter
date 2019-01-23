package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapVariableNotFoundException;
import Exceptions.InvalidOperatorException;
import Exceptions.VariableNotFoundException;

public abstract class Exp {

    public abstract int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) throws DivisionByZeroException, InvalidOperatorException, VariableNotFoundException, HeapVariableNotFoundException;

    public abstract String toString();
}
