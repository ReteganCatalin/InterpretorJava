package Controller;

import Exceptions.MyExceptions;
import Model.List.MyIList;
import Model.List.MyList;
import Model.ProgramState;
import Model.Stack.MyIStack;
import Model.Stmt.IStmt;
import Repository.Repository;

import java.util.ArrayList;

public class ProgramController {
    Repository repo;
    public ProgramState oneStep(ProgramState state) throws  MyExceptions
    {
        MyIStack<IStmt> stk=state.getStack();
        if(stk.isEmpty())
        {
            throw new MyExceptions("Stack is empty no more executions");
        }
        IStmt  crtStmt = stk.pop();
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
        ProgramState prg = repo.getCurrentProgram();
        if(prg.getStack().isEmpty())
        {
            throw new MyExceptions("The Stack is Empty the program is done");

        }
        states.add(prg.toString());

        while (!prg.getStack().isEmpty())
        {
            oneStep(prg);//here you can display the prg state
            states.add(prg.toString());
        }
        return states;
    }

    public ProgramController(Repository repo) {
        this.repo = repo;
    }
}
