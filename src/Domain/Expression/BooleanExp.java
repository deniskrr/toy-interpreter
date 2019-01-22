package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.*;

public class BooleanExp extends Exp {

    private Exp exp1, exp2;
    private String option;

    public BooleanExp(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> table, IDictionary<Integer, Integer> heap) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapVariableNotFoundException {

        if (option.equals("<")) {
            if (exp1.evaluate(table, heap) < exp2.evaluate(table, heap))
                return 1;
        }
        if (option.equals("<=")) {
            if (exp1.evaluate(table, heap) <= exp2.evaluate(table, heap))
                return 1;
        }
        if (option.equals("==")) {
            if (exp1.evaluate(table, heap) == exp2.evaluate(table, heap))
                return 1;
        }
        if (option.equals("!=")) {
            if (exp1.evaluate(table, heap) != exp2.evaluate(table, heap))
                return 1;
        }
        if (option.equals(">")) {
            if (exp1.evaluate(table, heap) > exp2.evaluate(table, heap))
                return 1;
        }
        if (option.equals(">=")) {
            if (exp1.evaluate(table, heap) >= exp2.evaluate(table, heap))
                return 1;
        }
        return 0;

    }

    @Override
    public String toString() {
        return exp1.toString() + " " + option + " " + exp2.toString();
    }
}
