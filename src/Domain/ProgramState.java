package Domain;

import Domain.ADT.*;
import Domain.Statements.IStatement;
import Exceptions.EmptyStackException;


/**
 * An instance of a Program.
 */
public class ProgramState {

    private static int newID = 1;

    private IStack<IStatement> executionStack;
    private IStack<IDictionary<String, Integer>> symbolTable;
    private IList<Integer> out;
    private IStatement originalProgram;

    private ProcTable<String, ProcedureEntry> procedureTable;
    private IDictionary<Integer, SemaphoreEntry> semaphoreTable;
    private IDictionary<Integer, FileData> fileTable;
    private IDictionary<Integer, Integer> heapTable;
    private int id;


    /**
     * Constructor
     *
     * @param originalProgram - the original statement
     */
    public ProgramState(IStatement originalProgram) {
        this.executionStack = new MyStack<>();
        this.symbolTable = new MyStack<>();
        symbolTable.push(new MyDictionary<>());
        this.out = new MyList<>();
        this.fileTable = new FileTable<>();
        this.heapTable = new HeapTable<>();
        this.originalProgram = originalProgram;
        this.semaphoreTable = new SemaphoreTable<>();
        this.id = newID++;
        this.procedureTable = new ProcTable<>();
        this.executionStack.push(originalProgram);
    }

    /**
     * Constructor used by {@link Domain.Statements.ForkStatement}
     *
     * @param executionStack  - the execution stack
     * @param symbolTable     - the symbol table
     * @param out             - the out table
     * @param originalProgram - the original statement
     * @param fileTable       - the file table
     * @param heapTable       - the heapTable table
     * @param semaphoreTable  - the semaphore table
     * @param procedureTable  - the procedure table
     */
    public ProgramState(IStack<IStatement> executionStack, IStack<IDictionary<String, Integer>> symbolTable, IList<Integer> out, IStatement originalProgram, IDictionary<Integer, FileData> fileTable, IDictionary<Integer, Integer> heapTable, IDictionary<Integer, SemaphoreEntry> semaphoreTable, ProcTable<String, ProcedureEntry> procedureTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.id = newID++;
        this.semaphoreTable = semaphoreTable;
        this.procedureTable = procedureTable;
        this.executionStack.push(originalProgram);
    }


    /**
     * Gets a text representation of the ProgramState
     *
     * @return - a text representaton
     */
    @Override
    public String toString() {
        String str = "";
        str += "Id:\n\t" + id;
        str += "\nExeStack:\n" + executionStack.toString();
        str += "Sym Table:\n" + symbolTable.toString();
        str += "File Table:\n" + fileTable.toString();
        str += "HeapTable Table: \n" + heapTable.toString();
        str += "SemaphoreTable Table: \n" + semaphoreTable.toString();
        str += "Procedure Table: \n" + procedureTable.toString();
        str += "Print output:\n" + out.toString();
        str += "\n\n";

        return str;
    }

    /**
     * Checks if the program has finished
     *
     * @return <code>true</code> - if the program has finished
     * <code>false</code> - otherwise
     */
    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    /**
     * Executes the statement on the top of the ExecutionStack
     *
     * @return - the result of the statement execution
     * @see IStatement
     */
    public ProgramState oneStep() {
        if (executionStack.isEmpty())
            throw new EmptyStackException();
        IStatement crtStmt = executionStack.pop();
        return crtStmt.execute(this);
    }

    public IDictionary<Integer, Integer> getHeapTable() {
        return heapTable;
    }

    public IDictionary<String, Integer> getSymbolTable() {
        return symbolTable.peek();
    }

    public IDictionary<Integer, FileData> getFileTable() {
        return fileTable;
    }

    public IDictionary<Integer, SemaphoreEntry> getSemaphoreTable() {
        return semaphoreTable;
    }

    public void setHeapTable(IDictionary<Integer, Integer> heapTable) {
        this.heapTable = heapTable;
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IList<Integer> getOut() {
        return out;
    }

    public void setSymbolTable(IStack<IDictionary<String, Integer>> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ProcTable<String, ProcedureEntry> getProcedureTable() {
        return procedureTable;
    }

    public IStack<IDictionary<String, Integer>> getSymTables() {
        return symbolTable;
    }

    public void setFileTable(IDictionary<Integer, FileData> fileTable) {
        this.fileTable = fileTable;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public void addProcedure(String name, ProcedureEntry entry) {
        this.procedureTable.add(name, entry);
    }

    public void setOut(IList<Integer> out) {
        this.out = out;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public int getId() {
        return id;
    }
}
