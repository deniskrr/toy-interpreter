package Domain.Statements;

import Domain.ADT.IDictionary;
import Domain.ADT.IStack;
import Domain.Expression.Exp;
import Domain.PrgState;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapVariableNotFoundException;
import Exceptions.InvalidOperatorException;
import Exceptions.VariableNotFoundException;

public class IfStmt implements IStmt{

    private Exp expression;
    private IStmt thenS, elseS;

    public IfStmt(Exp expression, IStmt thenS, IStmt elseS) {
        this.expression = expression;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFoundException, HeapVariableNotFoundException {
        try {
            IStack<IStmt> stack = state.getExeStack();
            IDictionary<String, Integer> symTable = state.getSymTable();
            if (expression.evaluate(symTable, state.getHeap()) != 0)
                stack.push(thenS);
            else
                stack.push(elseS);
            return null;
        } catch (DivisionByZeroException | InvalidOperatorException | VariableNotFoundException e) {
            throw e;
        }
    }

    @Override
    public String toString(){
        return "IF (" + expression.toString() + ") THEN (" + thenS.toString() + ") ELSE (" + elseS.toString() + ") ";}
}
