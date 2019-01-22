package Controller;

import Domain.ADT.*;
import Domain.PrgState;
import Repository.IRepository;
import Exceptions.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private IRepository repo;
    private ExecutorService executor;

    /**
     * Constructor
     * @param r - repository of the controller
     */
    public Controller(IRepository r) {
        executor = Executors.newFixedThreadPool(2);
        repo = r;
    }

    /**
     * Add a new ProgramState to the controller
     * @param newProgramState - ProgramState to be added
     */
    public void addProgramState(PrgState newProgramState) {
        repo.addProgram(newProgramState);
    }

    /**
     * Remove the ProgramStates that have finished execution
     * @param inPrgList - list of controller's ProgramStates
     * @return list The filtered list
     */
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    /**
     * Execute one step for every ProgramState
     * @param prgList List of ProgramStates
     * @throws DivisionByZeroException Dividing by 0
     * @throws InvalidOperatorException Creating an ArithmeticExpression with invalid operator
     * @throws VariableNotFound Querying a variable that does not exist
     * @throws HeapWritingException Updating a key that does not exist
     * @throws HeapVariableNotFoundException Querying a key that is not defined in heap
     * @throws EmptyStackException Empty execution stack
     */
    public void oneStepForAllPrg(List<PrgState> prgList) throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapWritingException, HeapVariableNotFoundException, EmptyStackException {

        // Log the curernt state of every ProgramState
        prgList.forEach(prg -> repo.logPrgStateExec(prg));

        // Prepare the list of Callable
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    try {
                        return p.oneStep();
                    } catch (DivisionByZeroException |
                            InvalidOperatorException |
                            VariableNotFound |
                            HeapWritingException |
                            HeapVariableNotFoundException |
                            EmptyStackException e){ throw e;}
                }))
                .collect(Collectors.toList());

        // Start the execution of the callables
        // It returns the list of threads
        List<PrgState> newPrgList = null;
        try {
            newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (ExecutionException | InterruptedException e) {
                            return null;
                        }
                        })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {}

        // Add the newly created threads to the list of existing threads
        prgList.addAll(newPrgList);

        // Log the curernt state of every ProgramState
        prgList.forEach(prg -> repo.logPrgStateExec(prg));

        // Update the list of ProgramStates with the ones computed
        repo.setProgramStates(prgList);
    }


    /**
     *  Execute all steps
     * @throws DivisionByZeroException Dividing by 0
     * @throws InvalidOperatorException Creating an ArithmeticExpression with invalid operator
     * @throws VariableNotFound Querying a variable that does not exist
     * @throws HeapWritingException Updating a key that does not exist
     * @throws HeapVariableNotFoundException Querying a key that is not defined in heap
     * @throws EmptyStackException Empty execution stack
     */
    public void allStepEvaluation() throws DivisionByZeroException, InvalidOperatorException, VariableNotFound, HeapWritingException, HeapVariableNotFoundException, EmptyStackException {
        executor = Executors.newFixedThreadPool(2);

        // Remove the completed programs
        List<PrgState> prgStateList = removeCompletedPrg(repo.getProgramStates());
        while(prgStateList.size() > 0){

            // Call the conservativeGarbageCollector
            prgStateList.forEach(program -> program.getHeap().setContent(conservativeGarbageCollector(program.getSymTable().values(), program.getHeap())));

            try {
                oneStepForAllPrg(prgStateList);
            } catch (DivisionByZeroException |
                    InvalidOperatorException |
                    VariableNotFound |
                    HeapWritingException |
                    HeapVariableNotFoundException |
                    EmptyStackException e){
                throw e;
            }
            prgStateList.forEach(program -> System.out.println(program.toString()));

            // Remove the completed programs
            prgStateList = removeCompletedPrg(repo.getProgramStates());
        }
        executor.shutdownNow();

        // Close the files
        List<PrgState> tmpList = repo.getProgramStates();
        tmpList.forEach(program -> program.getFileTable().getDictionary().entrySet()
                    .stream()
                    .map(e -> e.getValue()).map(e -> e.getReader())
                    .forEach(e -> { try { e.close(); } catch (IOException exc){}}));

        // Update the repository state
        repo.setProgramStates(prgStateList);


    }

    private Set conservativeGarbageCollector(Collection<Integer> symTableValues, IDictionary<Integer,Integer> heap) {
        return heap.getDictionary().entrySet()
                .stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toSet());
    }

    public IRepository getRepo() {
        return repo;
    }
}

//    public PrgState oneStepEvaluation(PrgState programState) throws VariableNotFound, DivisionByZeroException, InvalidOperatorException, EmptyStackException, HeapWritingException, HeapVariableNotFoundException{
//
//        try {
//            IStack<IStmt> stack = programState.getExeStack();
//            if (stack.isEmpty())
//                throw new EmptyStackException();
//            IStmt currentStatement = stack.pop();
//            PrgState prg = currentStatement.execute(programState);
//            return prg;
//
//        } catch (FileAlreadyExistsException | FileException e) {
//            System.err.println(e.getMessage());
//        } catch (DivisionByZeroException | InvalidOperatorException| VariableNotFound| EmptyStackException | HeapWritingException | HeapVariableNotFoundException e) {
//            throw e;
//        }
//       return null;
//    }

//    public void allStepEvaluation() throws VariableNotFound, DivisionByZeroException, InvalidOperatorException, EmptyStackException, HeapWritingException, HeapVariableNotFoundException {
//
//        PrgState program = repo.getCurrentProgram();
//        HeapAllocationStmt.setCount(1);
//        try {
//            while (true) {
//                System.out.println(program.toString());
//                oneStepEvaluation(program);
//                program.getHeap().setContent(conservativeGarbageCollector(program.getSymTable().values(), program.getHeap()));
//                repo.logPrgStateExec(program);
//            }
//        } catch (DivisionByZeroException | InvalidOperatorException| VariableNotFound| EmptyStackException | HeapWritingException | HeapVariableNotFoundException | NullPointerException e) {
//                throw e;
//        }
//        finally {
//            program.getFileTable().getDictionary().entrySet()
//                    .stream()
//                    .map(e -> e.getValue()).map(e -> e.getReader())
//                    .forEach(e -> { try { e.close(); } catch (IOException exc){}});
//            program.getFileTable().getDictionary().clear();
//
//            System.out.println("All files are closed!");
//        }
//    }
