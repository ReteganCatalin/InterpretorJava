package ProgramController;

import Exceptions.MyExceptions;
import Model.ProgramState;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import Repository.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgramController {
    Repository repo;
    ExecutorService executor;
    Map<Integer, Value> safeGarbageCollector(List<Integer> addresses, Map<Integer,Value> heap)
    {
        return heap.entrySet().stream().filter(e->addresses.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    private List<Integer> getSymbolTableAddresses(Collection<Value> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }
    List<Integer> getAllAddresesfromSymbolTable()
    {
        return repo.getProgramList().stream()
                .map(p -> getSymbolTableAddresses(p.getSymbolsTable().getValues().values()))
                .reduce(Stream.of(0).collect(Collectors.toList()),
                        (acc, item) -> Stream.concat(acc.stream(), item.stream())
                                .collect(Collectors.toList()));
    }


    public void allStep() throws MyExceptions
    {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> list = removeCompletedPrograms(repo.getProgramList());
        list.forEach(program -> {try {repo.logProgramStateExecution(program);} catch(MyExceptions ignored) {}});
        while (!list.isEmpty()) {
            callGarbageCollector();
            oneStepForAllProgram(list);
            list = removeCompletedPrograms(repo.getProgramList());
        }
        repo.getProgramList().forEach(program -> {try {repo.logProgramStateExecution(program);} catch(MyExceptions ignored) {}});
        executor.shutdownNow();
        repo.setProgramStates(list);
    }

    public void oneStepForAllProgram(List<ProgramState> activePrograms) throws MyExceptions{

        List<Callable<ProgramState>> callList = activePrograms.stream().map((ProgramState program) -> (Callable<ProgramState>)(() -> {return program.oneStep();}))
                .collect(Collectors.toList());
        List<ProgramState> newList = null;
        try {
            newList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException ignored) {
                            return null;
                        }
                    })
                    .filter(state -> state != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new MyExceptions(e.getMessage());
        }
        activePrograms.addAll(newList);

        activePrograms.forEach(program -> {try {repo.logProgramStateExecution(program);} catch(MyExceptions ignored) {}});
        repo.setProgramStates(activePrograms);

    }

    List<ProgramState> removeCompletedPrograms(List<ProgramState> activePrograms)
    {
        return activePrograms.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }





    public ProgramController(Repository repo) {
        this.repo = repo;
    }
}
