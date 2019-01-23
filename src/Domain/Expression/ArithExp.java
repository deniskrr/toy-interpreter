package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.InvalidOperatorException;


public class ArithExp extends Exp {

    private Exp exp1, exp2;
    private char operator; // it may be: + or - or * or /

    public ArithExp(char operator, Exp e1, Exp e2) {
        this.operator = operator;
        exp1 = e1;
        exp2 = e2;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) {
        if (operator == '+')
            return exp1.evaluate(symTable, heapTable) + exp2.evaluate(symTable, heapTable);
        if (operator == '-')
            return exp1.evaluate(symTable, heapTable) - exp2.evaluate(symTable, heapTable);
        if (operator == '*')
            return exp1.evaluate(symTable, heapTable) * exp2.evaluate(symTable, heapTable);
        if (operator == '/' && exp2.evaluate(symTable, heapTable) != 0)
            return exp1.evaluate(symTable, heapTable) / exp2.evaluate(symTable, heapTable);
        else if (exp2.evaluate(symTable, heapTable) == 0)
            throw new DivisionByZeroException();
        throw new InvalidOperatorException();
    }

    @Override
    public String toString() {
        String str = "";
        str += exp1.toString();
        str += operator;
        str += exp2.toString();
        return str;
    }
}
