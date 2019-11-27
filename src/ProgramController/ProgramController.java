package ProgramController;

import Exceptions.MyExceptions;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Stmt.IStatement;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import Repository.Repository;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgramController {
    Repository repo;
    ExecutorService executor;
    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap)
    {return heap.entrySet().stream().filter(e->symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues,Collection<Value> heap){
        return  Stream.concat(
                heap.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();})
                ,symTableValues.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();})).collect(Collectors.toList());
    }


    public ArrayList<String> allStep() throws MyExceptions
    {
        ArrayList<String> states=new ArrayList<String>() ;
        ArrayList<ProgramState> program = repo.getProgramList();
        if(program.getStack().isEmpty())
        {
            throw new MyExceptions("The Stack is Empty the program is done");

        }
        states.add(program.toString());
        repo.logProgramStateExecution();
        while (!program.getStack().isEmpty())
        {
            oneStep(program);//here you can display the prg state
            program.getHeapTable().setValues((HashMap)safeGarbageCollector(getAddrFromSymTable(program.getSymbolsTable().getValues().values(),program.getHeapTable().getValues().values()),program.getHeapTable().getValues()));;
            states.add(program.toString());

            repo.logProgramStateExecution();

        }
        return states;
    }

    void oneStepForAllPrg() {
        //before the execution, print the PrgState List into the log file
        // prgList.forEach(prg ->repo.logPrgStateExec(prg));  //RUN concurrently one step for each of the existing PrgStates //
        // -----------------------------------------------------------------------//
        // prepare the list of callablesList<Callable<PrgState>> callList = prgList.stream()
        // .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
        // .collect(Collectors.toList())//start the execution of the callables
        // it returns the list of new created PrgStates
        // (namely threads)List<PrgState> newPrgList = executor.invokeAll(callList). stream()                                                                    . map(future -> { try { return future.get();}                                                                                                catch(...) {                                                                                                  //here you can treat the possible                                                                                                 // exceptions thrown by statements                                                                                                 // execution, namely the green part   // from previous allStep method}                                                                                               })                                                                    .filter(p -> p!=null)                                                                    .collect(Collectors.toList())//add the new created threads to the list of existing threads prgList.addAll(newPrgList);//------------------------------------------------------------------------------//after the execution, print the PrgState List into the log fileprgList.forEach(prg ->repo.logPrgStateExec(prg));
    }
    //Save the current programs in the repositoryrepo.setPrgList(prgList);}
    ArrayList<ProgramState> removeCompletedPrograms(ArrayList<ProgramState> activePrograms)
    {
        return (ArrayList<ProgramState>)activePrograms.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }



    public ProgramController(Repository repo) {
        this.repo = repo;
    }
}
