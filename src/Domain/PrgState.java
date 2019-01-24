package Domain;

import Domain.ADT.*;
import Domain.Statements.IStmt;
import Domain.Statements.ProcedureEntry;
import Exceptions.EmptyStackException;


public class PrgState {

    private static int newID = 1;

    private IStack<IStmt> exeStack;
    private IStack<IDictionary<String, Integer>> symTable;
    private IList<Integer> out;
    private IStmt originalProgram;

    private ProcTable<String, ProcedureEntry> procTable;
    private IDictionary<Integer, SemaphoreEntry> semaphoreTable;
    private IDictionary<Integer, FileData> fileTable;
    private IDictionary<Integer, Integer> heap;
    private int id;


    // Used by fork statements
    public PrgState(IStack<IStmt> exeStack, IStack<IDictionary<String, Integer>> symTable, IList<Integer> out, IStmt originalProgram, IDictionary<Integer, FileData> fileTable, IDictionary<Integer, Integer> heap, IDictionary<Integer, SemaphoreEntry> semaphoreTable, ProcTable<String, ProcedureEntry> procTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = newID++;
        this.semaphoreTable = semaphoreTable;
        this.procTable = procTable;
        this.exeStack.push(originalProgram);
    }


    //Used by main program
    public PrgState(IStmt originalProgram) {
        this.exeStack = new MyStack<>();
        this.symTable = new MyStack<>();
        symTable.push(new MyDictionary<>());
        this.out = new MyList<>();
        this.fileTable = new FileTable<>();
        this.heap = new HeapTable<>();
        this.originalProgram = originalProgram;
        this.semaphoreTable = new Semaphore<>();
        this.id = newID++;
        this.procTable = new ProcTable<>();
        this.exeStack.push(originalProgram);
    }

    @Override
    public String toString() {
        String str = "";
        str += "Id:\n\t" + id;
        str += "\nExeStack:\n" + exeStack.toString();
        str += "Sym Table:\n" + symTable.toString();
        str += "File Table:\n" + fileTable.toString();
        str += "HeapTable Table: \n" + heap.toString();
        str += "Semaphore Table: \n" + semaphoreTable.toString();
        str += "Procedure Table: \n" + procTable.toString();
        str += "Print output:\n" + out.toString();

        return str;
    }

    public boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() {
        if (exeStack.isEmpty())
            throw new EmptyStackException();
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public IDictionary<Integer, Integer> getHeap() {
        return heap;
    }

    public IDictionary<String, Integer> getSymTable() {
        return symTable.peek();
    }

    public IDictionary<Integer, FileData> getFileTable() {
        return fileTable;
    }

    public IDictionary<Integer, SemaphoreEntry> getSemaphoreTable() {
        return semaphoreTable;
    }

    public void setHeap(IDictionary<Integer, Integer> heap) {
        this.heap = heap;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IList<Integer> getOut() {
        return out;
    }

    public void setSymTable(IStack<IDictionary<String, Integer>> symTable) {
        this.symTable = symTable;
    }

    public ProcTable<String, ProcedureEntry> getProcTable() {
        return procTable;
    }

    public IStack<IDictionary<String, Integer>> getSymTables() {
        return symTable;
    }

    public void setFileTable(IDictionary<Integer, FileData> fileTable) {
        this.fileTable = fileTable;
    }

    public void setExeStack(IStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void addProcedure(String name, ProcedureEntry entry) {
        this.procTable.add(name, entry);
    }

    public void setOut(IList<Integer> out) {
        this.out = out;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public int getId() {
        return id;
    }
}
