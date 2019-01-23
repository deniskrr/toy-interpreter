package Domain.Statements;


import Domain.ADT.IDictionary;
import Domain.ADT.IList;
import Domain.Expression.Exp;
import Domain.PrgState;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapVariableNotFoundException;
import Exceptions.InvalidOperatorException;
import Exceptions.VariableNotFoundException;

public class PrintStmt implements IStmt {

    private Exp exp;

    public PrintStmt(Exp e){
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, InvalidOperatorException, VariableNotFoundException, HeapVariableNotFoundException {
        try{
            IList<Integer> queue = state.getOut();
            IDictionary<String,Integer> symTable = state.getSymTable();
            queue.add(exp.evaluate(symTable, state.getHeap()));
        } catch (InvalidOperatorException | VariableNotFoundException | DivisionByZeroException e) {
            throw e;
        }
        return null;
    }

    @Override
    public String toString(){
        return "print (" +  exp.toString() + ")";
    }
}
