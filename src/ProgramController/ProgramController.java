package ProgramController;

import Exceptions.MyExceptions;
import Model.ProgramState;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import Repository.Repository;

import java.util.*;
import java.util.concurrent.*;
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
    List<Integer> getAllSymbolTableAdresses()
    {
        return repo.getProgramList().stream()
                .map(p -> getSymbolTableAddresses(p.getSymbolsTable().getValues().values()))
                .reduce(Stream.of(0).collect(Collectors.toList()),
                        (acc, item) -> Stream.concat(acc.stream(), item.stream())
                                .collect(Collectors.toList()));
    }

    public Repository getRepo()
    {
        return repo;
    }

    List<Integer> extractAllValidAddresses()
    {
        Set<Map.Entry<Integer, Value>> heapEntrySet = repo.getProgramList().get(0).getHeapTable().getValues().entrySet();
        LinkedList<Integer> indirectAddressesList = new LinkedList<>(getAllSymbolTableAdresses());
        boolean doneExtracting = false;
        while (!doneExtracting) {
            doneExtracting = true;
            List <Integer> currentIndirectPhaseAddresses = heapEntrySet.stream()
                    .filter(entry -> indirectAddressesList.contains(entry.getKey()))
                    .filter(entry -> entry.getValue() instanceof ReferenceValue)
                    .map(entry -> {ReferenceValue v = (ReferenceValue) entry.getValue();
                        return v.getAddress();})
                    .filter(entry -> !indirectAddressesList.contains(entry))
                    .collect(Collectors.toList());

            if (!currentIndirectPhaseAddresses.isEmpty()) {
                doneExtracting = false;
                indirectAddressesList.addAll(currentIndirectPhaseAddresses);
            }
        }
        return indirectAddressesList;
    }

    void callGarbageCollector()
    {
        repo.getProgramList().get(0).getHeapTable().setValues(safeGarbageCollector(extractAllValidAddresses(),repo.getProgramList().get(0).getHeapTable().getValues()));
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

    public void oneStep() throws MyExceptions
    {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> list = removeCompletedPrograms(repo.getProgramList());
        if (!list.isEmpty()) {
            callGarbageCollector();
            oneStepForAllProgram(list);
        }
        executor.shutdownNow();
        repo.setProgramStates(list);
    }
    public void removeAfterOneStep()
    {
        repo.setProgramStates(removeCompletedPrograms(repo.getProgramList()));
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
                        } catch (InterruptedException ignored) {
                            return null;
                        }
                        catch (ExecutionException exception)
                        {
                            if (exception.getCause() instanceof MyExceptions)
                            {
                                MyExceptions myException = (MyExceptions) exception.getCause();
                                repo.logExceptions(myException);
                            }
                            return null;
                        }
                    })
                    .filter(state -> state != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException except) {
            throw new MyExceptions(except.getMessage());
        }
        activePrograms.addAll(newList);

        activePrograms.forEach(program -> {try {repo.logProgramStateExecution(program);} catch(MyExceptions ignored) {}});
        repo.setProgramStates(activePrograms);
        repo.ifException();

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
