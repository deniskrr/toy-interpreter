package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.*;


public class ArithExp extends Exp {

    private Exp exp1,exp2;
    private char operator; // it may be: + or - or * or /

    public ArithExp(char operator, Exp e1, Exp e2){
        this.operator = operator;
        exp1 = e1;
        exp2 = e2;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> table,  IDictionary<Integer, Integer> heap) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapVariableNotFoundException {
        try {
            if (operator == '+')
                return exp1.evaluate(table, heap) + exp2.evaluate(table, heap);
            if (operator == '-')
                return exp1.evaluate(table, heap) - exp2.evaluate(table, heap);
            if (operator == '*')
                return exp1.evaluate(table, heap) * exp2.evaluate(table, heap);
            if (operator == '/' && exp2.evaluate(table, heap) != 0)
                return exp1.evaluate(table, heap) / exp2.evaluate(table, heap);
            else if (exp2.evaluate(table, heap) == 0)
                throw new DivisionByZeroException();
            throw new InvalidOperatorException();
        }
        catch (DivisionByZeroException | InvalidOperatorException | VariableNotFound | HeapVariableNotFoundException e){
            throw e;
        }
    }

    @Override
    public String toString(){
        String str = "";
        str += exp1.toString();
        str += operator;
        str += exp2.toString();
        return str;
    }
}
