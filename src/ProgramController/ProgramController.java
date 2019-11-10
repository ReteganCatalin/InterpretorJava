package ProgramController;

import Exceptions.MyExceptions;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Stmt.IStatement;
import Repository.Repository;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramController {
    Repository repo;
    public ProgramState oneStep(ProgramState state) throws MyExceptions {
        MyIStack<IStatement> stk=state.getStack();
        if(stk.isEmpty())
        {
            throw new MyExceptions("Stack is empty no more executions");
        }
        IStatement crtStmt = stk.pop();
        try {
            return crtStmt.execute(state);
        } catch (IOException e) {
            throw new MyExceptions(e.getMessage());
        }
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
        }
        return states;
    }

    public ProgramController(Repository repo) {
        this.repo = repo;
    }
}
