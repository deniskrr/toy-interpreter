package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.*;

public abstract class Exp {

    public abstract int evaluate(IDictionary<String, Integer> table, IDictionary<Integer, Integer> heap) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapVariableNotFoundException;

    public abstract String toString();
}
