package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapVariableNotFoundException;
import Exceptions.InvalidOperatorException;
import Exceptions.VariableNotFoundException;

public class BooleanExp extends Exp {

    private Exp exp1, exp2;
    private String option;

    public BooleanExp(Exp exp1, Exp exp2, String option) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.option = option;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) throws DivisionByZeroException, InvalidOperatorException, VariableNotFoundException, HeapVariableNotFoundException {

        if (option.equals("<")) {
            if (exp1.evaluate(symTable, heapTable) < exp2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals("<=")) {
            if (exp1.evaluate(symTable, heapTable) <= exp2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals("==")) {
            if (exp1.evaluate(symTable, heapTable) == exp2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals("!=")) {
            if (exp1.evaluate(symTable, heapTable) != exp2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals(">")) {
            if (exp1.evaluate(symTable, heapTable) > exp2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals(">=")) {
            if (exp1.evaluate(symTable, heapTable) >= exp2.evaluate(symTable, heapTable))
                return 1;
        }
        return 0;

    }

    @Override
    public String toString() {
        return exp1.toString() + " " + option + " " + exp2.toString();
    }
}
