package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapVariableNotFoundException;
import Exceptions.InvalidOperatorException;
import Exceptions.VariableNotFoundException;


public class ArithExp extends Exp {

    private Exp exp1,exp2;
    private char operator; // it may be: + or - or * or /

    public ArithExp(char operator, Exp e1, Exp e2){
        this.operator = operator;
        exp1 = e1;
        exp2 = e2;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable) throws DivisionByZeroException, InvalidOperatorException, VariableNotFoundException, HeapVariableNotFoundException {
        try {
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
        } catch (DivisionByZeroException | InvalidOperatorException | VariableNotFoundException | HeapVariableNotFoundException e) {
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
