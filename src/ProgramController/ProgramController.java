package ProgramController;

import Exceptions.MyExceptions;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Stmt.IStatement;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import Repository.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgramController {
    Repository repo;
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

    public ProgramState oneStep(ProgramState state) throws MyExceptions {
        MyIStack<IStatement> stk=state.getStack();
        if(stk.isEmpty())
        {
            throw new MyExceptions("Stack is empty no more executions");
        }
        IStatement crtStmt = stk.pop();
        return crtStmt.execute(state);

    }

    public String WrapperOneStep() throws  MyExceptions
    {

        ProgramState prg = repo.getCurrentProgram();
        if(!prg.getStack().isEmpty())
        {
            oneStep(prg);
            return prg.toString();
        }
        throw new MyExceptions("The Stack is Empty");
    }

    public ArrayList<String> allStep() throws MyExceptions
    {
        ArrayList<String> states=new ArrayList<String>() ;
        ProgramState program = repo.getCurrentProgram();
        if(program.getStack().isEmpty())
        {
            throw new MyExceptions("The Stack is Empty the program is done");

        }
        states.add(program.toString());
        repo.logProgramStateExecution();
        while (!program.getStack().isEmpty())
        {
            oneStep(program);//here you can display the prg state
            states.add(program.toString());
            repo.logProgramStateExecution();
            program.getHeapTable().setValues((HashMap)safeGarbageCollector(getAddrFromSymTable(program.getSymbolsTable().getValues().values(),program.getHeapTable().getValues().values()),program.getHeapTable().getValues()));;
        }
        return states;
    }

    public ProgramController(Repository repo) {
        this.repo = repo;
    }
}
