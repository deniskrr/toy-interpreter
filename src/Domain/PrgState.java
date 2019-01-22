package Domain;

import Domain.ADT.*;
import Domain.Statements.IStmt;
import Exceptions.*;


public class PrgState {

    public static int newID = 1;

    private IStack<IStmt> exeStack;
    private IDictionary<String,Integer> symTable;
    private IList<Integer> out;
    private IStmt originalProgram;

    private IDictionary<Integer, FileData> fileTable;
    private IDictionary<Integer, Integer> heap;
    private int id;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, Integer> symTable, IList<Integer> out, IStmt originalProgram, IDictionary<Integer, FileData> fileTable, IDictionary<Integer, Integer> heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = newID++;
        this.exeStack.push(originalProgram);
    }

    public PrgState(IStmt originalProgram) {
        this.exeStack = new MyStack<IStmt>();
        this.symTable = new MyDictionary<String, Integer>();
        this.out = new MyList<Integer>();
        this.fileTable = new FileTable<Integer, FileData>();
        this.heap = new Heap<Integer, Integer>();
        this.originalProgram = originalProgram;
        this.id = newID++;
        this.exeStack.push(originalProgram);
    }

    public PrgState(IStmt originalProgram, int id) {
        this.exeStack = new MyStack<IStmt>();
        this.symTable = new MyDictionary<String, Integer>();
        this.out = new MyList<Integer>();
        this.fileTable = new FileTable<Integer, FileData>();
        this.heap = new Heap<Integer, Integer>();
        this.originalProgram = originalProgram;
        this.id = id;
        this.exeStack.push(originalProgram);
    }

    @Override
    public String toString() {
        String str = "";
        str += "Id:\n\t" + Integer.toString(id);
        str += "\nExeStack:\n" + exeStack.toString();
        str += "Sym Table:\n" + symTable.toString();
        str += "File Table:\n" + fileTable.toString();
        str += "Heap: \n" + heap.toString();
        str += "Print output:\n" + out.toString();

        return str;
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws VariableNotFound, DivisionByZeroException, InvalidOperatorException, EmptyStackException, HeapWritingException, HeapVariableNotFoundException, FileAlreadyExistsException, FileException {
        try {
            if (exeStack.isEmpty())
                throw new EmptyStackException();
            IStmt crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        } catch (DivisionByZeroException |
                InvalidOperatorException |
                VariableNotFound |
                HeapWritingException |
                HeapVariableNotFoundException |
                EmptyStackException e)
            { throw e;}
    }

    public IDictionary<Integer, Integer> getHeap() {
        return heap;
    }

    public void setHeap(IDictionary<Integer, Integer> heap) {
        this.heap = heap;
    }

    public IStack<IStmt> getExeStack(){
        return exeStack;
    }

    public IDictionary<String, Integer> getSymTable(){
        return symTable;
    }

    public IList<Integer> getOut(){
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }


    public IDictionary<Integer, FileData> getFileTable() {
        return fileTable;
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
