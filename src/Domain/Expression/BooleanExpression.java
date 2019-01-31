package Domain.Expression;

import Domain.ADT.IDictionary;

public class BooleanExpression extends Expression {

    private Expression expression1, expression2;
    private String option;

    public BooleanExpression(Expression expression1, Expression expression2, String option) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.option = option;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) {

        if (option.equals("<")) {
            if (expression1.evaluate(symTable, heapTable) < expression2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals("<=")) {
            if (expression1.evaluate(symTable, heapTable) <= expression2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals("==")) {
            if (expression1.evaluate(symTable, heapTable) == expression2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals("!=")) {
            if (expression1.evaluate(symTable, heapTable) != expression2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals(">")) {
            if (expression1.evaluate(symTable, heapTable) > expression2.evaluate(symTable, heapTable))
                return 1;
        }
        if (option.equals(">=")) {
            if (expression1.evaluate(symTable, heapTable) >= expression2.evaluate(symTable, heapTable))
                return 1;
        }
        return 0;

    }

    @Override
    public String toString() {
        return expression1.toString() + " " + option + " " + expression2.toString();
    }
}
