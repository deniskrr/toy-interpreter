package Domain.Expression;

import Domain.ADT.IDictionary;
import Domain.Statements.IStatement;


/**
 * An Expression that is used in {@link IStatement}.
 */
public abstract class Expression {

    /**
     * Evaluates the Expression
     * @param symTable the SymbolTable
     * @param heapTable the HeapTable
     * @return the result of the evaluation
     */
    public abstract int evaluate(IDictionary<String, Integer> symTable, IDictionary<Integer, Integer> heapTable);


    /**
     * Gets a text representation of the Expression
     * @return a text representation
     */
    public abstract String toString();
}
