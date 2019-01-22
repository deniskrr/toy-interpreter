package Domain.Statements.HeapStatements;


import Domain.ADT.IDictionary;
import Domain.Expression.Exp;
import Domain.PrgState;
import Domain.Statements.IStmt;

public class HeapAllocationStmt implements IStmt {

    private String varName;
    private Exp exp;
    private static int count = 1;

    public HeapAllocationStmt(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }

    public static void setCount(int count) {
        HeapAllocationStmt.count = count;
    }

    @Override
    public PrgState execute(PrgState state){

        try {
            IDictionary<String, Integer> symTable = state.getSymTable();
            IDictionary<Integer, Integer> heap = state.getHeap();
            heap.add(count, exp.evaluate(symTable, heap));
            if (symTable.checkExistence(varName))
                symTable.update(varName, count);
            else
                symTable.add(varName, count);
            count++;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String toString(){
        return "new (" + this.varName + ", " + this.exp + ")" ;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }
}
