package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.InvalidOperatorException;


public class ArithmeticExpression extends Expression {

    private Expression expression1, expression2;
    private char operator; // it may be: + or - or * or /

    public ArithmeticExpression(char operator, Expression e1, Expression e2) {
        this.operator = operator;
        expression1 = e1;
        expression2 = e2;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) {
        if (operator == '+')
            return expression1.evaluate(symTable, heapTable) + expression2.evaluate(symTable, heapTable);
        if (operator == '-')
            return expression1.evaluate(symTable, heapTable) - expression2.evaluate(symTable, heapTable);
        if (operator == '*')
            return expression1.evaluate(symTable, heapTable) * expression2.evaluate(symTable, heapTable);
        if (operator == '/' && expression2.evaluate(symTable, heapTable) != 0)
            return expression1.evaluate(symTable, heapTable) / expression2.evaluate(symTable, heapTable);
        else if (expression2.evaluate(symTable, heapTable) == 0)
            throw new DivisionByZeroException();
        throw new InvalidOperatorException();
    }

    @Override
    public String toString() {
        String str = "";
        str += expression1.toString();
        str += operator;
        str += expression2.toString();
        return str;
    }
}
