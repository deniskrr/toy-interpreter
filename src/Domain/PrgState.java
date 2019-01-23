package Domain;

import Domain.ADT.*;
import Domain.Statements.IStmt;
import Exceptions.EmptyStackException;


public class PrgState {

    private static int newID = 1;

    private IStack<IStmt> exeStack;
    private IDictionary<String, Integer> symTable;
    private IList<Integer> out;
    private IStmt originalProgram;

    private IDictionary<Integer, SemaphoreEntry> semaphoreTable;
    private IDictionary<Integer, FileData> fileTable;
    private IDictionary<Integer, Integer> heap;
    private int id;


    // Used by fork statements
    public PrgState(IStack<IStmt> exeStack, IDictionary<String, Integer> symTable, IList<Integer> out, IStmt originalProgram, IDictionary<Integer, FileData> fileTable, IDictionary<Integer, Integer> heap, IDictionary<Integer, SemaphoreEntry> semaphoreTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = newID++;
        this.semaphoreTable = semaphoreTable;
        this.exeStack.push(originalProgram);
    }


    //Used by main program
    public PrgState(IStmt originalProgram) {
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
        this.out = new MyList<>();
        this.fileTable = new FileTable<>();
        this.heap = new HeapTable<>();
        this.originalProgram = originalProgram;
        this.semaphoreTable = new Semaphore<>();
        this.id = newID++;
        this.exeStack.push(originalProgram);
    }

    public PrgState(IStmt originalProgram, int id) {
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
        this.out = new MyList<Integer>();
        this.fileTable = new FileTable<>();
        this.heap = new HeapTable<>();
        this.originalProgram = originalProgram;
        this.id = id;
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
        return symTable;
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

    public IStmt getOriginalProgram() {
        return originalProgram;
    }


    public void setFileTable(IDictionary<Integer, FileData> fileTable) {
        this.fileTable = fileTable;
    }

    public void setExeStack(IStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDictionary<String, Integer> symTable) {
        this.symTable = symTable;
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
