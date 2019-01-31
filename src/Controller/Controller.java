package Controller;

import Domain.ADT.IDictionary;
import Domain.ProgramState;
import Exceptions.*;
import Repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


/**
 * Class used for executing a {@link ProgramState} stored in a {@link IRepository} instance.
 */
public class Controller {

    private IRepository repo;
    private ExecutorService executor;

    /**
     * Constructor
     *
     * @param r - repository of the controller
     */
    public Controller(IRepository r) {
        executor = Executors.newFixedThreadPool(2);
        repo = r;
    }

    /**
     * Adds a new ProgramState to the controller
     *
     * @param newProgramState - ProgramState to be added
     */
    public void addProgramState(ProgramState newProgramState) {
        repo.addProgram(newProgramState);
    }

    /**
     * Removes the ProgramStates that have finished execution
     *
     * @param inPrgList - list of controller's ProgramStates
     * @return the filtered list
     */
    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList) {
        return inPrgList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    /**
     * Executes one step for every ProgramState
     *
     * @param prgList List of ProgramStates
     */
    public void oneStepForAllPrg(List<ProgramState> prgList) {

        // Log the current state of every Program
        prgList.forEach(prg -> repo.logPrgStateExec(prg));

        // Prepare the list of Callable
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (() -> {
                    try {
                        return p.oneStep();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        System.exit(1);
                        return null;
                    }
                }))
                .collect(Collectors.toList());

        // Start the execution of the callables
        // It returns the list of threads
        List<ProgramState> newPrgList = null;
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
        } catch (InterruptedException e) {
        }

        // Add the newly created threads to the list of existing threads
        prgList.addAll(newPrgList);

        // Log the curernt state of every ProgramState
        prgList.forEach(prg -> repo.logPrgStateExec(prg));

        // Update the list of ProgramStates with the ones computed
        repo.setProgramStates(prgList);
    }


    /**
     * Executes all PrgStates from start to end.
     */
    public void allStepEvaluation() {
        executor = Executors.newFixedThreadPool(2);

        // Remove the completed programs
        List<ProgramState> programStateList = removeCompletedPrg(repo.getProgramStates());
        while (programStateList.size() > 0) {

            // Call the conservativeGarbageCollector
            programStateList.forEach(program -> program.getHeapTable().setContent(conservativeGarbageCollector(program.getSymbolTable().values(), program.getHeapTable())));

            oneStepForAllPrg(programStateList);

            programStateList.forEach(program -> System.out.println(program.toString()));

            // Remove the completed programs
            programStateList = removeCompletedPrg(repo.getProgramStates());
        }
        executor.shutdownNow();

        // Close the files
        List<ProgramState> tmpList = repo.getProgramStates();
        tmpList.forEach(program -> program.getFileTable().getProcTable().entrySet()
                .stream()
                .map(e -> e.getValue()).map(e -> e.getReader())
                .forEach(e -> {
                    try {
                        e.close();
                    } catch (IOException exc) {
                        throw new FileReadException();
                    }
                }));

        // Update the repository state
        repo.setProgramStates(programStateList);
    }


    /**
     * Filters the Heap so that every value in it is referenced in the SymTable
     * @param symTableValues - the entries in the SymTable
     * @param heap - the heap
     * @return the set of filtered heap entries
     */
    private Set conservativeGarbageCollector(Collection<Integer> symTableValues, IDictionary<Integer, Integer> heap) {
        return heap.getProcTable().entrySet()
                .stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toSet());
    }


    /**
     * Gets the repository
     * @return the repository
     */
    public IRepository getRepo() {
        return repo;
    }
}
